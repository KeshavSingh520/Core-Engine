package com.tests;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.pageobjects.HomePage;
import com.pageobjects.LoginPage;

import base.BaseTestCase;
import common.AssertStep;

public class LoginTest extends BaseTestCase{
	
	@Test
	public void testLoginFunctionality(){
		HomePage homePage= PageFactory.initElements(driver, HomePage.class);
		AssertStep.assertStep(this, "Navigating to login page.");
		LoginPage loginPage = homePage.clickLogin();	
		AssertStep.assertStep(this, "Getting windows.");
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
	}

}
