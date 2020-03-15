package pageClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import driver.ConfigReader;
import driver.Logger;

public class HomePage extends BasicPage
{
	By searchBox = By.id(ConfigReader.GetProperty("searchBox"));
	By mobileElement = By.cssSelector(ConfigReader.GetProperty("mobileElement"));
	By btnAddToCart = By.xpath(ConfigReader.GetProperty("btnAddToCart"));
	By productInCart = By.xpath(ConfigReader.GetProperty("productInCart"));
	By btnSubmit = By.xpath(ConfigReader.GetProperty("btnSubmit"));
	By laptopElement = By.xpath(ConfigReader.GetProperty("laptopElement"));
	By popUpBox = By.xpath(ConfigReader.GetProperty("popUpBox"));
	By btnAddLaptop = By.xpath(ConfigReader.GetProperty("btnAddLaptop"));
	By mobileElement2 = By.xpath("//span[@id='pdagDesktopSparkleDescription1']");

	By btnClosePopup = By.xpath(ConfigReader.GetProperty("popupClose"));

	public HomePage(WebDriver iDriver)
	{
		super(iDriver);
	}

	public boolean launchWebsite(String iUrl)
	{
		boolean ret = false;
		if (mDriver != null)
		{
			mDriver.get(iUrl);

			String expectedTitle = ConfigReader.GetProperty("pageTitle");
			ret = waitForPageLoadAndTitleContains(expectedTitle);
		}
		return ret;
	}

	public boolean checkIfLoginSucceed()
	{
		if (mDriver != null)
			return mDriver.getPageSource().contains("Hello");
		else
			return false;
	}

	private boolean ShopItem(String iSearchKey, By iItem)
	{
		boolean ret = false;

		try
		{
			boolean newTabOpen = false;
			WebElement element = null;
			if (mDriver != null)
			{
				mDriver.findElement(searchBox).clear();
				mDriver.findElement(searchBox).sendKeys(iSearchKey);
				mDriver.findElement(btnSubmit).click();

				mDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				element = waitForElementPresence(iItem);

				if (element != null)
				{
					element.click();
					mDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

					try
					{
						element = mDriver.findElement(btnAddToCart);
					}
					catch (Exception e) // new tab opens
					{
						switchToNextWindow();
						newTabOpen = true;
					}

					mDriver.findElement(btnAddToCart).click();
					ret = validateElement(productInCart, "Added to Cart");

					if (ret == false)
					{
						// may be some popup opens, just close it
						mDriver.findElement(btnClosePopup).click();
						ret = validateElement(productInCart, "Added to Cart");
					}

					if (newTabOpen)
						switchToPreviousWindow(true);
				}
			}
		}
		catch (Exception e)
		{
			ret = false;
			e.printStackTrace();
		}
		return ret;

	}

	public boolean shopItems() throws InterruptedException
	{
		boolean ret = false;

		if (mDriver != null)
		{
			Logger.Log("Shopping Item 1..");
			ret = ShopItem("Mobiles under 10000", mobileElement);
			Logger.Log("Shopping Item 1:", ret);

			Logger.Log("Shopping Item 2..");
			ShopItem("Iphone X", mobileElement2);
			Logger.Log("Shopping Item 2:", ret);

			Logger.Log("Shopping Item 3..");
			ShopItem("Dell Inspiron 3567", laptopElement);
			Logger.Log("Shopping Item 3:", ret);
		}

		return ret;
	}

}
