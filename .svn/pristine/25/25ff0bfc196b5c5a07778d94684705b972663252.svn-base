package Framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationProperties {
	
	/** The configuration property. */
	private static ConfigurationProperties configurationProperty = null;
	
	/** The Constant PROPERTY_FILENAME. */
	public final static String PROPERTY_FILENAME = "config/config.properties";
	
	/** The configuration properties. */
	private static Properties configurationProperties = new Properties();

	/**
	 * Gets the single instance of ConfigurationProperties.
	 *
	 * @return single instance of ConfigurationProperties
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private ConfigurationProperties() throws FileNotFoundException, IOException {
		configurationProperties.load(new FileInputStream(PROPERTY_FILENAME));
	}

	public static synchronized ConfigurationProperties getInstance() throws FileNotFoundException, IOException {
		if (configurationProperty == null) {
			configurationProperty = new ConfigurationProperties();
		}
		return configurationProperty;
	}

	/**
	 * returns the value of the property denoted by key.
	 *
	 * @param key
	 *            the key
	 * @return the property
	 * @throws Exception 
	 */
	public  String getProperty(final String key) throws Exception {
		String property = configurationProperties.getProperty(key);
		if (property == null) {
			throw new Exception("Property not set for: " + key);
		}
		return property != null ? property.trim() : property;
	}
}
