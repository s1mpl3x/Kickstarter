package me.simplex.pluginkickstarter.storage;

import me.simplex.pluginkickstarter.util.ConfigNodeDataType;

public class ConfigurationNodeContainer {
	private String node;
	private String defaultValue;
	private ConfigNodeDataType type;
	
	public ConfigurationNodeContainer() {
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public ConfigNodeDataType getType() {
		return type;
	}

	public void setType(ConfigNodeDataType type) {
		this.type = type;
	}
	
	
	
}
