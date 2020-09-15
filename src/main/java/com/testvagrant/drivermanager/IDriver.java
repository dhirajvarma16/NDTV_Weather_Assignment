package com.testvagrant.drivermanager;

import org.openqa.selenium.WebDriver;

public interface IDriver {
	
	public abstract void setup();
	public abstract void desiredCapabilities();
	public abstract void initialize();
	public abstract WebDriver getDriver();

}
