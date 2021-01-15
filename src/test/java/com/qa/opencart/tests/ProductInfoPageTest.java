package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("Epic- 4- Feature Name: Product information page of OpenCartdemo aplication")
@Story("User story 303: Design Product information page with different test cases")
public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void accountsPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Description("Verify product info test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=1)
	public void verifyProductInfoTest() {
		
		String productName = "iMac";
		
		accountsPage.doSearch(productName);
		productInfoPage = accountsPage.selectProductFromResults(productName);
		
		Map<String, String> productInfoMap = productInfoPage.getProductInformation();

		System.out.println(productInfoMap);
		productInfoMap.forEach((k,v)->System.out.println(k+ " " + v));
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(productInfoMap.get("name"), productName);
		softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(productInfoMap.get("price"), "$100.00");
		softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 14");
		softAssert.assertAll();

	}
	
	@Description("Verify product info page title test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=2)
	public void verifyProductInfoPageTitleTest_iMac() {
		accountsPage.doSearch("iMac");
		productInfoPage = accountsPage.selectProductFromResults("iMac");
		Assert.assertEquals(productInfoPage.getProductInfoPageTitle("iMac"), "iMac");
	}
	
	@Description("Verify product info page images test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=3)
	public void verifyProductImageTest() {
		accountsPage.doSearch("iMac");
		productInfoPage = accountsPage.selectProductFromResults("iMac");
		Assert.assertTrue(productInfoPage.verifyResultImagesCount() == 3);
	}
	
	@Description("Verify Add to cart Button display test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4)
	public void verifyAddToCartBtntest() {
		accountsPage.doSearch("iMac");
		productInfoPage = accountsPage.selectProductFromResults("iMac");
		Assert.assertTrue(productInfoPage.isAddToCartbtnDisplay());
	}
	
	
	@Description("Verify write a review link display test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=5)
	public void verifyWriteAReviewLinktest() {
		accountsPage.doSearch("iMac");
		productInfoPage = accountsPage.selectProductFromResults("iMac");
		Assert.assertTrue(productInfoPage.verifyWriteAReviewLink());
	}
	
	@Description("Verify wish list button display test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=6)
	public void isWishListBtnDisplayTest() {
		accountsPage.doSearch("iMac");
		productInfoPage = accountsPage.selectProductFromResults("iMac");
		Assert.assertTrue(productInfoPage.isWishListBtnDisplay());
	}
	
	@Description("Verify success msg when product added in to the wishlist test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=7)
	public void verifyWishListMsgText() {
		accountsPage.doSearch("iMac");
		productInfoPage = accountsPage.selectProductFromResults("iMac");
		Assert.assertTrue(productInfoPage.verifyWishListSuccessMsg());
	}
	
	@Description("Verify Compare Product Button Display test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=8)
	public void verofyCompareProductBtnDisplayTest() {
		accountsPage.doSearch("iMac");
		productInfoPage = accountsPage.selectProductFromResults("iMac");
		Assert.assertTrue(productInfoPage.isCompareProductBtnDisplay());
	}
	
	@Description("Verify success msg when product added in to the compare product list test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=9)
	public void verifyCompareProductSuccessMsgTest() {
		accountsPage.doSearch("iMac");
		productInfoPage = accountsPage.selectProductFromResults("iMac");
		Assert.assertTrue(productInfoPage.verifyCompareProductSuccessMsg());
	}


}
