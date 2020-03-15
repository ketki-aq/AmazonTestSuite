package testSuites;

import org.openqa.selenium.WebDriver;

import driver.ConfigReader;
import driver.Logger;
import pageClasses.CartPage;
import pageClasses.HomePage;
import pageClasses.LoginPage;

public class TestCase1
{

	public static void Run(WebDriver driver)
	{

		boolean result = false;

		try
		{
			HomePage home = new HomePage(driver);

			Logger.Log("Loading page..");
			result = home.launchWebsite("https:\\www.amazon.in");
			Logger.Log("Loading page:", result);

			Logger.Log("Shopping Items..");
			result = home.shopItems();
			Logger.Log("Shopping Items:", result);

			CartPage cart = new CartPage(driver);

			Logger.Log("Checking Cart..");
			result = cart.viewCart();
			Logger.Log("Checking Cart:", result);

			LoginPage login = new LoginPage(driver);
			Logger.Log("Logging In..");
			result = login.signIn(ConfigReader.GetProperty("userID"), ConfigReader.GetProperty("userPWD"));
			Logger.Log("Logging In:", result);

			Logger.Log("Deleting Item from Cart..");
			result = cart.deleteItemFromCart();
			Logger.Log("Deleting Item from Cart:", result);

			Logger.Log("CheckOut..");
			result = cart.checkOut();
			Logger.Log("CheckOut:", result);

			Logger.Log("Logging Out..");
			result = login.signOut();
			Logger.Log("Logging Out:", result);

			Logger.closeLogger();

		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
