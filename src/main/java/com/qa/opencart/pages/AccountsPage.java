package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utilities.Constants;
import com.qa.opencart.utilities.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//1.locators
	private By logo = By.cssSelector("div#logo a");
	private By accountSectionsHeaders  = By.cssSelector("div#content h2");
	private By search = By.cssSelector("#search input");
	private By searchIcon = By.cssSelector("#search button");
	private By searchResults = By.cssSelector("div.product-thumb h4 a");
	
	//2. Constructor
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	//3. Page Actions
	public String verifyAccountsPageTitle() {
		return elementUtil.waitForPageTitle(Constants.ACCOUNTPAGE_TITLE, 5);
	}
	
	public String verifyAccountsPageLogo() {
		
		if(elementUtil.doIsDisplayed(logo)) {
			return elementUtil.doGetText(logo);
		} 
		return null;
	}

	public List<String> getAccountSectionList() {
		
		List<String> sectionsTextList = new ArrayList<>();
		List<WebElement> sectionsList = driver.findElements(accountSectionsHeaders);
		
		for (WebElement e : sectionsList) {
			String sectionText = e.getText();
			System.out.println(sectionText);
			sectionsTextList.add(sectionText);
		}
		return sectionsTextList;		
	}
	
	public boolean getSectionListCount() {
		return elementUtil.getElements(accountSectionsHeaders ).size()==Constants.ACCOUNTPAGE_SECTIONS_COUNT;
	}
	
	public boolean doSearch(String product) {
		elementUtil.doSendKeys(search,product);
		elementUtil.doClick(searchIcon);
		if(elementUtil.getElements(searchResults).size()>0){
			return true;
		}
		return false;
		
	}
	
	public ProductInfoPage selectProductFromResults(String productName) {
		List<WebElement> productResultList =  elementUtil.visibiblityOfAllElements(searchResults, Constants.ACCOUNTPAGE_TIMEOUT);
		for (WebElement e : productResultList) {
			String product = e.getText();
			if(product.equals(productName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
}
