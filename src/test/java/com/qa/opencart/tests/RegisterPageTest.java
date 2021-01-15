package com.qa.opencart.tests;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utilities.Constants;
import com.qa.opencart.utilities.ExcelUtil;
import com.qa.opencart.utilities.RandomNumGenerate;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("Epic- 2- Feature Name: Register page of OpenCartdemo aplication")
@Story("User story 301: Design register page with different test cases")
public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void setup() {
		registerPage = loginPage.doClickRegBtn();
	}
	
	@Description("Verify register page title test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void verifyRegPageTitleTest() {
		String regTitle = registerPage.getRegPageTitle();
		System.out.println("Register Page title is: " + regTitle);
		Assert.assertEquals(regTitle, Constants.REGISTERPAGE_TITLE);
	}
	
	@Test(priority=2)
	@Description("Verify register page logo test")
	@Severity(SeverityLevel.MINOR)
	public void verifyRegPageLogoTextTest() {
		String regLogoText = registerPage.getLogoHeaderValue();
		System.out.println("Register Page logo text is: " + regLogoText);
		Assert.assertEquals(regLogoText, Constants.REGISTER_PAGE_LOGO_TEXT);
	}
	
	@Description("Verify register page Cart button display test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void cartButtonDisplayTest() {
		Assert.assertTrue(registerPage.isCartButtonDisplay());
	}
	
	@Description("Verify register page header menu count test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=4)
	public void headerMenuCountTest() {
		Assert.assertTrue(registerPage.getHeaderMenuCount()==Constants.REGISTER_HEADER_MENULIST_COUNT);
	}
	
	@Description("Verify register page breadcrumb display test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=5)
	public void breadcrumbDisplayTest() {
		Assert.assertTrue(registerPage.isBreadcrumbDisplay());
	}
	
	@Description("Verify login link display on the register page test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=6)
	public void loginLinkDisplayTest() {
		Assert.assertTrue(registerPage.isLoginLinkDisplay());
	}
	
	@Description("Verify register page section list count test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=7)
	public void sectionListCountTest() {
		Assert.assertTrue(registerPage.getSectionsListCount()==Constants.REGISTER_SECTION_LIST_COUNT);
	}
	
	@Description("Verify register page section list text test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=8)
	public void sectionListTextTest() {
		Assert.assertEquals(registerPage.getSectionsList(),Constants.regPageSectionsList());		
	}
	
	@Description("Verify register page footer header count test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=9)
	public void footerHeaderCountTest() {
		Assert.assertTrue(registerPage.getFooterHeaderCount()==Constants.REGISTER_FOOTER_HEADER_COUNT);
	}
	
	@Description("Verify register page footer links count test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=10)
	public void footerLinksCountTest() {
		Assert.assertTrue(registerPage.getFooterLinksCount()==Constants.REGISTER_FOOTER_LINKS_COUNT);
	}
	
	@Description("Verify register page footer links list test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=11)
	public void footerLinksListTextTest() {
		List<String> footerLinksText = registerPage.getFooterLinksList();
		for (String s : footerLinksText) {
			if(s.contains("Newsletter")) {
				System.out.println("Footer links text are correct");
				break;
			}
		}
	}
	
	@Description("Verify register page footer power text display test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=12)
	public void footerPowerTextDisplayTest() {
		Assert.assertTrue(registerPage.isfooterPowerTextDisplay());
	}
	
	@DataProvider
	public Object[][] getRegTestData() {
		Object[][] regTestData=  ExcelUtil.getExcelData("register");
		return regTestData;
	}
	
	@Description("Verify register feature test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(dataProvider="getRegTestData", priority=13)
	public void registerTest(String firstname, String lastname, String email, String phoneNumber,String pwd,String subscribe){	
		
		String randomNum = RandomNumGenerate.generateRandomNumber();		
		String emailId = email.concat(randomNum).concat("@yopmail.com");	
		
		System.out.println("Register with this email address: " + emailId);
		
		Assert.assertTrue(registerPage.accountRegistration(firstname, lastname,
				emailId,phoneNumber,pwd,pwd,subscribe));	
	}
}
