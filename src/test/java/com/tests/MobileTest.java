package com.tests;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import base.BaseTestCase;
import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;
import utils.MobileHandler;

@Slf4j
public class MobileTest extends BaseTestCase{
	
	AndroidDriver<WebElement> driver;
	String text = "Terms";

	String xpathOk = "//android.widget.Button[@text='OK']";
	String checkbox = "new UiScrollable(new UiSelector().scrollable(true))" +
	        ".scrollIntoView(new UiSelector().text(\"Label\"))";
	@Test
	public void testMobile() throws InterruptedException{
		 driver = new MobileHandler().init();
		 driver.findElement(By.xpath(xpathOk)).click();
		 driver.findElement(By.id("buttonStartWebview")).click();
		 driver.findElement(By.id("spinner_webdriver_test_data")).click();
		 driver.findElement(By.xpath("//android.widget.TextView[@text='formPage']")).click();
		 System.out.println("Text is: "+driver.findElement(By.xpath("//android.widget.CheckBox")).getAttribute("text"));
//		 JavascriptExecutor jse = (JavascriptExecutor)driver;
//		 jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//android.widget.CheckBox")));
	}
	
	@Test
	public void testChrome() throws InterruptedException{
		threadDriver.get().findElement(By.name("q")).sendKeys("Appium",Keys.ENTER);
		 JavascriptExecutor jse = (JavascriptExecutor)threadDriver.get();
		 jse.executeScript("arguments[0].scrollIntoView();",threadDriver.get().findElement(By.linkText("Terms")));
	}
}
