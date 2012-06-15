package me.simplex.pluginkickstarter.util;

public enum ConfigNodeDataType {
	STRING,
	STRING_LIST,
	INT,
	INT_LIST,
	DOUBLE,
	DOUBLE_LIST,
	BOOLEAN,
	BOOLEAN_LIST;
	
	public String toString() {
		return this.name().toLowerCase().substring(0, 1).toUpperCase()+this.name().substring(1).replace("_", " ");
	};
}

