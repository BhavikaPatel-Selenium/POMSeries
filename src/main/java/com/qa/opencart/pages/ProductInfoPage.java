package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utilities.Constants;
import com.qa.opencart.utilities.ElementUtil;
import com.qa.opencart.utilities.JavaScriptUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	private JavaScriptUtil jsUtil;
	
	//By locators
	private By addTocartBtn = By.cssSelector("#cart button");
	private By productNameHeader = By.cssSelector("#content h1");
	private By productImages = By.cssSelector("#content ul li img");
	private By productMetaData = By.cssSelector("#content .list-unstyled:nth-of-type(1) li");
	private By productQuantity = By.xpath("//input[@id='input-quantity']");
	private By writeReview = By.xpath("(//div[@class='rating']//a)[2]");
	private By wishListBtn = By.xpath("(//button[@type='button']/i[contains(@class,'fa-heart')])[1]");
	private By wishListSucessMessage = By.cssSelector("#product-product div a[href*='wish']");
	private By compareProduct = By.xpath("(//button[@type='button' and @data-original-title='Compare this Product'])[1]");
	private By compareSuccessMsg = By.cssSelector("div.alert a:nth-of-type(2)");
	private By productPrice = By.cssSelector("#content .list-unstyled:nth-of-type(2) li");
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
		jsUtil = new JavaScriptUtil(this.driver);
	}
	
	public int verifyResultImagesCount() {
		int imageCount = elementUtil.getPageElementsCount(productImages, Constants.PRODUCTINFOPAGE_TIMEOUT);
		System.out.println("Image count is: " + imageCount);
		return imageCount;
	}
	
	public Map<String, String> getProductInformation() {
		
		Map<String,String> productInfoMap= new HashMap<String,String>();
		
		productInfoMap.put("name", elementUtil.doGetText(productNameHeader).trim());
		List<WebElement> productMetaDataList = elementUtil.getElements(productMetaData);
		//Brand: Apple
		//Product Code: Product 14
		//Availability: Out Of Stock
		for (WebElement e : productMetaDataList) {
			String[] metaData = e.getText().split(":");
			String metaKey = metaData[0].trim();
			String metaValue = metaData[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
		//price
		List<WebElement> productPriceList = elementUtil.getElements(productPrice);

		productInfoMap.put("price", productPriceList.get(0).getText().trim());
		productInfoMap.put("exTaxPrice", productPriceList.get(1).getText().split(":")[1].trim());
		
		return productInfoMap;
	}
	public boolean isAddToCartbtnDisplay() {
		return elementUtil.getElement(addTocartBtn).isDisplayed();
	}
	
	public void selectQuantity(String quantity) {
		elementUtil.doSendKeys(productQuantity, quantity);
	}
	
	public boolean verifyWriteAReviewLink() {
		jsUtil.scrollIntoView(elementUtil.getElement(writeReview));
		return elementUtil.doIsDisplayed(writeReview);
	}
	
	public boolean isWishListBtnDisplay() {
		return elementUtil.getElement(wishListBtn).isDisplayed();
	}
	
	public boolean verifyWishListSuccessMsg() {
		elementUtil.actionsClickWhenReady(wishListBtn, Constants.PRODUCTINFOPAGE_TIMEOUT);
		String successText = elementUtil.waitForElementWithFluentWait(wishListSucessMessage,Constants.PRODUCTINFOPAGE_TIMEOUT,Constants.PRODUCTINFOPAGE_POOLING_TIME).getText();
		jsUtil.scrollIntoView(elementUtil.getElement(wishListSucessMessage));
		
		System.out.println(successText);
		if(successText.contains("wish list")) {
			return true;
		}
		return false;
	}
	
	public boolean isCompareProductBtnDisplay() {
		return elementUtil.getElement(compareProduct).isDisplayed();
	}

	public boolean verifyCompareProductSuccessMsg() {
	
	elementUtil.actionsClickWhenReady(compareProduct, Constants.PRODUCTINFOPAGE_TIMEOUT);
	String successText = elementUtil.waitForElementWithFluentWait
			(compareSuccessMsg,Constants.PRODUCTINFOPAGE_TIMEOUT,Constants.PRODUCTINFOPAGE_POOLING_TIME).getText();
	
	System.out.println(successText);
		if(successText.contains("product comparison")) {
			return true;
		}
		return false;
	}
	
	public String getProductInfoPageTitle(String productName) {
		String title = elementUtil.waitForPageTitleToBe(productName,Constants.PRODUCTINFOPAGE_TIMEOUT);
		System.out.println("Product Page title is : " + title);
		return title;
	}
}
