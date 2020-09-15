package com.testvagrant.model;

public class WeatherModel {

	private Number humidity;
	private Number tempInDegrees;
	private Number tempInFahrenheit;
	
	public WeatherModel(Number humidity, Number tempInDegrees, Number tempInFahrenheit) {
		this.humidity = humidity;
		this.tempInDegrees = tempInDegrees;
		this.tempInFahrenheit = tempInFahrenheit;
	}
	
	public Number getHumidity() {
		return humidity;
	}
	public void setHumidity(Number humidity) {
		this.humidity = humidity;
	}
	public Number getTempInDegrees() {
		return tempInDegrees;
	}
	public void setTempInDegrees(Number tempInDegrees) {
		this.tempInDegrees = tempInDegrees;
	}
	public Number getTempInFahrenheit() {
		return tempInFahrenheit;
	}
	public void setTempInFahrenheit(Number tempInFahrenheit) {
		this.tempInFahrenheit = tempInFahrenheit;
	}
	
}
