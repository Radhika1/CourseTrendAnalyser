/**
 * 
 */
package edu.utd.ooad.cta.config;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.utd.ooad.cta.db.DatabaseConfig;

/**
 * 
 * @author radhika
 * 
 */
public final class ToolConfiguration {

	/**
	 * Logger for logging the events in the class
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ToolConfiguration.class);

	/**
	 * 
	 */
	private Configuration config;

	/**
	 * Represents default config file.
	 */
	public static final String DEFAULT_CONFIG = "config.xml";

	
	/**
	 * 
	 */
	private static final String CFGKEY_DATABASE = "database";

	/**
	 * 
	 */
	private static final String CFGKEY_PROPERTY_VALUE = "property";

	/**
	 * 
	 */
	private static final String CFGKEY_PROPERTY_NAME = "property[@name]";

	/**
	 * 
	 */

	/**
	 * 
	 */
	private static ToolConfiguration instance;

	/**
	 * private constructor to make this class as final
	 */
	private ToolConfiguration() {
		this(DEFAULT_CONFIG);
	}

	/**
	 * 
	 * @return ToolConfiguration
	 */
	public static ToolConfiguration getInstance() {
		if (instance == null) {
			instance = new ToolConfiguration();
		}
		return instance;
	}

	/**
	 * Overloaded private constructor
	 * 
	 * @param configFile
	 *            - config file name
	 */
	private ToolConfiguration(final String configFile) {
		try {
			config = new CompositeConfiguration();
			((CompositeConfiguration) config)
					.addConfiguration(new SystemConfiguration());
			((CompositeConfiguration) config)
					.addConfiguration(new XMLConfiguration(configFile));
			((CompositeConfiguration) config).setThrowExceptionOnMissing(true);
			printConfig();
		} catch (ConfigurationException e) {
		}
	}

	/**
	 * print configurations from config file
	 */
	private void printConfig() {
		LOGGER.debug("Configurtion Keys");
		Iterator<?> it = config.getKeys();
		while (it.hasNext()) {
			String key = (String) it.next();
			LOGGER.debug("[{} = {}]", key, config.getString(key));
		}
	}

	/**
	 * Core method to get property value from config file
	 * 
	 * @param key
	 *            - string parameter(key)
	 * @return Object
	 */
	public Object getProp(final String key) {
		LOGGER.debug("[{}={}]", key, config.getProperty(key));
		return config.getProperty(key);
	}

	

	public void loadDatabaseProperties() {
		List<Object> props = config.getList(String.format("%s.%s",
				CFGKEY_DATABASE, CFGKEY_PROPERTY_NAME));
		List<Object> propVals = config.getList(String.format("%s.%s",
				CFGKEY_DATABASE, CFGKEY_PROPERTY_VALUE));
		DatabaseConfig dbconfig = DatabaseConfig.getInstance();
		for (int i = 0; i < props.size(); i++)
			dbconfig.setProperty((String) props.get(i),
					(String) propVals.get(i));

		LOGGER.debug("{}", props);
		LOGGER.debug("{}", propVals);
	}

	
}