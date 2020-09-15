package com.testvagrant.drivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxDriverManager implements IDriver {
	
	private final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private final FirefoxOptions options = new FirefoxOptions();

	// this class not available outside of this package, use DriverFactory class to create driver object
	FirefoxDriverManager() {
		super();
	}
	
	public void setup() {
		WebDriverManager.firefoxdriver().setup();
	}

	public void desiredCapabilities() {
		
		if (Boolean.getBoolean("headless")) {
			options.addArguments("--headless");
		}
		options.addArguments("start-maximized");
		options.addArguments("--incognito");
		options.addArguments("--disable-notifications");

	}

	public void initialize() {
		driver.set(new FirefoxDriver(options));

	}

	public WebDriver getDriver() {
		desiredCapabilities();
		initialize();
		return driver.get();
	}

}
