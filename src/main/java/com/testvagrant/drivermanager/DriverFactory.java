package com.testvagrant.drivermanager;

import org.testng.annotations.BeforeSuite;

public class DriverFactory {

	public static IDriver getBrowser(String browser) {
		
		String os = System.getProperty("os.name").toLowerCase();

		if (browser == null || browser.isEmpty()) {
			return new ChromeDriverManager();
		} else if (browser.equalsIgnoreCase("chrome")) {
			return new ChromeDriverManager();
		} else if (browser.equalsIgnoreCase("firefox")) {
			return new FirefoxDriverManager();
		} else if (browser.equalsIgnoreCase("ie") && os.contains("windows")) {
			return new InternetExplorerDriverManager();
		} else if (browser.equalsIgnoreCase("safari") && os.contains("mac")) {
			return new SafariDriverManager();
		}

		return new ChromeDriverManager();
	}

	@BeforeSuite
	private void browserSelection() {
		DriverFactory.getBrowser(System.getProperty("browser")).setup();
	}

}
