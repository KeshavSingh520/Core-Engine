package com.tests;


import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.pageobjects.HomePage;
import com.pageobjects.LoginPage;

import base.BaseTestCase;
import common.AssertStep;
public class HomePageTest extends BaseTestCase{
	
	
	
	
	@Test
	public void testNavigationLogin(){
		HomePage homePage= PageFactory.initElements(driver, HomePage.class);
		AssertStep.assertStep(this, "Click login.");
		LoginPage loginPage=homePage.clickLogin();
		AssertStep.assertTrue(loginPage.isInputUserNameDisplayed(), this, "Login is displayed.");
		
	}
	
}
