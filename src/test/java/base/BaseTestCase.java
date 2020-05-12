package base;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import report.ReportHandler;
import utils.BrowserHandler;

@Slf4j
@Getter
@Listeners({listener.TestListener.class})
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class BaseTestCase extends AbstractTestNGSpringContextTests {
	public static WebDriver driver;
	public static BaseTestCase baseTestcase= new BaseTestCase();
	@Value("${Url}")
	private String url;
	@Value("${Browser}")
	private String browser;
	
	private String currentMethod;

	private int implicitWait = 10;

	private static ReportHandler reportHandler = null;
	public static String strPath = System.getProperty("user.dir")+ "\\test-output\\reports\\"+"screenshot.png";
	public BrowserHandler browserHandler;
	public static DesiredCapabilities cap= new DesiredCapabilities();
	ChromeOptions options = new ChromeOptions();
	@BeforeSuite
	public void start(){
		reportHandler = new ReportHandler();
		reportHandler.initiateReport();
		ReportHandler.extentReports.setSystemInfo("Browser", this.browser);
	}
	
	@BeforeMethod
	public void init(Method method) {
		this.currentMethod=method.getName();
		log.info("Test Method Started"+" "+"["+this.currentMethod+"]"+" "+"["+"PASS"+"]");
		reportHandler.createTest(getClass().getName()+"."+method.getName());
	//	this.browserHandler=new BrowserHandler(this.browser);
		//this.browserHandler.createUIDriver(driver);
		if (driver == null) {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(options);
			} else if (browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}

			driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
			driver.get(url);
		}
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		reportHandler.endLogger();
		String status= result.getStatus()==1?"PASS":result.getStatus()==2?"FAIL":"SKIP";
		log.info("Test Method Ended"+" "+"["+this.currentMethod+"]"+" "+"["+status+"]");	
		driver.quit();
		driver=null;
	}
	
	public ExtentTest getExtentTestObj(){
		return reportHandler.getExtentTestObj();
	}


	public static WebDriver getDriver() {
		return driver;
	}
	
	public static BaseTestCase getBaseTestCase(){
		return baseTestcase;
	}
	
	public String getCurrentPackage(){
		return getClass().getName();
	}
}
