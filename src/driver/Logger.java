package driver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger
{
	private static boolean isInit = false;
	private static String logFileName = "./testLog.txt";
	private static String LogFileAbsPath = "";
	private static BufferedWriter writer = null;

	private static void init()
	{
		if (!isInit)
		{
			isInit = true;
			File logFile = new File(logFileName);
			LogFileAbsPath = logFile.getAbsolutePath();

			try
			{
				writer = new BufferedWriter(new FileWriter(logFile));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}

	public static void Log(String iMsg, boolean iResult)
	{
		String logMsg = iMsg + (iResult == true ? " Success" : " Failed");
		System.out.println(logMsg);
		writeToFile(logMsg);

	}

	public static void Log(String iMsg)
	{
		System.out.println(iMsg);
		writeToFile(iMsg);
	}

	private static void writeToFile(String iMsg)
	{
		init();
		try
		{
			writer.write(iMsg + "\n");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void closeLogger()
	{
		System.out.println("Check log at: " + LogFileAbsPath);
		if (writer != null)
		{
			try
			{
				writer.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
