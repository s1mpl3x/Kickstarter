package me.simplex.pluginkickstarter.generator;

import me.simplex.pluginkickstarter.PluginKickstarter;
import me.simplex.pluginkickstarter.storage.PermissionContainer;
import me.simplex.pluginkickstarter.util.PermissionsType;

public class GenPermissions extends Generator {
	
	private PermissionContainer container;

	public GenPermissions(PluginKickstarter main, PermissionContainer container) {
		super(main);
		this.container = container;
	}

	@Override
	public String buildClassname() {
		return "Permissions";
	}	
	
	
	public String buildPermissionIncludes(){
		String build = "";
		
		for(PermissionsType type : container.getAllTypes()){
			build += "import " + main.getData().getPackage() + ".permissions." + type.getConstructor() + ";\n";
		}
		
		build += this.buildMainClassImport();
		
		return build;
	}
	
	public String buildPermissionInits(){
		String build = "";
		
		for(PermissionsType type : container.getAllTypes())
			if(!type.equals(PermissionsType.OpPermissions))
				build += 
				"		try{/n" +
				"			tempPlugin = new " + type.getConstructor() +"();/n" +
				"			if(tempPlugin.isActive()){/n" +
				"				permPlugin = tempPlugin;/n" +
				"				return;/n" +
				"			}/n" +
				"		}catch(NoClassDefFoundError e){}/n/n";
		
		build +=
		"		permPlugin = new OpPermissions();" +
		"		plugin.log(\"CRITICAL: No Permission-System hooked. Plugin will not work properly. \" +" + 
		"					\"Use one of the following Systems: Vault, PermissionsEx, GroupManager, BukkitPermissions. \" + " +
		"					\"Using Op-Status as Permission.\"); ";	
		
		return build;
	}

}
