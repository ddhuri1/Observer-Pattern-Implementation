## Name: Devina Sachin Dhuri

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on the project.
#### Note: build.xml is present in studentCoursesBackup/src folder.
To clean, compile and run, be sure to be in the studentCoursesBackup folder.

-----------------------------------------------------------------------
## Instruction to clean:

### Command: ant -buildfile src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

### Command: ant -buildfile src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

### Command:  ant -buildfile src/build.xml run -Darg0=input.txt -Darg1=delete.txt -Darg2=output1.txt -Darg3=output2.txt -Darg4=output3.txt -Darg5=0/1/2/3/4

Note: Arguments accept the absolute path of the files. Darg5 is the debug value


-----------------------------------------------------------------------
## Description: Data Structures:

#### Observer Pattern:
Observer is a behavioral design pattern.
In this asssignment, the main tree is the subject and the 2 backup trees are the observers, that get a notification from the subject
on insertion or deletion of a course.
Whenever a new node is created, the main tree is cloned by the 2 observers and the observers are registered at the node of the main tree.
If a course is added to an existing node or a course has been deleted, the main tree notifies all the obe\servers of the update (Insert/Delete)
and the observers take the necessary action to match the main tree structure. 

#### Type of tree used : BST
BST is a special type of binary tree in which left child of a node has value less than the parent and right child has value greater than parent.
##### Time Complexity:
Searching: worst case complexity is O(n). In general, time complexity is O(h) where h is height of BST.  
Insertion: worst case complexity is O(n). In general, time complexity is O(h).  
Deletion: worst case complexity is O(n). In general, time complexity is O(h).  

#### ArrayList is used to store the BNumber and courses.
##### Time Complexity:
add(): O(1)  
remove: O(n)   
contains: O(n)  

-----------------------------------------------------------------------


### References and Citations:
1. BST SOURCE:
https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/
2. OBSRVER PATTERN:
https://www.baeldung.com/java-observer-pattern
