package studentCoursesBackup.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import studentCoursesBackup.util.MyLogger;
import studentCoursesBackup.util.MyLogger.DebugLevel;

/**
* @author Devina Dhuri
*/

public class Results implements FileDisplayInterface, StdoutDisplayInterface 
{
	private String outFile;
	private PrintWriter writer;
	private BufferedWriter bw;
    private String strResult;
	
	public Results(String outFile) 
	{
		MyLogger.writeMessage("Results constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
		this.outFile = outFile;
	}
	
	/**
     * This function write the result to a file
     * @param s String to be written
	 * @param outFile File where result is to be written
	 * @return None
     */
	public void writeToFile(String s,String outFile) 
	{
		try 
		{
			bw = new BufferedWriter(new FileWriter(outFile));
			bw.write(s);
			MyLogger.writeMessage("Results written to file", MyLogger.DebugLevel.RESULTS);
			bw.close();
		}
		catch(IOException e) {
			System.err.println("Exception: writing to output.txt");
			System.err.println("Exiting");
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	/**
     * This function stores result
     * @param strResult String to be stored
	 * @return None
     */
	public void storeResult(String strResult) 
	{
        this.strResult = strResult;
    }
	
	/**
     * Getter method for private data members
     * @param None
	 * @return String
     */
	public String getResult() 
	{
		return strResult;
	}

	/**
     * Setter method for private data members
     * @param strResult
	 * @return None
     */
	public void setResult(String strResult) 
	{
		this.strResult = strResult;
	}
	
	/**
	* @param None
	* @return String 
	*/
	public String toString()
	{
		return "--------------------------" + "\n" + getClass().getName()+"@"+Integer.toHexString(hashCode()) + "\n" + "Output FileName:" + outFile + "--------------------" + "\n" ;
	}
}
