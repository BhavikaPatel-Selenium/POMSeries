package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utilities.Constants;
import com.qa.opencart.utilities.ElementUtil;
import com.qa.opencart.utilities.JavaScriptUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	private JavaScriptUtil jsUtil;
	
	//1. By locators
	private By logo = By.cssSelector("div#logo a");
	private By cartButton = By.cssSelector("div#cart button");
	private By headerMenu = By.xpath("(//ul[contains(@class,'navbar-nav')]/li)/a[1]");
	private By regBreadcrumb = By.xpath("//ul[@class='breadcrumb']");
	private By regHeader =By.cssSelector("div#content h1");
	private By loginLink = By.xpath("//div[@id='content']//a[contains(text(),'login')]");
	private By sectionsList = By.xpath("//div[@class='list-group']/a");
	private By footerHeader = By.xpath("//div[@class='row']//h5");
	private By footerLinks = By.xpath("//div[@class='col-sm-3']//ul[@class='list-unstyled']//a");
	private By footerPowerText = By.xpath("(//div[@class='container']//p)[3]");
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By emailAddress = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	
	private By subscribeYes = By.xpath("//label[@class='radio-inline'][position()=1]/input");
	private By subscribeNo = By.xpath("//label[@class='radio-inline'][position()=2]/input");
	
	private By agreePolicy = By.xpath("//input[@name='agree' and @value='1']");
	private By continueBtn = By.xpath("//input[@value='Continue' and @type='submit']");
	
	private By accountSuccessMessg = By.cssSelector("#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	//2.Constructor
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
		jsUtil= new JavaScriptUtil(this.driver);
	}
	
	//3. Page Actions
	
	public String getRegPageTitle() {
		return elementUtil.waitForPageTitle(Constants.REGISTERPAGE_TITLE, 5);
	}
	
	public String getLogoHeaderValue() {
		if(elementUtil.doIsDisplayed(logo)) {
			return elementUtil.doGetText(logo);
		}
		return null;
	}
	
	public boolean isCartButtonDisplay() {
		return elementUtil.doIsDisplayed(cartButton);
	}
	
	public int getHeaderMenuCount() {
		return elementUtil.getPageElementsCount(headerMenu, 5);
	}
	
	public boolean isBreadcrumbDisplay() {
		return elementUtil.doIsDisplayed(regBreadcrumb);
	}
	
	public String getRegHeaderTextValue() {
		return elementUtil.doGetText(regHeader);
	}
	
	public boolean isLoginLinkDisplay() {
		return elementUtil.doIsDisplayed(loginLink);
	}
	
	public int getSectionsListCount() {
		return elementUtil.getPageElementsCount(sectionsList, 5);
	}
	
	public List<String> getSectionsList() {
		List<String> sectionsTextList = new ArrayList<>();
		System.out.println("Register page sections list: ");
		List<WebElement> sectionList =  elementUtil.getElements(sectionsList);
		for (WebElement e : sectionList) {
			String text = e.getText();
			System.out.println(text);
			sectionsTextList.add(text);
		}
		return sectionsTextList;
	}
	
	public boolean accountRegistration(String fn, String ln, 
			String email, String phone, String pwd,
			String conPwd, String subscribe) {
		
		elementUtil.doSendKeys(firstName,fn);
		elementUtil.doSendKeys(lastName,ln);
		elementUtil.doSendKeys(emailAddress, email);
		elementUtil.doSendKeys(telephone, phone);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doSendKeys(confirmPassword, conPwd);
		
		if (subscribe.equals("yes")) {
			elementUtil.doClick(subscribeYes);
		} else {
			elementUtil.doClick(subscribeNo);
		}
		
		elementUtil.doClick(agreePolicy);
		elementUtil.doClick(continueBtn);
		String text = elementUtil.doGetText(accountSuccessMessg);
		if(text.contains(Constants.REGISTER_SUCESSFUL_PAGE_TITLE)) {
			elementUtil.doClick(logoutLink);
			elementUtil.doClick(registerLink);

			return true;
		}
		return false;
	}
	
	
	public int getFooterHeaderCount() {
		return elementUtil.getPageElementsCount(footerHeader, 5);
	}
	public int getFooterLinksCount() {
		return elementUtil.getPageElementsCount(footerLinks, 5);
	}
	
	public List<String> getFooterLinksList() {
		List<String> footerTextList = new ArrayList<>();
		System.out.println("Footer links list: ");
		List<WebElement> footerList =  elementUtil.getElements(footerLinks);
		for (WebElement e : footerList) {
			String text = e.getText();
			System.out.println(text);
			footerTextList.add(text);
		}
		return footerTextList;
	}
	
	public boolean isfooterPowerTextDisplay() {
		jsUtil.scrollIntoView(elementUtil.getElement(footerPowerText));
		return elementUtil.doIsDisplayed(footerPowerText);
	}
	
	
	
}
