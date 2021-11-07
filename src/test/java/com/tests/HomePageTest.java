package com.tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pageobjects.HomePage;
import com.pageobjects.LoginPage;

import base.BaseTestCase;
import common.AssertStep;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HomePageTest extends BaseTestCase{
	
	WebDriver driver;
	
	@BeforeMethod
	public void onStart() {
		driver=threadDriver.get();
	}
	
	
	@Test(groups = "Regression")
	public void testNavigationLogin() throws InterruptedException{
		HomePage homePage= PageFactory.initElements(driver, HomePage.class);
		AssertStep.assertStep(this, "Click login.");
		LoginPage loginPage=homePage.clickLogin();
		AssertStep.assertTrue(loginPage.isInputUserNameDisplayed(), this, "Login is displayed.");
		log.info("navigation: "+((RemoteWebDriver)driver).getSessionId().toString());
	}
	
	@Test(groups = "Regression")
	public void testButtonClick() throws InterruptedException{
		HomePage homePage= PageFactory.initElements(driver, HomePage.class);
		AssertStep.assertStep(this, "Click button-clicks.");
		homePage.clickButton();
		log.info("button click: "+((RemoteWebDriver)driver).getSessionId().toString());
	}
	
}
