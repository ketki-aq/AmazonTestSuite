package HubXPageClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReviewPage
{
	WebDriver driver;

	public void init()
	{
		System.setProperty("webdriver.chrome.driver",
				"D:\\pranav\\study\\javaEclipse\\Amazon\\ExternalLib\\selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.wallethub.com/");
	}

	public void login()
	{
		WebDriverWait wait1 = new WebDriverWait(driver, 5);
		// driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='text'])[2]")))
				.sendKeys("krrhe.91@gmail.com");
		// driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("krrhe.91@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("*Agree#Privacy1");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.navigate().to("https://wallethub.com/profile/test_insurance_company");
		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		WebElement reviewButton = driver.findElement(By.xpath("//button[contains(text(), 'Get Quote')]"));
		reviewButton.click();
		/*
		 * if (reviewButton.isDisplayed()) { reviewButton.click(); } else
		 */
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Get ')]"))).click();

		driver.findElement(By.xpath("//span[text()='Select...']")).click();
		driver.findElement(By.xpath("li[@class='dropdown-item'][text()='Health']")).click();
		driver.findElement(By.xpath("//div[@class='android']")).sendKeys("ABCFGTJKLYOLYLHROGL<G<<VLDFOROROOLLL");
		Rectangle pointer = driver
				.findElement(By.xpath("//*[name()='svg']//*[name()='g']/*[name()='path' and @fill='#4ae0e1']")).getRect();
		System.out.println(pointer.getHeight());

		// *[name()='svg']/*[name()='rect']

	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		ReviewPage rp = new ReviewPage();
		rp.init();
		rp.login();
	}

}
