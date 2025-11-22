package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	static public WebDriver driver;
	public Properties p;
	
	@BeforeClass
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException, InterruptedException 
	{
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//Selecting Operating System
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else 
			{
				System.out.println("No matching os");
				return;
			}
			//Selecting browser
			switch(br.toLowerCase())
			{
			case "chrome" :
				capabilities.setBrowserName("chrome");
				break;
				
			case "edge" :
				capabilities.setBrowserName("MicrosoftEdge");
				break;
				
			case "firefox" :
				capabilities.setBrowserName("firefox");
				break;
				
			default : 
				System.out.println("Invalid browser name..."); 
				return;
			}
			
			URI uri = URI.create("http://10.232.46.40:4444/wd/hub");
			driver=new RemoteWebDriver(uri.toURL(),capabilities);
			
		}
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			//Selecting Browser
			switch(br.toLowerCase()) 
			{
			case "chrome" :
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
				break;
				
			case "edge" :
				driver=new EdgeDriver();
				break;
				
			default :
				System.out.println("Invalid browser name....");
				return;
			}
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));//Reading URL from properties file
		driver.manage().window().maximize();
		
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
}
