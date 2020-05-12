package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class LoginPage extends BasePage {

	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(id = "text")
	private WebElement inputUserName;

	@FindBy(id = "login-button")
	private WebElement btnLogin;

	public boolean isInputUserNameDisplayed() {
		return inputUserName.isDisplayed();
	}

	public void clickLogin() {
		btnLogin.click();
	}
	
	public void acceptAlert(){
		driver.switchTo().alert().accept();
	}

}
