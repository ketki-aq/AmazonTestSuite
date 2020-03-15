package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import driver.ConfigReader;

public class CartPage extends BasicPage
{
	By cartLink = By.xpath(ConfigReader.GetProperty("cartLink"));
	By checkoutButton = By.xpath(ConfigReader.GetProperty("checkoutButton"));
	By deleteItems = By.xpath(ConfigReader.GetProperty("deleteItems"));
	By presentOnCartPage = By.xpath(ConfigReader.GetProperty("presentOnCartPage"));
	// By cartLink = By.xpath("//div[@id='nav-tools']//a[@id='nav-cart']");
	// By checkoutButton = By.xpath("//input[@name='proceedToCheckout']");
	// By deleteItems = By.xpath("//input[@value='Delete']");
	LoginPage login = new LoginPage(mDriver);

	public CartPage(WebDriver iDriver)
	{
		super(iDriver);
	}

	public boolean viewCart()
	{
		boolean ret = false;

		if (mDriver != null)
		{
			try
			{
				mDriver.findElement(cartLink).click();
				ret = validatePageContent("Shopping Cart");
			}
			catch (Exception e)
			{
				e.printStackTrace();
				ret = false;
			}
		}
		return ret;
	}

	public boolean checkOut()
	{
		boolean ret = false;

		if (mDriver != null)
		{
			try
			{
				mDriver.findElement(cartLink).click();
				ret = validatePageContent("Shopping Cart");
				if (ret)
				{
					mDriver.findElement(checkoutButton).click();
					ret = true;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				ret = false;
			}
		}
		return ret;
	}

	public boolean deleteItemFromCart()
	{
		boolean ret = false;
		if (mDriver != null)
		{
			try
			{
				mDriver.findElement(cartLink).click();
				ret = validatePageContent("Shopping Cart");
				if (ret)
				{
					mDriver.findElement(deleteItems).click();
					ret = validatePageContent("was removed from Shopping Cart"); // mDriver.findElement(By.xpath(ConfigReader.GetProperty("linkDelete"))).isDisplayed();
				}
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
