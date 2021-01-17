package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;

public class SignUpPage {
	
	WebDriver driver;
	public SignUpPage(WebDriver driver) {
		this.driver = driver;
		System.out.println("Sign up constructor");
	}

	public void SignUp() {
		System.out.println("Sign up method");
	}

}
