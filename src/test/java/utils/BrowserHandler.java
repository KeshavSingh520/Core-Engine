package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;

@Getter
public class BrowserHandler{

	private  String strBrowser;
	private String strUrl;
	private static ThreadLocal<WebDriver> threadDriver= new ThreadLocal<>();
	private int implicitWait = 10;

	private enum browsersList {
		CHROME, FIREFOX, EDGE, SAFARI
	}

	public BrowserHandler(String browser,String url) {
		this.strUrl=url;
		this.strBrowser=browser;
		switch (browsersList.valueOf(browsersList.class, strBrowser.toUpperCase())) {
		case CHROME:
			threadDriver.set(createChromeDriver());
		case FIREFOX:
		case EDGE:
		case SAFARI:
		}
	}

	public WebDriver createChromeDriver() {
		WebDriver driver=null;
		if (driver == null) {
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
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
