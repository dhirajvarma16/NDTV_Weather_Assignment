package com.testvagrant.drivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverManager implements IDriver {

	private final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private final ChromeOptions options = new ChromeOptions();
	
	// this class not available outside of this package, use DriverFactory class to create driver object
	ChromeDriverManager() {
		super();
	}

	public void setup() {
		WebDriverManager.chromedriver().setup();
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
		driver.set(new ChromeDriver(options));
	}

	public WebDriver getDriver() {
		desiredCapabilities();
		initialize();
		return driver.get();
	}

}
