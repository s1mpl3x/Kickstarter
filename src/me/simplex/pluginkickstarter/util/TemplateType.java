package me.simplex.pluginkickstarter.util;

public enum TemplateType {
	Plugin,
	MainClass,
	Listener,
	Command,
	Task,
	Configuration,
	PermissionsMain,
	
	//Different Permission Systems
	prmBukkitPermissions,
	prmGroupManager,
	prmOpPermissions,
	prmPermissionPlugin,
	prmPEX,
	prmVault;
	
	public String giveFilename(){
		if(this.name().contains("prm"))
			return "perm." + this.toString() + ".template";
		return this.toString()+".template";
	}
}