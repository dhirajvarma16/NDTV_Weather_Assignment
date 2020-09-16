package com.testvagrant.testcases;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.testvagrant.comparator.IWeatherComparator;
import com.testvagrant.model.VarianceModel;
import com.testvagrant.model.WeatherModel;
import com.testvagrant.utils.PropertyReader;

public class WeatherComparator {

	private PropertyReader property = new PropertyReader();

	@DataProvider(name = "Cities")
	public Object[] cityNamesProvider() {
		return new Object[] { "Ahmedabad", "Mumbai", "Chennai" };
	}

	@Test(dataProvider = "Cities")
	public void compareWeatherDetailsOfApiAndUiWithVariance(String city) {
		property.readPropertiesFile("src/test/resources/comparator.properties");

		HashMap<String, WeatherModel> uiWeatherDetails = NDTVWeatherPageTest.uiObjects();
		HashMap<String, WeatherModel> apiWeatherDetails = OpenWeatherAPITest.apiObjects();
		VarianceModel variance = new VarianceModel(Integer.parseInt(property.getProperty("humidityVariance")),
				Integer.parseInt(property.getProperty("celsiusVariance")),
				Integer.parseInt(property.getProperty("fahrenheitVariance")));

		IWeatherComparator weatherComparator = (uiObj, apiObj, varObj) -> {

			MathContext mtx = new MathContext(4);
			BigDecimal humidityResult = new BigDecimal(uiObj.getHumidity().floatValue())
					.subtract(new BigDecimal(apiObj.getHumidity().floatValue())).round(mtx);

			if (humidityResult.abs().floatValue() > varObj.getHumidity()) {
				Assert.fail("Humidity difference of UI and API is greater." + " " + "Humidity: " + humidityResult.abs()
						+" " + "Maximum variance allowed is: " + varObj.getHumidity());
			}

			BigDecimal celsiusResult = new BigDecimal(uiObj.getTempInDegrees().floatValue())
					.subtract(new BigDecimal(apiObj.getTempInDegrees().floatValue())).round(mtx);

			if (celsiusResult.abs().floatValue() > varObj.getTempInDegrees()) {
				Assert.fail("Celsius difference of UI and API is greater."+ " " + "Celsius: " + celsiusResult.abs()
						+ " "+ "Maximum variance allowed is: " + varObj.getTempInDegrees());
			}

			BigDecimal fahrenheitResult = new BigDecimal(uiObj.getTempInFahrenheit().floatValue())
					.subtract(new BigDecimal(apiObj.getTempInFahrenheit().floatValue())).round(mtx);

			if (fahrenheitResult.abs().floatValue() > varObj.getTempInFahrenheit()) {
				Assert.fail("Fahrenheit difference of UI and API is greater." + " "+ "Fahrenheit: " + fahrenheitResult.abs()
						+" "+ "Maximum variance allowed is: " + varObj.getTempInFahrenheit());
			}

		};

		weatherComparator.compare(uiWeatherDetails.get(city), apiWeatherDetails.get(city), variance);

	}

}
