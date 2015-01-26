package edu.rutgers.util;

import java.util.Properties;

public class ConfigReader {
	
	public enum PROPERTIES {
		
		LOGGER("logger"),
		DB_CONNECTOR("db_connector"),
		DB_CONNECTION("db_connection"),
		DB_NAME("db_name"),
		DB_USER("db_user"),
		DB_PASSWORD("db_password");
		
		private String strVal;
		private PROPERTIES(final String pstrVal) {
			strVal = pstrVal;
		}
	}
	
	private final static String gPropsFile = "app.properties";
	private static Properties gProperties;
	private volatile static ConfigReader instance;
	
	private ConfigReader() {
		gProperties = new Properties();
		try {
			gProperties.load(ConfigReader.class.getClassLoader().getResourceAsStream(ConfigReader.gPropsFile));
		} catch (Exception e) {
			System.out.println("FATAL ERROR: Initializing application's properties");
		}
	}
	
	/**
	 * Returns a singleton instance of the ConfigReader.
	 * @return
	 */
	public synchronized static ConfigReader getInstance() {
		if(ConfigReader.instance == null) {
			ConfigReader.instance = new ConfigReader();
		}
		return ConfigReader.instance;
	}
	
	/**
	 * Retrieves a String value of a property.
	 * @return String value of property
	 */
	public String getStr(final PROPERTIES penmProp) {
		return gProperties.getProperty(penmProp.strVal);
	}
}
