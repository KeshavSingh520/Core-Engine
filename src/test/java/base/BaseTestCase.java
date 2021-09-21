package base;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentTest;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import report.ReportHandler;
import utils.BrowserHandler;
import utils.MobileHandler;

@Slf4j
@Getter
@Listeners({ listener.TestListener.class })
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class BaseTestCase extends AbstractTestNGSpringContextTests {

	@Value("${Url}")
	private String url;

	@Value("${MobileBrowser}")
	private String mobileBrowser;
	
	@Value("${Browser}")
	private String browser;
	@Value("${FILE_DOWNLOAD_PATH}")
	private String fileDownloadFolder;
	
	@Value("${Selenium_Grid_Hub}")
	private String strHub;
	
	@Value("${Selenium_Grid_Enabled}")
	private String strGridEnabled;

	public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
	public static ThreadLocal<Method> threadMethod = new ThreadLocal<>();
	public static ThreadLocal<ExtentTest> threadExtentTest = new ThreadLocal<>();
	public static WebDriver driver;
	public static BaseTestCase baseTestcase = new BaseTestCase();
	private String basePath = System.getProperty("user.dir");
	private String completeAbsolutePath;
	private String currentMethod;
	private int implicitWait = 10;
	private String toolType = "Android";

	private static ReportHandler reportHandler = null;
	public BrowserHandler browserHandler;
	public MobileHandler mobileHandler;

	@BeforeSuite
	public void start() {
		reportHandler = new ReportHandler();
		reportHandler.initiateReport();
		ReportHandler.extentReports.setSystemInfo("Browser", this.browser);

	}

	@BeforeMethod
	public void init(Method method) throws MalformedURLException {
		this.currentMethod = method.getName();
		threadMethod.set(method);

		log.info("Test Method Started" + " " + "[" + threadMethod.get().getName() + "]" + " " + "[" + "PASS" + "]");
		threadExtentTest.set(reportHandler.createTest(getClass().getName() + "." + threadMethod.get().getName()));
		browserHandler = new BrowserHandler(this.browser, this.url,strHub,this.strGridEnabled,this.mobileBrowser);
		threadDriver.set(BrowserHandler.getWebdriver());
		log.info(threadMethod.get().getName() + " " + ((RemoteWebDriver) threadDriver.get()).getSessionId().toString());
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		String status = result.getStatus() == ITestResult.SUCCESS ? "PASS"
				: result.getStatus() == ITestResult.FAILURE ? "FAIL" : "SKIP";
		log.info("Test Method Ended" + " " + "[" + threadMethod.get().getName() + "]" + " " + "[" + status + "]");
		threadDriver.get().close();
		threadDriver.get().quit();
		driver = null;
	}

	@AfterSuite
	public void end() throws IOException {
		reportHandler.endLogger();
	}

	public ExtentTest getExtentTestObj() {
		return reportHandler.getExtentTestObj();
	}

	public static BaseTestCase getBaseTestCase() {
		return baseTestcase;
	}

	public String getCurrentPackage() {
		return getClass().getName();
	}

	public static WebDriver getWebdriver() {
		return threadDriver.get();
	}

	public void setDriver(WebDriver driver) {
		threadDriver.set(driver);
	}
}
