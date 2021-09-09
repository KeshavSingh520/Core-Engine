package utils;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.service.local.flags.ServerArgument;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MobileHandler {
	
	
	public static ThreadLocal<AndroidDriver<WebElement>> threadMobileTool;
	public String mobileDriver;
	DesiredCapabilities cap = new DesiredCapabilities();
	public AppiumServiceBuilder builder;
	public AppiumDriverLocalService service;
	
	public AppiumDriverLocalService startAppium() {
		if(service==null) {
		builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1");
		builder.usingAnyFreePort();
		builder.withArgument((ServerArgument)GeneralServerFlag.LOG_LEVEL,"info");
		service = AppiumDriverLocalService.buildService(builder);
		log.info("Starting Appium Service:  " + service.getUrl());
		service.start();
		}else {
			log.info("Appium Service already running:  " + service.getUrl());
		}
		return service;
	}
	
	private enum toolList {
		ANDROID,IOS
	}

	
	/**
	 * @return
	 */
	public AndroidDriver<WebElement> createAndroidDriver() {
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0.6");
		cap.setCapability(MobileCapabilityType.UDID, "cdaed149");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "OnePlus3T");
		cap.setCapability(MobileCapabilityType.APP, "D:\\Software\\selendroid-test-app-0.17.0.apk");
		cap.setCapability("appPackage", "io.selendroid.testapp");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		cap.setCapability("appActivity", "io.selendroid.testapp.HomeScreenActivity");
		cap.setCapability(MobileCapabilityType.FULL_RESET,true);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
//		cap.setCapability("avd", "Nexus4");
		AndroidDriver<WebElement> androidDriver = null;
		try {
			log.info("Initiating android driver");
//			System.setProperty("chromedriverExecutable",
//					System.getProperty("user.dir") + "/chromedriver.exe");
			androidDriver = new AndroidDriver<WebElement>(service.getUrl(),cap);
			log.info("Session id: " + androidDriver.getSessionId());
//			threadMobileTool.set(androidDriver);
			androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			androidDriver.setLogLevel(Level.INFO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return androidDriver;
	}
	
	public static AndroidDriver<WebElement> getThreadMobileTool() {
		return threadMobileTool.get();
	}
	
	public AndroidDriver<WebElement> init() {
		if(startAppium()!=null) {
			return createAndroidDriver();
		}
		return null;
	}
	
	public void closeAppium() {
		if(service==null) {
			service.stop();
		}
	}
	
	

}
