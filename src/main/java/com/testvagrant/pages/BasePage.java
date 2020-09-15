package com.testvagrant.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import java.util.List;

public class BasePage {

	private final WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public NDTVHomePage goToNDTVHomePage(String URL) {
		driver.get(URL);
		return new NDTVHomePage(driver);
	}

	public JavascriptExecutor javascriptExecutor() {
		return (JavascriptExecutor) driver;
	}

	public WebDriverWait setExplicitWaitTimeOutAndWait(long timeOutInSeconds) {
		return new WebDriverWait(driver, timeOutInSeconds);
	}

	public void waitTillVisibilityOfElement(long timeOutInSeconds, WebElement elementToBeVisible) {
		setExplicitWaitTimeOutAndWait(timeOutInSeconds).until(visibilityOf(elementToBeVisible));
	}

	public void waitTillElementToBeClickable(long timeOutInSeconds, WebElement elementsToBeClickable) {
		setExplicitWaitTimeOutAndWait(timeOutInSeconds).until(elementToBeClickable(elementsToBeClickable));
	}
	
	public void waitTillVisibilityOfAllElements(long timeOutInSeconds, List<WebElement> elementsToBeVisible) {
		setExplicitWaitTimeOutAndWait(timeOutInSeconds).until(visibilityOfAllElements(elementsToBeVisible));
	}
	
	public void sleep(long milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void waitForJSandJQueryToLoad() {

		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					// no jQuery present
					return true;
				}
			}
		};

		// wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};

		setExplicitWaitTimeOutAndWait(30).until(and(jsLoad, jQueryLoad));

	}

	public void tearDown() {
		driver.quit();
	}

}
