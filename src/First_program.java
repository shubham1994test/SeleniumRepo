import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class First_program 
{
	public static void main(String[] args) 
	{
		String exePath="F:\\Selenium\\geckodriver\\geckodriver.exe";
		System.setProperty("webdriver.firefox.marionette","exePath");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.google.com");
		driver.get("https://www.google.com");

		

	}

}
