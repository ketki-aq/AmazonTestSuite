package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import testSuites.TestCase1;

public class Main
{

	public static void main(String[] args)
	{

		System.setProperty("webdriver.chrome.driver", ConfigReader.GetProperty("webdriver.chrome.driver"));
		WebDriver driver = new ChromeDriver();

		TestCase1.Run(driver);

		driver.close();
	}

}
