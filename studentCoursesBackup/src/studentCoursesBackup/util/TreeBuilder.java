package studentCoursesBackup.util;

/** BST SOURCE:
* https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/
* OBSRVER PATTERN:
* https://www.baeldung.com/java-observer-pattern
*/

import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.util.Results;
import java.util.ArrayList;
import studentCoursesBackup.myTree.Node.OPERATION;
import studentCoursesBackup.util.MyLogger;
import studentCoursesBackup.util.MyLogger.DebugLevel;

/**
* @author Devina Dhuri
*/

public class TreeBuilder
{
	private int deletedNodes = 0;
	private int insertNodes = 0;
	private Node rootNode;
    public String resString;
	String[] dataArray ;
	int bNumber ;
	String course;
	Node currentNode = null;
	private Node nodeBackup1 = null;
	private Node nodeBackup2 = null;
	boolean present = true;
	 Node node = null;
	 
    /**
     * Constructor
     */
    public TreeBuilder() 
	{
		MyLogger.writeMessage("TreeBuilder constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
        rootNode = null;
        resString = "";
    }

	/**
     * This function parses delete text and deletes the nodes
     * @param line Line read from file
	 * @param tree TreeBuilder object
	 * @return None
     */
    public void parseDelete(String line, TreeBuilder tree) 
	{
		try
		{
			dataArray = line.split(":");
			bNumber  = Integer.parseInt(dataArray[0]);
			if(bNumber < 1000 || bNumber > 9999)
			{
				System.err.println("Exit from TreeBuilder : Invalid range for BNumber");
				System.exit(1);
			}
			
			course  = dataArray[1];
			
			currentNode = tree.searchNode(bNumber);
		
			if(currentNode != null) 
			{
				if (currentNode.getCourse().contains(course)) 
				{
					currentNode.getCourse().remove(course);
					deletedNodes +=1;
					MyLogger.writeMessage("Node deleted from the tree", MyLogger.DebugLevel.DELETE);
				}
				currentNode.notifyAll(currentNode, course, OPERATION.DELETE);
            }
        }
		catch (NumberFormatException e) 
		{
			System.err.println("Exit from TreeBuilder : Exiting Program");
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
        }
	}

	/**
     * This function checks validity of entered course name
     * @param course Course name
	 * @return Boolean value true/ false
     */
	public Boolean validCourse(String course)
	{
		if(course.matches("[A-K]"))
			return true;
		else 
			return false;
	}
	
	/**
     * This function parses input
     * @param line read from file
	 * @param tree TreeBuilder object
	 * @param backup1 TreeBuilder object
	 * @param backup2 TreeBuilder object
	 * @return None
     */
    public void parseInput(String line, TreeBuilder tree, TreeBuilder backup1, TreeBuilder backup2) 
	{
		try 
		{
			Boolean exists = false, validCr = false;
			dataArray = line.split(":");
			bNumber  = Integer.parseInt(dataArray[0]);
			if(bNumber < 1000 || bNumber > 9999)
			{
				System.err.println("Exit from TreeBuilder : Invalid range for BNumber");
				System.exit(1);
			}
			
			course  = dataArray[1];
			validCr = validCourse(course);

			currentNode = tree.searchNode(bNumber);

			if((currentNode != null) && (currentNode.getCourse().contains(course)))
				exists = true;

			if(validCr)
			{
				if (currentNode != null) 
				{
					if(!exists) 
					{
						
						currentNode.getCourse().add(course);
						currentNode.notifyAll(currentNode, course, OPERATION.INSERT);
					}
				} 
				else 
				{
					node = new Node(bNumber, course);
					tree.insertNode(node);
					insertNodes += 1;
					MyLogger.writeMessage("New Node added to the main tree", MyLogger.DebugLevel.INSERT);
					
					nodeBackup1 =  node.clone();
					backup1.insertNode(nodeBackup1);
					node.registerObserver(nodeBackup1);
					
					nodeBackup2 =  node.clone();
					backup2.insertNode(nodeBackup2);
					node.registerObserver(nodeBackup2);
				}
			}
			else
			{
				System.out.println("Input should contain course names in the range A-K");
				System.exit(0);
			}	
		} 
		catch (NumberFormatException e) 
		{
			System.err.println("Exit from TreeBuilder : Exiting Program");
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
     * This function search for a specific node based on the BNumber
     * @param bNumber
     * @return Node the node found
     */
    public Node searchNode(int bNumber) 
	{
        Node temp = rootNode;
        while (temp != null) 
		{
            if (temp.getBNumber() == bNumber)
			{
                return temp;
            } 
			else if (temp.getBNumber() > bNumber) 
			{
                temp = temp.getLeft();
            } 
			else 
			{
                temp = temp.getRight();
            }
        }
        return null;
    }

    /**
     * Function to insert node
	 * @param node Node to be inserted
     * @return Node rootNode 
     */
    public Node insertNode(Node node) 
	{
		if (rootNode == null) 
		{
            rootNode = node;
            return node;
        }
        if (rootNode.getBNumber() == node.getBNumber()) 
		{
            if (!rootNode.getCourse().contains(node.getCName())) 
			{
                rootNode.getCourse().add(node.getCName());
            }
        }
        if (node.getBNumber() < rootNode.getBNumber()) 
		{
            rootNode.setLeft( insertNodeR(node, rootNode.getLeft()));
        } 
		else 
		{
            // rootNode.right = insertNodeR(node, rootNode.right);
			rootNode.setRight(  insertNodeR(node, rootNode.getRight()));
        }
        return rootNode;
    }

    /**
     * Recursive function to insert node
     * @param rootNode root of tree
     * @param node, node to be inserted
     * @return Node root node
     */
    public Node insertNodeR(Node node, Node rootNode) 
	{
        if (rootNode == null) 
		{
            rootNode = node;
            return node;
        }
        if (node.getBNumber() == rootNode.getBNumber()) 
		{
            if (!rootNode.getCourse().contains(node.getCName())) 
			{
                rootNode.getCourse().add(node.getCName());
            }
        }
        if (node.getBNumber() < rootNode.getBNumber()) 
		{
            rootNode.setLeft( insertNodeR(node, rootNode.getLeft()));
        } 
		else 
		{
            // rootNode.right = insertNodeR(node, rootNode.right);
			rootNode.setRight(  insertNodeR(node, rootNode.getRight()));
        }
        return rootNode;
    }
	
	/**
     * This function prints the messages in Result object
     * @param result
     */
    public void printNodes(Results result) 
	{
        result.storeResult(resString);
    }

	/**
     * Prints the tree in ascending order: inorder
     * @param rootNode root of tree
	 * @param res Result to be printed
	 * @return None
     */
	public void print(Results res, Node rootNode) 
	{
		
        if(rootNode!=null)
		{
			print(res,rootNode.getLeft());
			// if(!rootNode.getCourse().isEmpty())
			// {
				resString += "BNumber:";
				resString += rootNode.getBNumber();
				resString += ", Courses:";
				for(String course : rootNode.getCourse())
				{
					resString += course+" ";
				}
				resString += "\n\n";
			// }
			print(res,rootNode.getRight());	
		}
    }

	/**
     * Getter method for private data members
     * @param None
	 * @return Node root
     */
	public Node getOriginalNode() 
	{
		return rootNode;
	}

	/**
     * Setter method for private data members
     * @param rootNode
	 * @return None
     */
	public void setOriginalNode(Node rootNode) 
	{
		this.rootNode = rootNode;
	}

	/**
     * Getter method for private data members
     * @param None
	 * @return Node backUp 1
     */
	public Node getObserverNode1() 
	{
		return nodeBackup1;
	}

	/**
     * Setter method for private data members
     * @param observer node 1
	 * @return None
     */
	public void setObserverNode1(Node observerNode1) 
	{
		this.nodeBackup1 = observerNode1;
	}

	/**
     * Getter method for private data members
     * @param None
	 * @return Node backUp 2
     */
	public Node getObserverNode2() 
	{
		return nodeBackup2;
	}

	/**
     * Setter method for private data members
     * @param observer node 2
	 * @return None
     */
	public void setObserverNode2(Node observerNode2) 
	{
		this.nodeBackup2 = observerNode2;
	}
	
	/**
	* @param None
	* @return String 
	*/
	public String toString()
	{
		return "--------------------------" + "\n" + getClass().getName()+"@"+Integer.toHexString(hashCode()) + "\n" + "Nodes Inserted:" + insertNodes + " \n " + "Nodes Deleted:" + deletedNodes +  "--------------------------" + "\n";
	}
}