package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utilities.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

//@Listeners(ExtentReportListener.class)
@Epic("Epic- 1- Feature Name: Login page of OpenCartdemo aplication")
@Story("User story 300: Design login page with different test cases")
public class LoginPageTest extends BaseTest {
	
	@Test(priority=1)
	@Description("Login page title test")
	@Severity(SeverityLevel.MINOR)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login page title: " +title );
		Assert.assertEquals(title, Constants.LOGINPAGE_TITLE);
	}
	
	@Test(priority=2)
	@Description("Forgot password link test")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyOfrgotPwdlinkTest() {
		System.out.println("Verifying forgot password link test");
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}

	@Test(priority=3)
	@Description("Login feature test")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountsPage.verifyAccountsPageTitle(), Constants.ACCOUNTPAGE_TITLE);
		
	}
	

}
