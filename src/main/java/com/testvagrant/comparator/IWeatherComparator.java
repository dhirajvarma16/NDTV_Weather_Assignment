package com.testvagrant.comparator;

import com.testvagrant.model.VarianceModel;
import com.testvagrant.model.WeatherModel;

@FunctionalInterface
public interface IWeatherComparator {

	void compare(WeatherModel uiObj, WeatherModel apiObj, VarianceModel varObj);
	
}
