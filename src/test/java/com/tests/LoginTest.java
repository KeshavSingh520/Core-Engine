package com.tests;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pageobjects.HomePage;
import com.pageobjects.LoginPage;

import base.BaseTestCase;
import common.AssertStep;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginTest extends BaseTestCase{
	
	WebDriver driver;
	
	@BeforeMethod
	public void onStart() {
		driver=threadDriver.get();
	}
	
	@Test
	public void testLoginFunctionality() throws InterruptedException{
		HomePage homePage= PageFactory.initElements(driver, HomePage.class);
		AssertStep.assertStep(this, "Navigating to login page.");
		LoginPage loginPage = homePage.clickLogin();	
		AssertStep.assertStep(this, "Getting windows.");
		log.info("Login Test session id: "+((RemoteWebDriver)driver).getSessionId().toString());
		Set<String> setWindows=driver.getWindowHandles();
		AssertStep.assertStep(this, "Iterating over set of windows");
		Iterator<String> itr=setWindows.iterator();
		itr.next();
		String strWindow=itr.next();
		AssertStep.assertStep(this, strWindow);
		driver.switchTo().window(strWindow);
		AssertStep.assertTrue(loginPage.isInputUserNameDisplayed(), this, "Navigated to login page.");
		AssertStep.assertStep(this, "Click Login");
		loginPage.clickLogin();
		AssertStep.assertStep(this, "Accept alert.");
		loginPage.acceptAlert();
		Thread.sleep(10000);
	}

}
