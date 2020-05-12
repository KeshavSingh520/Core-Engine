package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;

@Getter
public class BrowserHandler {

	private String strBrowser;
	private WebDriver driver;
	private int implicitWait = 10;

	private enum browsersList {
		CHROME, FIREFOX, EDGE, SAFARI
	}

	public BrowserHandler(String strBrowser) {
		this.strBrowser = strBrowser;
		browsersList browser = browsersList.valueOf(browsersList.class, strBrowser);
              
		switch (browser) {
		case CHROME:
			createUIDriver(driver);
		case FIREFOX:
		case EDGE:
		case SAFARI:
		}
	}

	public void createUIDriver(WebDriver driver) {
		if (driver == null) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
		}
	}
	
	public static void main(String[] args) {
		browsersList browser = browsersList.valueOf(browsersList.class, "CHROME");
		System.out.println(browser);
	}
	

}
