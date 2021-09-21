package utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.service.local.flags.ServerArgument;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;

@Getter
public class BrowserHandler {

	private String strBrowser;
	private String strUrl;
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
	private int implicitWait = 10;
	ChromeOptions cap = new ChromeOptions();
	public AppiumServiceBuilder builder;
	public AppiumDriverLocalService service;
	

	private enum browsersList {
		CHROME, FIREFOX, EDGE, SAFARI
	}

	public BrowserHandler(String browser, String url, String strhub, String strGridEnabled,String mobileBrowser) throws MalformedURLException {
		this.strUrl = url;
		this.strBrowser = browser;
		switch (browsersList.valueOf(browsersList.class, strBrowser.toUpperCase())) {
		case CHROME:
			threadDriver.set(createChromeDriver(strhub,strGridEnabled,mobileBrowser));
		case FIREFOX:
		case EDGE:
		case SAFARI:
		}
	}

	public WebDriver createChromeDriver(String strhub, String strGridEnabled,String mobileBrowser) throws MalformedURLException {
		WebDriver driver = null;
		if (driver == null) {
			System.setProperty("webdriver.chrome.silentOutput", "true");
			WebDriverManager.chromedriver().setup();
//			System.setProperty("webdriver.chrome.driver",
//					System.getProperty("user.dir") + "/chromedriver.exe");
			if (strGridEnabled.equalsIgnoreCase("Y")) {
				driver = new RemoteWebDriver(new URL(strhub), cap);
			} else if(mobileBrowser.equalsIgnoreCase("Y")){
				AppiumDriverLocalService service = startAppium();
				cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
				cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0.6");
				cap.setCapability(MobileCapabilityType.UDID, "cdaed149");
				cap.setCapability(MobileCapabilityType.DEVICE_NAME, "OnePlus3T");
				cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
				cap.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
				cap.setCapability("chromedriverExecutable", "D:\\Software\\Chrome93\\chromedriver.exe");
				driver = new RemoteWebDriver(new URL(service.getUrl().toString()),cap);
			}else {
				driver = new ChromeDriver();
			}
			driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
			driver.get(strUrl);
		}
		return driver;
	}
	
	@SuppressWarnings("deprecation")
	public WebDriver createFirefoxDriver(String strhub, String strGridEnabled) throws MalformedURLException {
		WebDriver driver = null;
		if (driver == null) {
			System.setProperty("webdriver.chrome.silentOutput", "true");
			WebDriverManager.firefoxdriver().setup();
			if (strGridEnabled.equalsIgnoreCase("Y")) {
				driver = new RemoteWebDriver(new URL(strhub), cap);
			} else {
				driver = new FirefoxDriver(cap);
			}
			driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
			driver.get(strUrl);
		}
		return driver;
	}

	@Test()
	public void get() {
		System.out.println(strUrl);
	}

	public void launchApplicationUrl(WebDriver driver) {
		driver.get(strUrl);
	}

	public static WebDriver getWebdriver() {
		return threadDriver.get();
	}
	
	public AppiumDriverLocalService startAppium() {
		if(service==null) {
		builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1");
		builder.usingAnyFreePort();
		builder.withArgument((ServerArgument)GeneralServerFlag.LOG_LEVEL,"info");
		service = AppiumDriverLocalService.buildService(builder);
		System.out.println("Starting Appium Service:  " + service.getUrl());
		service.start();
		}else {
			System.out.println("Appium Service already running:  " + service.getUrl());
		}
		return service;
	}

}
