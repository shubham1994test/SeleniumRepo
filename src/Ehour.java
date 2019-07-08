import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class Ehour 
{
	public static void main(String[] args) throws InterruptedException, ParseException 
	{
		String exePath = "F:/Selenium/chromedriver_win32/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.gmail.com");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("Shubham.gupta@redblink.net");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		driver.findElement(By.xpath("//input[@aria-label='Enter your password']")).sendKeys("Ganeshg@1994");  
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']")));
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='aj2']")));
		driver.findElement(By.xpath("//a[@href='https://mail.google.com/mail/u/0/#sent']")).click();
		Thread.sleep(10000);
		driver.navigate().refresh();
		driver.findElement(By.xpath("//*[@id=':28']")).click();
		String etime = driver.findElement(By.xpath("//span[contains(text(),'hours ago')]")).getText();
		if(etime.length()>24)
		{
		etime.substring(12, 16);
		etime= etime.concat(":00 PM");
		}
		else 
		{
				String[] a = etime.split(" ");
				etime=a[0].concat(":00");
		}
		System.out.println(etime);
		String arrivaltime = "09:30:00";

		DateFormat df = new SimpleDateFormat("hh:mm:ss");
		Date stopt = df.parse(etime);
		Date startt = df.parse(arrivaltime);
	
		DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
		String fstop = outputformat.format(stopt);
		Date sstt = df.parse(fstop);
		System.out.println(sstt.getTime());
		System.out.println(startt.getTime());
		long diff = sstt.getTime()-startt.getTime();
		long diffhours= diff / (60 * 60 * 1000) % 24;
		System.out.println(diffhours);
		String str = driver.findElement(By.xpath("//h2[@class='hP']")).getText(); 

		if(str.contains("Daily Status"))
		{
			String n = str.substring(14,18);
			String[] j = n.split("-");
			String date = j[0];
			//	System.out.println(date);
			Thread.sleep(5000);
			List<WebElement> gelements = driver.findElements(By.xpath("//p[@dir='ltr']"));
			System.out.println(gelements.size());
			List<String> al=new ArrayList<String>();
			for(int k=6;k<gelements.size();k++)
			{
				String t = gelements.get(k).getText();
				al.add(t);
			}
			driver.get("http://72.52.94.202:8990/eh/login");
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys("shubham1");
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Ganeshg@1994");
			driver.findElement(By.xpath("//button[@id='loginSubmit']")).click();
			try {
				driver.findElement(By.xpath("//div[text()='"+ date +"']")).click();

			}
			catch(Exception e)
			{	driver.findElement(By.xpath("(//div[text()='"+ date + "'])[2]")).click();
			}	
			Thread.sleep(5000);	
			List<WebElement> Fixeddates = driver.findElements(By.xpath("//td[@class='day' or @class='day borderless']"));
			System.out.println(Fixeddates.size());
			//	for(WebElement dd:Fixeddates)
			//	{
			//	System.out.println(dd.getText());
			//	}

			List<WebElement> Projectname = driver.findElements(By.xpath("//span[@class='projectName projectNameFilter']"));
			for(int i =0;i<Fixeddates.size();i++)
			{
				String z = Fixeddates.get(i).getText();
				String edate = z.substring(6, 10).trim();
				for(int w=0;w<Projectname.size();w++)
				{
					//	System.out.println(edate);
					//	System.out.println(date);
					if(Projectname.get(w).getText().equals("383media Reporting") && edate.contains(date))
					{
						System.out.println(i);
						i++;
						driver.findElement(By.xpath("(//span[text()='383media Reporting']/../..//td[@class='options']/a)["+i+"]")).click();
						i--;
						System.out.println(driver.findElement(By.xpath("(//textarea[@class='timesheetTextarea'])[2]")).getText());
						if(driver.findElement(By.xpath("(//textarea[@class='timesheetTextarea'])[2]")).getText().equals(""))
						{
							for(int l=0;l<al.size();l++) 
							{ 
								driver.findElement(By.xpath("(//textarea[@class='timesheetTextarea'])[2]")).sendKeys(al.get(l));
							}
							driver.findElement(By.xpath("//span[text()='ok']")).click();
							Thread.sleep(5000);
							driver.findElement(By.xpath("(//span[text()='store'])[1]")).click();
							driver.findElement(By.xpath("(//span[text()='store'])[2]")).click();
						}
						else
							System.out.println("Status already saved for this date" + str);
					}
				}
			}
		}
		else 
			System.out.println("Status mail not found");
	}
}