package me.simplex.pluginkickstarter.storage;

import java.util.ArrayList;

import me.simplex.pluginkickstarter.util.PermissionsType;

public class PermissionContainer {
	private ArrayList<PermissionsType> includePermissions;
	
	public PermissionContainer(){
		includePermissions = new ArrayList<PermissionsType>();
	}
	
	public void addSystem(PermissionsType type){
		includePermissions.add(type);
	}
	
	public void removeSystem(PermissionsType type){
		includePermissions.remove(type);
	}
	
	public ArrayList<PermissionsType> getAllTypes(){
		return includePermissions;
	}
	
	public boolean contains(PermissionsType type){
		return includePermissions.contains(type);
	}
}
