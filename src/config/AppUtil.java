package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {
	public static WebDriver driver;
    public static Properties conpro;
    @SuppressWarnings("deprecation")
	@BeforeTest
    public static void setUp() throws Throwable, IOException {
    	conpro = new Properties();
    	conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
    	if(conpro.getProperty("browser").equalsIgnoreCase("chrome")) { 
    		driver = new ChromeDriver();
    		driver.manage().window().maximize();
    		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	}else if(conpro.getProperty("browser").equalsIgnoreCase("firefox"))
    	{
    		driver = new FirefoxDriver();
    	}
    	else {
    		Reporter.log("The browser is not available");
    	}
    }
    @AfterTest
    public static void tearDown() {
    	driver.quit();
    	
    }
}
