package me.simplex.pluginkickstarter.util;

public enum PermissionsType {
	Pex("PEXPermissions"),
	BukkitPermissions("BukkitPermissionsPermissions"),
	GroupManager("GroupManagerPermissions"),
	Vault("VaultPermissions"),
	OpPermissions("");
	
	private String constructor;
	private PermissionsType(String constructor) {
		this.constructor = constructor;
	}
	
	public String getConstructor(){
		return constructor;
	}
}
