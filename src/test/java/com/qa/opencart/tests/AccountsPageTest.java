package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utilities.Constants;
import com.qa.opencart.utilities.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic- 3- Feature Name: Account page of OpenCartdemo aplication")
@Story("User story 302: Design account page with different test cases")

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void setup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	@Description("Account feature test")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void verifyAccountPageTitleTest() {
		String accountsTitle = accountsPage.verifyAccountsPageTitle();
		System.out.println("Accounts Page title is: " + accountsTitle);
		Assert.assertEquals(accountsTitle, Constants.ACCOUNTPAGE_TITLE);
	}
	
	@Description("Account Page logo test")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void verifyAccountPageLogoTest() {
		String logoText= accountsPage.verifyAccountsPageLogo();
		Assert.assertEquals(logoText, Constants.ACCOUNTPAGE_LOGO);
	}
	
	@Description("Account page sections count test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void verifyAccountPageSectionCountTest() {
		Assert.assertTrue(accountsPage.getSectionListCount());
	}
	
	@Description("Verify Account page sections test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void verifyAccountPageSectionsTest() {
		Assert.assertEquals(accountsPage.getAccountSectionList(),Constants.accountHeaderSectionsList());;
	}
	
	
	@Description("Account search feature test with iMac")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 5)
	public void verifySearchTest_iMac() {
		Assert.assertTrue(accountsPage.doSearch("iMac"));
	}
	
	@DataProvider
	public Object[][] getsearchTestData() {
		return ExcelUtil.getExcelData("search product data");
	}
	
	@Description("Account search feature test with different products")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 6,dataProvider="getsearchTestData")
	public void verifySearchTest(String productName) {
		Assert.assertTrue(accountsPage.doSearch(productName));
	}
	
	
	
	@Description("Account search results test with iMac")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 7)
	public void verifyProductResultsTest() {
		accountsPage.doSearch("iMac");
		accountsPage.selectProductFromResults("iMac");
	}
}
