package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;

import driver.ConfigReader;

public class LoginPage extends BasicPage
{

	By emailId = By.xpath(ConfigReader.GetProperty("emailId"));
	By btnSubmit = By.xpath(ConfigReader.GetProperty("btnSubmit"));
	By password = By.xpath(ConfigReader.GetProperty("password"));
	By btnLogin = By.id(ConfigReader.GetProperty("btnLogin"));
	By btnAccountLink = By.xpath(ConfigReader.GetProperty("btnAccountLink"));
	By linkSignOut = By.xpath(ConfigReader.GetProperty("linkSignOut"));

	public LoginPage(WebDriver iDriver)
	{
		super(iDriver);
	}

	private boolean checkIfLoginSucceed()
	{
		if (mDriver != null)
			return mDriver.getPageSource().contains("Hello, testersd");
		else
			return false;
	}

	public boolean signIn(String iUsername, String iPassword)
	{
		boolean ret = false;
		if (mDriver != null)
		{
			try
			{
				mDriver.findElement(btnAccountLink).click();
				mDriver.findElement(emailId).sendKeys(iUsername);
				mDriver.findElement(btnSubmit).click();
				mDriver.findElement(password).sendKeys(iPassword);
				mDriver.findElement(btnLogin).click();
				ret = checkIfLoginSucceed();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				ret = false;
			}

		}
		return ret;
	}

	public boolean signOut()
	{
		boolean ret = false;

		if (mDriver != null)
		{
			try
			{

				try
				{
					mDriver.findElement(btnAccountLink).click();
				}
				catch (Exception e)
				{
					mDriver.get("https:\\www.amazon.in");
				}
				WebElement ele = mDriver.findElement(btnAccountLink);
				Action act = mActionBuilder.moveToElement(ele).build();
				act.perform();
				WebElement ele1 = mDriver.findElement(linkSignOut);
				Action act1 = mActionBuilder.moveToElement(ele1).click().build();
				act1.perform();
				ret = true;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				ret = false;
			}
		}
		return ret;
	}
}
