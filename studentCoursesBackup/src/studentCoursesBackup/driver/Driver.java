
package studentCoursesBackup.driver;

import studentCoursesBackup.util.Results;
import studentCoursesBackup.util.TreeBuilder;
import studentCoursesBackup.util.FileProcessor;
import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.util.MyLogger;
import studentCoursesBackup.util.MyLogger.DebugLevel;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.NumberFormatException;

/**
* @author Devina Dhuri
*/

public class Driver 
{
	public static void main(String[] args) 
	{
		String inFile = "";
		String OutFile1 = "";
		String OutFile2 = "";
		String OutFile3 = "";
		String delFile = "";
		String line;
		int debugValue = -1;
		Results res ;
		Results res2 ;
		Results res3 ;
		try 
		{
			//validation check for arguments.
			if (args.length != 6 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}") || args[3].equals("${arg3}") || args[4].equals("${arg4}")|| args[5].equals("${arg5}"))
			{
				throw new IllegalArgumentException("Error Illegal Number of Arguments");
			}	
			else if(args.length == 6)
			{
				if(args[0] != null && !args[0].equals("${arg0}")) 
				{
					inFile = args[0];
				}
				else 
				{
					throw new IllegalArgumentException("Invalid input file");
				}
				if(args[1] != null && !args[1].equals("${arg1}")) 
				{
					delFile = args[1];
				}
				else 
				{
					throw new IllegalArgumentException("Invalid delete file");
				}
				if(args[2] != null && !args[2].equals("${arg2}")) 
				{
					OutFile1 = args[2];
				}
				else 
				{
					throw new IllegalArgumentException("Invalid output1 file");
				}
				if(args[3] != null && !args[3].equals("${arg3}")) 
				{
					OutFile2 = args[3];
				}
				else 
				{
					throw new IllegalArgumentException("Invalid output2 file");
				}
				if(args[4] != null && !args[4].equals("${arg4}")) 
				{
					OutFile3 = args[4];
				}
				else 
				{
					throw new IllegalArgumentException("Invalid output3 file");
				}
				if(args[5] != null && !args[5].equals("${arg5}")) 
				{
					try 
					{
            			debugValue = Integer.parseInt(args[5]);
						if(!(debugValue >= 0 && debugValue <= 4)) 
						{
							throw new NumberFormatException("Debug value must be integers between 0 and 4 inclusive");
						}
						MyLogger.setDebugValue(debugValue);
            		}
            		catch(NumberFormatException e) 
					{
            			System.err.println("Exit from Driver : Exiting Program");
						System.err.println(e.getMessage());
						e.printStackTrace();
						System.exit(1);
            		}
				}
				else 
				{
					throw new IllegalArgumentException("Please enter debug value");
				}
				
				File file=new File(inFile);
				boolean exists = file.exists();
				
				//checks if File exists.
				if(exists == false)
				{
					throw new FileNotFoundException("Error File Not Found");
				}
				
				if(file.length() == 0)
				{
					throw new Exception("Error Empty Input File");
				}
				res = new Results(OutFile1);
				res2 = new Results(OutFile2);
				res3 = new Results(OutFile3);
				
				File file1 = new File(delFile);
				boolean exists1 = file1.exists();
				TreeBuilder originalTree = new TreeBuilder();
				
				//checks if File exists.
				if(exists1 == false)
				{
					throw new FileNotFoundException("Error File Not Found");
				}
				
				if(file1.length() == 0)
				{
					throw new Exception("Error Empty Input File");
				}
				
				TreeBuilder backupTree1 = new TreeBuilder();
				TreeBuilder backupTree2 = new TreeBuilder();
				FileProcessor fp = new FileProcessor(inFile);
				line = fp.readLine();
				
				while(line != null)
				{
					originalTree.parseInput(line, originalTree, backupTree1, backupTree2);
					line = fp.readLine();
				}
				
				fp = new FileProcessor(delFile);
				line = fp.readLine();
				
				while(line != null)
				{
					originalTree.parseDelete(line, originalTree);
					line = fp.readLine();
				}
				
				originalTree.print(res, originalTree.getOriginalNode());
				originalTree.printNodes(res);
				res.writeToFile(res.getResult(), OutFile1);
				
				backupTree1.print(res2, backupTree1.getOriginalNode());
				backupTree1.printNodes(res2);
				res2.writeToFile(res2.getResult(), OutFile2);
				
				backupTree2.print(res3, backupTree2.getOriginalNode());
				backupTree2.printNodes(res3);
				res3.writeToFile(res3.getResult(), OutFile3);
			}
		}
		catch(NumberFormatException e) 
		{
			System.err.println("Exit from Driver : Exiting Program");
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		
		catch(IllegalArgumentException e) 
		{ 
			
			System.err.println("Exit from Driver : Exiting Program");
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		catch(FileNotFoundException e) 
		{ 
			System.err.println("Exit from Driver : Exiting Program");
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		catch(Exception e) 
		{ 
			System.err.println("Exit from Driver : Exiting Program");
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}
}