package com.testvagrant.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

	Properties prop;

	public Properties readPropertiesFile(String filename) {
				
		prop = new Properties();
		try {
			prop.load(new FileInputStream(filename));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return prop;
	}
	
	public String getProperty(String key) {
		return prop.getProperty(key);
	}
	
}
