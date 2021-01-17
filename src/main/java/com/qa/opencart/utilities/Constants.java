package com.qa.opencart.utilities;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	public static final String LOGINPAGE_TITLE = "Account Login";
	public static final String ACCOUNTPAGE_TITLE = "My Account";
	public static final String ACCOUNTPAGE_LOGO = "Your Store";
	public static final int ACCOUNTPAGE_SECTIONS_COUNT = 4;
	public static final String REGISTERPAGE_TITLE = "Register Account";
	public static final String REGISTER_SUCESSFUL_PAGE_TITLE = "Your Account Has Been Created!";
	public static final String REGISTER_PAGE_LOGO_TEXT= "Your Store";
	public static final int REGISTER_HEADER_MENULIST_COUNT = 8;
	public static final int REGISTER_SECTION_LIST_COUNT = 13;
	public static final int REGISTER_FOOTER_HEADER_COUNT = 4;
	public static final int REGISTER_FOOTER_LINKS_COUNT = 15;
	public static final String PRODUCTINFOPAGE_WISHLIST_SUCCESS_MESSAGE = " Success: You have added ";
	public static final int PRODUCTINFOPAGE_TIMEOUT =10;
	public static final int PRODUCTINFOPAGE_POOLING_TIME = 1;
	public static final int ACCOUNTPAGE_TIMEOUT = 10;


	public  static List<String> accountHeaderSectionsList() {
		List<String> accountSectionList = new ArrayList<String>();
		accountSectionList.add("My Account");
		accountSectionList.add("My Orders");
		accountSectionList.add("My Affiliate Account");
		accountSectionList.add("Newsletter");
		return accountSectionList;
	}

	public  static List<String> regPageSectionsList() {
		List<String> regPageSectionList = new ArrayList<String>();
		regPageSectionList.add("Login");
		regPageSectionList.add("Register");
		regPageSectionList.add("Forgotten Password");
		regPageSectionList.add("My Account");
		regPageSectionList.add("Address Book");
		regPageSectionList.add("Wish List");
		regPageSectionList.add("Order History");
		regPageSectionList.add("Downloads");
		regPageSectionList.add("Recurring payments");
		regPageSectionList.add("Reward Points");
		regPageSectionList.add("Returns");
		regPageSectionList.add("Transactions");
		regPageSectionList.add("Newsletter");
		return regPageSectionList;
	}

}
