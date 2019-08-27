package studentCoursesBackup.myTree;

import studentCoursesBackup.myTree.Node.OPERATION;

public interface SubjectI 
{
	public void registerObserver(Node observer);
    public void notifyAll(Node node, String course, OPERATION operation);
}