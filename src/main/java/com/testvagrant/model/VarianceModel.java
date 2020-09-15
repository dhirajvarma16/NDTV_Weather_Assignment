package com.testvagrant.model;

public class VarianceModel {
	
	private int humidity;
	private int tempInCelsius;
	private int tempInFahrenheit;
	
	public VarianceModel(int humidity, int tempInCelsius, int tempInFahrenheit) {
		this.setHumidity(humidity);
		this.setTempInDegrees(tempInCelsius);
		this.setTempInFahrenheit(tempInFahrenheit);
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public int getTempInDegrees() {
		return tempInCelsius;
	}

	public void setTempInDegrees(int tempInDegrees) {
		this.tempInCelsius = tempInDegrees;
	}

	public int getTempInFahrenheit() {
		return tempInFahrenheit;
	}

	public void setTempInFahrenheit(int tempInFahrenheit) {
		this.tempInFahrenheit = tempInFahrenheit;
	}

}
