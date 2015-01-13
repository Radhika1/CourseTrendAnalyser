package edu.utd.ooad.cta.db;

import java.util.HashMap;
import java.util.Map;

public class DatabaseConfig {

	private Map<String, String> properties;
	private static DatabaseConfig _instance;

	public static DatabaseConfig getInstance() {
		if (_instance == null) {
			_instance = new DatabaseConfig();
		}
		return _instance;
	}

	private DatabaseConfig() {
		this(new HashMap<String, String>());
	}

	public DatabaseConfig(Map<String, String> properties) {
		this.properties = properties;
	}

	public Boolean contains(String key) {
		return properties.containsKey(key);
	}

	public String getProperty(String key) {
		return this.contains(key) ? properties.get(key) : null;
	}

	public void setProperty(String key, String value) {
		properties.put(key, value);
	}

	public Boolean isEmptyOrNull() {
		return (properties.isEmpty() || properties == null);
	}

	public Integer size() {
		return properties.size();
	}
}
