package com.tvo.puzzle.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	public static String get(String key) {
		try
		{
			// Get the inputStream
			InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("/META-INF/system-config.properties");
			Properties properties = new Properties();
			// load the inputStream using the Properties
			properties.load(inputStream);
			// get the value of the property
			return properties.getProperty(key).trim();
		}
		catch (Exception e)
		{
			return "";
		}
	}
	public static String getMessage(String key){
		try
		{
			// Get the inputStream
			InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("/FacesMessageBundle.properties");
			Properties properties = new Properties();
			// load the inputStream using the Properties
			properties.load(inputStream);
			// get the value of the property
			return properties.getProperty(key).trim();
		}
		catch (Exception e)
		{
			return "";
		}
	}
}
