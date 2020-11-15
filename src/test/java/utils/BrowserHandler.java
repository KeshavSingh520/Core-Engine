package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;

@Getter
public class BrowserHandler {

	private String strBrowser;
	private String strUrl;
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
	private int implicitWait = 10;

	private enum browsersList {
		CHROME, FIREFOX, EDGE, SAFARI
	}

	public BrowserHandler(String browser, String url) {
		this.strUrl = url;
		this.strBrowser = browser;
		switch (browsersList.valueOf(browsersList.class, strBrowser.toUpperCase())) {
		case CHROME:
			threadDriver.set(createChromeDriver());
			break;
		case FIREFOX:
			threadDriver.set(createFireFoxDriver());
			break;
		case EDGE:
			threadDriver.set(creatEdgeDriver());
			break;
		case SAFARI:
			break;
		default:
		}
	}

	public WebDriver createChromeDriver() {
		WebDriver driver = null;
		if (driver == null) {
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
			driver.get(strUrl);
		}
		return driver;
	}

	public WebDriver createFireFoxDriver() {
		WebDriver driver = null;
		if (driver == null) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
			driver.get(strUrl);
		}
		return driver;
	}

	public WebDriver creatEdgeDriver() {
		WebDriver driver = null;
		if (driver == null) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
			driver.get(strUrl);
		}
		return driver;
	}

	public void launchApplicationUrl(WebDriver driver) {
		driver.get(strUrl);
	}

	public static WebDriver getWebdriver() {
		return threadDriver.get();
	}

}
