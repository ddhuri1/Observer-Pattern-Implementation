package studentCoursesBackup.myTree;

import studentCoursesBackup.util.Results;
import java.util.ArrayList;
import studentCoursesBackup.util.MyLogger;
import studentCoursesBackup.util.MyLogger.DebugLevel;
import studentCoursesBackup.myTree.Node.OPERATION;
import studentCoursesBackup.myTree.SubjectI;

/**
 * @author Devina Dhuri
 */

public class Node implements ObserverI,SubjectI,Cloneable
{
    private int bNumber;
    private String course;
    private Node left = null;
	private Node right = null; 
   	private ArrayList<String> courses = new ArrayList<String>();
    private ArrayList<Node> backupList = new ArrayList<Node>();

	public enum OPERATION 
	{
		INSERT, 
		DELETE
	}
	
    /**
     * Parameterized Constructor
     * @param bNumber BNumber pf student 
     * @param course Course name
     */
    public Node(int bNumber, String course) 
	{
		MyLogger.writeMessage("Node constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
        this.bNumber = bNumber;
        this.course = course;
        this.bNumber = bNumber;
        this.courses.add(course);
    }
	
	public Node(){}
	
	/**
     * Function to clone new Node
	 * @param None
	 * @return Node 
     */
    public Node clone() 
	{
		Node node = new Node();
		ArrayList<String> list = new ArrayList<>();
		node.left = null;
		node.setBNumber(this.bNumber);
		node.right = null;
		for(int i = 0 ; i < courses.size(); i++)
		{
			list.add(courses.get(i));
		}
		node.setCourse(list);
		
		// try {
			// node = (Node) super.clone();
		// }
		// catch(CloneNotSupportedException e) 
		// {
			// System.err.println("Exception: Not able to clone super class");
			// System.err.println("Exit from Node : Exiting Program");
			// System.err.println(e.getMessage());
			// e.printStackTrace();
			// System.exit(1);
		// }
		return node;
    }
	
	/**
     * Function to update backup nodes
	 * @param node Node to be updated
	 * @param course Course needed to be added
	 * @param operation Insert or Delete kind of update needed
	 * @return None 
     */
	public void update(Node node, String course,OPERATION operation) 
	{
		switch(operation) 
		{
			case INSERT:
				if(!node.courses.contains(course))
				{
					// System.out.println("comes here2");
					node.courses.add(course);
				}
				break;
				
			case DELETE:
				if(node.courses.contains(course))
				{
					node.courses.remove(course);
				}
				break;
		}
	}
	
	/**
     * Function to notify all observers of any change made
     * @param node Node to be updated
	 * @param course Course needed to be added
	 * @param operation Insert or Delete kind of update needed
	 * @return None
     */
    public void notifyAll(Node node, String course, OPERATION operation) {
        for(Node node1: backupList) 
		{
			update(node1, course, operation);
        }
    }

    /**
     * Function to register observer
     * @param observer Observer needed to be added 
	 * @return None
     */
    public void registerObserver(Node observer) 
	{
        backupList.add(observer);
    }
	
	/**
     * Getter method for private data members
     * @param None
	 * @return Int bNumber
     */
	public int getBNumber() 
	{
		return bNumber;
	}

	/**
     * Setter method for private data members
     * @param bNumber
	 * @return None
     */
	public void setBNumber(int bNumber) 
	{
		this.bNumber = bNumber;
	}
	
	/**
     * Getter method for private data members
     * @param None
	 * @return NOde left
     */
	public Node getLeft() 
	{
		return left;
	}

	/**
     * Setter method for private data members
     * @param left
	 * @return None
     */
	public void setLeft(Node left) 
	{
		this.left = left;
	}
	
	/**
     * Getter method for private data members
     * @param None
	 * @return NOde right
     */
	public Node getRight() 
	{
		return right;
	}

	/**
     * Setter method for private data members
     * @param right
	 * @return None
     */
	public void setRight(Node right) 
	{
		this.right = right;
	}

	/**
     * Getter method for private data members
     * @param None
	 * @return String
     */
	public String getCName() 
	{
		return course;
	}

	/**
     * Setter method for private data members
     * @param course
	 * @return None
     */
	public void setCName(String course) 
	{
		this.course = course;
	}
	
	/**
     * Getter method for private data members
     * @param None
	 * @return ArrayList of courses
     */
	public ArrayList<String> getCourse() 
	{
		return courses;
	}

	/**
     * Setter method for private data members
     * @param courses ArrayList of courses
	 * @return None
     */
	public void setCourse(ArrayList<String> courses) 
	{
		this.courses = courses;
	}
	
	/**
	* @param None
	* @return String 
	*/
	public String toString()
	{
		return "--------------------------" + "\n" + getClass().getName()+"@"+Integer.toHexString(hashCode()) + "\n" + "Observers are:" + backupList + " \n " + "--------------------------" + "\n";
	}

}