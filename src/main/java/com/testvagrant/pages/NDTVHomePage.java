package com.testvagrant.pages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NDTVHomePage extends BasePage {

	private final WebDriver driver;

	public NDTVHomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "h_sub_menu")
	private WebElement submenu;

	@FindBy(linkText = "WEATHER")
	private WebElement weatherMenu;
	
	@FindBy(xpath = "//*[text()='No Thanks']")
	WebElement popup;

	public NDTVWeatherPage goToWeatherPage() {

		try {
			submenu.click();
			weatherMenu.click();
		} catch (ElementClickInterceptedException e) {
			popup.click();
			//waitTillElementToBeClickable(2, subMenu);
			submenu.click();
			weatherMenu.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new NDTVWeatherPage(driver);
	}

}
