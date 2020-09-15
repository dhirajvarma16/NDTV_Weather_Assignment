package com.testvagrant.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;

import com.testvagrant.model.WeatherModel;
import com.testvagrant.utils.PropertyReader;

public class APIHelper {

	private PropertyReader property = new PropertyReader();

	private void setBaseURI() {
		property.readPropertiesFile("src/test/resources/config.properties");
		RestAssured.baseURI = property.getProperty("uri");
	}

	private RequestSpecification getRequest(HashMap<String, String> queryParams) {
		setBaseURI();
		return RestAssured.given().queryParams(queryParams);
	}

	public Response createRequest(String requestType, String path, HashMap<String, String> queryParams) {
		Response response = null;

		switch (requestType.toLowerCase().trim()) {
		case "get":
			response = getRequest(queryParams).get(path);
			break;

		case "post":
			response = getRequest(queryParams).post(path);
			break;

		default:
			break;
		}
		return response;
	}

	public WeatherModel jsonTOObject(Response metric, Response imperial) {
		Number hum = null, cel = null, far = null;

		String humTemp = metric.getBody().jsonPath().getString("main.humidity");
		String celTemp = metric.getBody().jsonPath().getString("main.temp");
		String farTemp = imperial.getBody().jsonPath().getString("main.temp");

		try {
			hum = NumberFormat.getInstance().parse(humTemp);
			cel = NumberFormat.getInstance().parse(celTemp);
			far = NumberFormat.getInstance().parse(farTemp);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new WeatherModel(hum, cel, far);
	}

}
