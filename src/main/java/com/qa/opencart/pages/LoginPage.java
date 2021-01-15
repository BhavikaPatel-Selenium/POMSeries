package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utilities.Constants;
import com.qa.opencart.utilities.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	//1. By locator / OR
	
	By userName = By.id("input-email");
	By password = By.id("input-password");
	By loginBtn = By.xpath("//input[@type='submit']");
	By forgotPwdLink = By.cssSelector("div.form-group a");
	By registerBtn = By.xpath("//a[text()='Continue']");
	
	//2. Constructor of page class
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	//3.Page Actions
	@Step("Get login page title")
	public String getLoginPageTitle() {
		return elementUtil.waitForPageTitle(Constants.LOGINPAGE_TITLE, 5);
	}
	
	@Step("Checking forgot passwork link exist")
	public boolean isForgotPwdLinkExist() {
		//return driver.findElement(forgotPwdLink).isDisplayed();
		return elementUtil.doIsDisplayed(forgotPwdLink);
	}
	
	@Step("Trying to do login with username: {0} and password: {1}")
	public AccountsPage doLogin(String un, String pw) {
//		driver.findElement(userName).sendKeys(un);
//		driver.findElement(password).sendKeys(pw);
//		driver.findElement(loginBtn).click();
		elementUtil.doSendKeys(userName, un);
		elementUtil.doSendKeys(password, pw);
		elementUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	public RegisterPage doClickRegBtn() {
		elementUtil.doClick(registerBtn);
		return new RegisterPage(driver);
	}
			
}
