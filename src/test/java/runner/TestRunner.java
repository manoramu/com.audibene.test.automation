package runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features = "src/test/java/features"
		, glue = "stepdefinitions"
		)
public class TestRunner extends AbstractTestNGCucumberTests {
	private static final ThreadLocal<WebDriver> drivers = new ThreadLocal<>();
	
	public static WebDriver getDriver() {
		return drivers.get();
	}
	
	@BeforeClass
    @Parameters({"browser"})
    public void setUpClass(String browser) {
		WebDriver driver = null;
		String path = System.getProperty("user.dir");
        if(browser.equalsIgnoreCase("chrome")) {
        	System.setProperty("webdriver.chrome.driver", path + 
    				"//src//drivers//chromedriver.exe");
    		driver = new ChromeDriver();
        } else if(browser.equalsIgnoreCase("firefox")) {
        	System.setProperty("webdriver.gecko.driver", path + 
        			"//src//drivers//geckodriver.exe");
    		driver = new FirefoxDriver();
        } 
        drivers.set(driver);
        super.setUpClass();
    }
	
	@AfterClass
    public void tearDownClass() {
		getDriver().quit();
		drivers.remove();
        super.tearDownClass();
    }
	
}