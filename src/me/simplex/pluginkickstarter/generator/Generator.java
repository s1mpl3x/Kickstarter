package me.simplex.pluginkickstarter.generator;

import java.io.File;

import me.simplex.pluginkickstarter.PluginKickstarter;
import me.simplex.pluginkickstarter.util.TemplateType;

public abstract class Generator {
	PluginKickstarter main;
	
	public Generator(PluginKickstarter main) {
		this.main = main;
	}
	
	public String buildFileName() {
		return buildClassname()+".java";
	}
	
	public String buildAuthor(){
		return main.getData().getAuthor();
	}
	
	public String buildWebsite(){
		return main.getData().getWebsite();
	}
	
	public String buildPluginName(){
		return main.getData().getPluginname();
	}
	
	public String buildPackage(TemplateType template , boolean isPackageDeclare){
		String ret;
		if (template.equals(TemplateType.Command)) {
			ret = main.getData().getPackage()+".commands";
		}
		else if(template.equals(TemplateType.Task)) {
			ret = main.getData().getPackage()+".tasks";
		}
		else if(template.equals(TemplateType.Listener)){
			ret = main.getData().getPackage()+".listeners";
		}
		else if(template.equals(TemplateType.Configuration)){
			ret = main.getData().getPackage() + ".configuration";
		}
		else {
			ret = main.getData().getPackage();
		}
		if (isPackageDeclare) {
			ret=ret+";\n";
		}
		return ret;
	}
	
	public String buildFilepath(TemplateType template){
		return "src"+File.separator+buildPackage(template,false).replace(".", File.separator);
	}
		
	public abstract String buildClassname();
	
	public String buildMainClassImport(){
		return "import "+buildPackage(TemplateType.MainClass,false)+"."+buildMainClassType()+";\n";
	}
	
	public String buildMainClassType(){
		return StringToClassName(main.getData().getPluginname());
	}
	
	public static String StringToClassName(String g){
		return g.toLowerCase().substring(0, 1).toUpperCase()+g.substring(1);
	}
}