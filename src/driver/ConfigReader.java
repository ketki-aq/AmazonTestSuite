package driver;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ConfigReader
{

	private static boolean isInit = false;
	private static Map<String, String> configMap = new HashedMap<String, String>();

	public static void initConfig()
	{
		isInit = true;
		// File directory = new File("./");
		// System.out.println(directory.getAbsolutePath());
		String fileName = "./config.xlsx";
		try
		{

			FileInputStream file = new FileInputStream(new File(fileName));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook

			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext())
			{
				Row row = (XSSFRow) rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();

				if (cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					String key = cell.getStringCellValue();
					if (cellIterator.hasNext())
					{
						cell = cellIterator.next();
						String Value = cell.getStringCellValue();
						configMap.put(key, Value);
					}

				}

				// while (cellIterator.hasNext())
				// {
				// Cell cell = cellIterator.next();
				// // Check the cell type and format accordingly
				// switch (cell.getCellType())
				// {
				// case NUMERIC:
				// System.out.print(cell.getNumericCellValue() + "\t");
				// break;
				// case STRING:
				// System.out.print(cell.getStringCellValue() + "\t");
				// break;
				// }
				// }
				// System.out.println("");
			}
			file.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void initConfigXls()
	{
		isInit = true;
		// File directory = new File("./");
		// System.out.println(directory.getAbsolutePath());
		String fileName = "./config1.xls";
		try
		{

			FileInputStream file = new FileInputStream(new File(fileName));

			// Create Workbook instance holding reference to .xlsx file
			HSSFWorkbook workbook = new HSSFWorkbook(file);

			// Get first/desired sheet from the workbook

			HSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext())
			{
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();

				if (cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					String key = cell.getStringCellValue();
					if (cellIterator.hasNext())
					{
						cell = cellIterator.next();
						String Value = cell.getStringCellValue();
						configMap.put(key, Value);
					}

				}
			}
			file.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static String GetProperty(String iKey)
	{
		if (!isInit)
			initConfigXls();

		String retValue = "";

		if (configMap.containsKey(iKey))
		{
			retValue = configMap.get(iKey);
		}
		return retValue;
	}
}
