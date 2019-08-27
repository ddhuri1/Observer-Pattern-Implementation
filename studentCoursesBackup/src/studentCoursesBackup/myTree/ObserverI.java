package studentCoursesBackup.myTree;

import studentCoursesBackup.myTree.Node.OPERATION;

public interface ObserverI 
{
	public void update(Node node, String course,OPERATION operation);
}