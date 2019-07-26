import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Conversion 
{
	public static void main(String[] args) 
	{
		String exePath = "F:\\Selenium\\chromedriver_win32\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://www.poundsterlinglive.com/best-exchange-rates/convert/best-canadian-dollar-to-us-dollar-exchange-rate?amount=1");
		
		Actions click = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,2500)");

		//		click.moveToElement(driver.findElement(By.xpath("//h2[contains(text(), 'Exchange Rate History for CAD To USD: 2019')]"))).perform();
		WebElement ele = driver.findElement(By.xpath("(//td[@class='arrows'])[1]/div"));		
		ele.click();
//		click.doubleClick(ele).perform();
		String t = driver.findElement(By.xpath("//h3[contains(text(),'May')]/..//td[@class='calendar-day']/p[3]")).getText();
		System.out.println(t);
	}

}
