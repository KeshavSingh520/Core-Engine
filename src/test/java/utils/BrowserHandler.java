package utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;

@Getter
public class BrowserHandler{

	private  String strBrowser;
	private String strUrl;
	private static ThreadLocal<WebDriver> threadDriver= new ThreadLocal<>();
	private int implicitWait = 10;
	ChromeOptions cap = new ChromeOptions();

	private enum browsersList {
		CHROME, FIREFOX, EDGE, SAFARI
	}

	public BrowserHandler(String browser,String url,String strhub) throws MalformedURLException {
		this.strUrl=url;
		this.strBrowser=browser;
		switch (browsersList.valueOf(browsersList.class, strBrowser.toUpperCase())) {
		case CHROME:
			threadDriver.set(createChromeDriver(strhub));
		case FIREFOX:
		case EDGE:
		case SAFARI:
		}
	}

	public WebDriver createChromeDriver(String strhub) throws MalformedURLException {
		WebDriver driver=null;
		if (driver == null) {
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			WebDriverManager.chromedriver().setup();
			driver = new RemoteWebDriver(new URL(strhub),cap);
			driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
			driver.get(strUrl);
		}
		return driver;
	}
	
	@Test()
	public void get(){
		System.out.println(strUrl);
	}
	
	public void launchApplicationUrl(WebDriver driver) {
		driver.get(strUrl);
	}
	
	public static WebDriver getWebdriver(){
		return threadDriver.get();
	}
	

}
