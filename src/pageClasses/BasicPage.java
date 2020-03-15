package pageClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicPage
{
	protected WebDriver mDriver;
	protected Actions mActionBuilder;

	private static int mCurrentTab = 0;

	BasicPage(WebDriver iDriver)
	{
		this.mDriver = iDriver;
		this.mActionBuilder = new Actions(iDriver);
	}

	public boolean switchToNextWindow()
	{
		boolean result = false;
		if (mDriver != null)
		{
			Set<String> windowSet = mDriver.getWindowHandles();
			List<String> windowList = new ArrayList<String>(windowSet);

			String currentWindow = mDriver.getWindowHandle();
			mCurrentTab = windowList.indexOf(currentWindow);
			int actualTabNumbers = windowList.size();

			if ((mCurrentTab + 1) <= actualTabNumbers - 1)
			{
				mCurrentTab++;
				String curWindow = windowList.get(mCurrentTab);
				mDriver.switchTo().window(curWindow);
				result = true;
			}

		}
		return result;
	}

	public boolean switchToPreviousWindow(boolean closeOldWindow)
	{
		boolean result = false;
		if (mDriver != null)
		{
			Set<String> windowSet = mDriver.getWindowHandles();
			List<String> windowList = new ArrayList<String>(windowSet);

			String currentWindow = mDriver.getWindowHandle();
			mCurrentTab = windowList.indexOf(currentWindow);

			if (closeOldWindow)
			{
				mDriver.close();
			}
			mCurrentTab--;

			mCurrentTab = (mCurrentTab > 0) ? mCurrentTab : 0;

			String curWindow = windowList.get(mCurrentTab);
			mDriver.switchTo().window(curWindow);
			result = true;
		}
		return result;
	}

	public boolean validateElement(By iElementName, String iRefElement)
	{
		boolean ret = false;
		try
		{
			String elemText = mDriver.findElement(iElementName).getText();
			ret = (iRefElement.equalsIgnoreCase(elemText));
		}
		catch (Exception e)
		{

		}

		return ret;
	}

	public WebElement waitForElementPresence(By iElementName) // String elemID)
	{
		WebElement element = null;
		if (mDriver != null)
		{
			WebDriverWait wait = new WebDriverWait(mDriver, 30); // you can reuse this one
			element = wait.until(ExpectedConditions.presenceOfElementLocated(iElementName));
		}
		return element;
	}

	public boolean waitForPageLoadAndTitleContains(String pageTitle)
	{
		int timeout = 2;
		boolean ret = false;
		if (mDriver != null)
		{
			WebDriverWait wait = new WebDriverWait(mDriver, timeout, 1000);
			ret = wait.until(ExpectedConditions.titleContains(pageTitle));
		}
		return ret;
	}

	public boolean validatePageContent(String iContent)
	{
		if (mDriver != null)
			return mDriver.getPageSource().contains(iContent);
		else
			return false;
	}

}
