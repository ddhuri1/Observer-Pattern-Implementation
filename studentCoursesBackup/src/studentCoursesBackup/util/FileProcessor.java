package studentCoursesBackup.util;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Scanner; 
import java.io.File; 
import studentCoursesBackup.util.MyLogger;
import studentCoursesBackup.util.MyLogger.DebugLevel;

/**
 * @author Devina Dhuri
 */
 
public class FileProcessor 
{
	private String inFile;
	BufferedReader reader;
	FileInputStream fis;
	
	public FileProcessor(String inFile) 
	{
		MyLogger.writeMessage("FileProcessor constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
		this.inFile = inFile;
		try
		{
			// MyLogger.writeMessage("FileProcessor constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
			fis = new FileInputStream(inFile);
			reader = new BufferedReader(new InputStreamReader(fis));
		}
		catch(Exception e)
		{
			System.err.println("Exit from File Processor : Exiting Program");
			System.err.println("Error Opening the Input File");
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	* This function returns a line read from file
	* @param None
	* @return String 
	*/
	public String readLine() throws IOException
	{
		try 
		{
			return reader.readLine();
		}
		catch (IOException e) 
		{
			System.err.println("Exit from File Processor : Exiting Program");
			System.err.println("Error Reading the Input/Delete File");
			e.printStackTrace();
			System.err.println(e.getMessage());
			System.exit(1);
		}	
		return null;
	}
	
	/**
	* @param None
	* @return String 
	*/
	public String toString()
	{
		return "--------------------------" + "\n" + getClass().getName()+"@"+Integer.toHexString(hashCode()) + "\n" + "Input FileName:" + inFile + " \n " + "--------------------------" + "\n";
	}
	
}