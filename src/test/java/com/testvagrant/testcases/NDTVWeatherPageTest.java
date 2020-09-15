package com.testvagrant.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import com.testvagrant.drivermanager.DriverFactory;
import com.testvagrant.model.WeatherModel;
import com.testvagrant.pages.BasePage;
import com.testvagrant.utils.PropertyReader;

public class NDTVWeatherPageTest {

	private BasePage basepage;
	private PropertyReader property = new PropertyReader();
	static HashMap<String, WeatherModel> weatherUiObj = new HashMap<String, WeatherModel>();

	@BeforeMethod
	public void createDriver() {
		basepage = new BasePage(DriverFactory.getBrowser(System.getProperty("browser")).getDriver());
		property.readPropertiesFile("src/test/resources/config.properties");
	}

	@Test
	public void verifyTitle() {
		String title = basepage.goToNDTVHomePage(property.getProperty("url")).goToWeatherPage().getPageTitle();

		assertThat(title).contains("NDTV Weather");
	}

	@Test
	public void checkDefaultSelectedCitiesInCheckbox() {
		ArrayList<String> citiesInCheckbox = basepage.goToNDTVHomePage(property.getProperty("url")).goToWeatherPage()
				.getDefaultSelectedCitiesInCheckbox();

		assertThat(citiesInCheckbox).isNotNull().contains("Bengaluru", "Bhopal", "Chennai", "Hyderabad", "Kolkata",
				"Lucknow", "Mumbai", "New Delhi", "Patna", "Srinagar", "Visakhapatnam");
	}

	@Test
	public void checkDefaultCitiesAppearedInMap() {
		ArrayList<String> citiesInMap = basepage.goToNDTVHomePage(property.getProperty("url")).goToWeatherPage()
				.getDefaultCitiesAppearedInMap();

		assertThat(citiesInMap).isNotNull().contains("Bengaluru", "Bhopal", "Chennai", "Hyderabad", "Kolkata",
				"Lucknow", "Mumbai", "New Delhi", "Patna", "Srinagar", "Visakhapatnam");
	}

	@Test
	public void compareCitiesInCheckboxWithMap() {
		ArrayList<String> citiesInCheckbox = basepage.goToNDTVHomePage(property.getProperty("url")).goToWeatherPage()
				.getDefaultSelectedCitiesInCheckbox();
		ArrayList<String> citiesInMap = basepage.goToNDTVHomePage("http://ndtv.com/").goToWeatherPage()
				.getDefaultCitiesAppearedInMap();

		assertThat(citiesInCheckbox).isEqualTo(citiesInMap);
	}

	@DataProvider(name = "Cities")
	public Object[] cityNamesProvider() {
		return new Object[] { "Ahmedabad", "Mumbai", "Chennai" };
	}

	@Test(dataProvider = "Cities")
	public void checkWeatherElementsAreDisplayedForACity(String city) {
		Boolean result = basepage.goToNDTVHomePage(property.getProperty("url")).goToWeatherPage().unSelectAllCities()
				.selectACityInCheckBox(city).clickOnACityInMap(city).checkWeatherElementsAreDisplayed();

		assertTrue(result);
	}

	@Test(dataProvider = "Cities")
	public void validateWeatherDetailsDisplayedForACity(String city) {
		WeatherModel weatherObj = basepage.goToNDTVHomePage(property.getProperty("url")).goToWeatherPage().unSelectAllCities()
				.selectACityInCheckBox(city).clickOnACityInMap(city).getTempDetailsAsWeatherObject();
		
		weatherUiObj.put(city, weatherObj);

		assertThat(weatherObj).isNotNull().matches(element -> element.getHumidity().floatValue() >= 0
				&& element.getTempInDegrees().floatValue() >= 0 && element.getTempInFahrenheit().floatValue() >= 0);
	} 
	
	@Test
	public static HashMap<String, WeatherModel> uiObjects() {
		return weatherUiObj;
	}
	
	@AfterMethod
	public void driverQuit() {
		basepage.tearDown();
	}
}
