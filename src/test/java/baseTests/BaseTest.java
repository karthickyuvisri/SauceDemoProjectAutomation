package baseTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import pageObjects.LoginPage;

public class BaseTest {

	protected static Logger logger;
	public WebDriver driver;
	public LoginPage lp;
	public String url="https://www.saucedemo.com/";
	
	
	public void fileSetup() {
		
	}
	
	@BeforeMethod
	@Parameters({"browser","loc"})
	public void setup(String br,String loc) throws MalformedURLException {
		String remoteurl="http://192.168.205.112:4444/wd/hub";
		if(loc.equalsIgnoreCase("local")) {
		switch(br.toLowerCase()) {
		case "chrome":driver=new ChromeDriver(); break;
		case "edge" : driver=new EdgeDriver(); break;
		default :System.out.println("Invalid Browser Selection"); return;
		}
		}
		else if(loc.equalsIgnoreCase("remote")) {
			DesiredCapabilities cap=new DesiredCapabilities();
			cap.setBrowserName(br);
			cap.setPlatform(Platform.MAC);
			driver=new RemoteWebDriver(new URL(remoteurl),cap);
		}
		
		
		lp=new LoginPage(driver);
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
