package com.testvagrant.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testvagrant.model.WeatherModel;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NDTVWeatherPage extends BasePage {

	private final WebDriver driver;

	public NDTVWeatherPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='message']//input")
	private List<WebElement> checkBox;

	@FindBy(className = "cityText")
	private List<WebElement> mapContainer;

	@FindBy(xpath = "//div[@id='messages']")
	private List<WebElement> checkboxPanel;
	
	@FindBy(xpath = "//b[contains(text(),'Humidity')]")
	private WebElement humidity;

	@FindBy(xpath = "//b[contains(text(),'Temp in Fahrenheit')]")
	private WebElement fahrenheit;
	
	@FindBy(xpath = "//b[contains(text(),'Temp in Degrees')]")
	private WebElement degrees;

	@FindBy(xpath = "//*[contains(@href, 'close')]")
	private WebElement popClose;
	
	@FindBy(className = "leaflet-popup-content-wrapper")
	private WebElement mapPopup;
	
	private final String selectCityInCheckbox = "//div[@id='messages']//input[@id='%s']";
	
	private final String selectCityInMap = "//div[contains(text(),'%s')]";

	public String getPageTitle() {
		return driver.getTitle();
	}

	public ArrayList<String> getDefaultSelectedCitiesInCheckbox() {

		waitTillVisibilityOfAllElements(10, checkBox);

		return checkBox.stream().filter(element -> element.isSelected())
				.map(element -> element.getAttribute("id").trim())
				.collect(Collectors.toCollection(ArrayList<String>::new));
	}

	public ArrayList<String> getDefaultCitiesAppearedInMap() {

		waitTillVisibilityOfAllElements(10, mapContainer);

		return mapContainer.stream().map(element -> element.getText().trim())
				.collect(Collectors.toCollection(ArrayList<String>::new));
	}

	public NDTVWeatherPage unSelectAllCities() {
		waitTillVisibilityOfAllElements(10, checkBox);
		checkBox.stream().filter(element -> element.isSelected()).forEach(element -> element.click());
		return this;
	}

	public NDTVWeatherPage selectACityInCheckBox(String city) {
		waitTillVisibilityOfAllElements(5, checkboxPanel);
		driver.findElement(By.xpath(String.format(selectCityInCheckbox,city))).click();
		return this;
	}
	
	public NDTVWeatherPage clickOnACityInMap(String city) {
		waitTillVisibilityOfAllElements(5, mapContainer);
		driver.findElement(By.xpath(String.format(selectCityInMap,city))).click();
		return this;
	}
	
	public boolean checkWeatherElementsAreDisplayed(){
		waitTillVisibilityOfElement(3, mapPopup);
		return humidity.isDisplayed() && degrees.isDisplayed() && fahrenheit.isDisplayed(); 
	}

	public WeatherModel getTempDetailsAsWeatherObject() {
		Number hum=null, deg=null, far=null;
		
		waitTillVisibilityOfElement(3, mapPopup);
		
		String humTemp = humidity.getText().replaceAll("[^0-9\\.]", "").trim();
		String degTemp = degrees.getText().replaceAll("[^0-9\\.]", "").trim();
		String farTemp = fahrenheit.getText().replaceAll("[^0-9\\.]", "").trim();
		
		popClose.click();
		
		try {
			hum = NumberFormat.getInstance().parse(humTemp);
			deg = NumberFormat.getInstance().parse(degTemp);
			far = NumberFormat.getInstance().parse(farTemp);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new WeatherModel(hum,deg,far);

	}

}
