package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Bhavika Patel
 *
 */
public class DriverFactory {

	WebDriver driver;
	Properties prop;
	public static String highlight;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to initialize the webdriver on the basis of given browser
	 * name..
	 * 
	 * @param browserName
	 * @return
	 */
	public WebDriver init_driver(Properties prop) {

		String browserName = prop.getProperty("browser");
		System.out.println("browser name is: " + browserName);

		highlight = prop.getProperty("highlight").trim();
		optionsManager = new OptionsManager(prop);

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if (browserName.equals("safari")) {
			driver = new SafariDriver();
		} else {
			System.out.println("Please pass the correct browser name : " + browserName);
		}

		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();

		return getDriver();

	}

	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}

	/**
	 * This method is used to initialize the properties from config file.
	 * 
	 * @return returns Properties prop
	 */
	public Properties init_prop() {

		FileInputStream ip = null;
		prop = new Properties();
		String env = System.getProperty("env");
		System.out.println("Runnning on the environment: " + env);

		if (env == null) {
			try {
				ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				switch (env) {
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
				default:
					System.out.println("Please pass the correct environment");
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("File nout found at given place");
			} 
			
			try {
					prop.load(ip);
			} catch (FileNotFoundException e) {
					e.printStackTrace();
					System.out.println("File nout found at given place");
			} catch (IOException e) {
					e.printStackTrace();
			}
			return prop;
		}
		return prop;
	}

	/**
	 * To take screenshot
	 */

	public String getScreenshot() {
		// If you find cross icon instead of image in screenshots folder then use below
		// 2 lines
		// String src =
		// ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BASE64);
		// File srcFile = new File(src);

		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}