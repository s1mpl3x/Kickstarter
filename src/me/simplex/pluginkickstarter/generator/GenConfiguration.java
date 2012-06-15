package me.simplex.pluginkickstarter.generator;

import java.util.ArrayList;

import me.simplex.pluginkickstarter.PluginKickstarter;
import me.simplex.pluginkickstarter.storage.ConfigurationNodeContainer;
import me.simplex.pluginkickstarter.util.ConfigNodeDataType;

public class GenConfiguration extends Generator{

	private ArrayList<ConfigurationNodeContainer> configNode;

	public GenConfiguration(PluginKickstarter main) {
		super(main);
		configNode = main.getData().getConfigNodes();
	}

	@Override
	public String buildClassname() {
		return "Config";
	}
	
	public String buildVars(){
		String ret = "";
		
		for(ConfigurationNodeContainer  container : configNode){
			ret += "	private " + getPrefix(container.getType()) + " " + buildConfigVarName(container) + ";\n";
		}
		
		return ret;
	}
	
	public String buildGetter(){
		String ret = "";
		
		for(ConfigurationNodeContainer container : configNode){
			ret += "\n	public " + getPrefix(container.getType()) + " get" + buildConfigVarName(container) + "(){\n";
			ret += "		return " + buildConfigVarName(container) + ";\n";
			ret += "	}\n";
		}
		
		return ret;
	}
	
	public String buildDefaults(){
		String ret = "		FileConfiguration config = plugin.getConfig();\n";
		ret += "		config.options().header(\"" + main.getData().getCfgfileheader() + "\");\n\n";
		
		for(ConfigurationNodeContainer node : configNode){
			String stringAdding = getStringAddings(node);
			ret += "		config.addDefault(\"" + node.getNode() + "\", " + stringAdding + node.getDefaultValue() + stringAdding + ");\n";
		}
		
		ret += "\n		config.options().copyDefaults(true);\n" +
				"		plugin.saveConfig();\n";
		
		return ret;
	}
	
	private String getStringAddings(ConfigurationNodeContainer container){
		if(container.getType().equals(ConfigNodeDataType.STRING)) return "\"";
		
		return "";
	}
	
	public String buildReload(){
		String ret = "		plugin.reloadConfig();\n"
					+ "		FileConfiguration config = plugin.getConfig();\n\n";
		
		for(ConfigurationNodeContainer node : configNode){
			ret += buildCfgGetString(node);
		}
		
		return ret;
	}
	
	private String getPrefix(ConfigNodeDataType type){
		switch (type) {
		case BOOLEAN: 		return	"boolean";
		case BOOLEAN_LIST: 	return	"ArrayList<Boolean>";
		case DOUBLE: 		return	"double";
		case DOUBLE_LIST: 	return	"ArrayList<Double>";
		case INT: 			return	"int";
		case INT_LIST: 		return	"ArrayList<Integer>";
		case STRING:		return	"String";
		case STRING_LIST:	return	"ArrayList<String>";
		}
		
		return "";
	}
	
	private String buildConfigVarName(ConfigurationNodeContainer c){
		return "config_"+c.getNode().replace(".", "_").toLowerCase(); 
	}
	
	private String buildCfgGetString(ConfigurationNodeContainer c){
		switch (c.getType()) {
			case BOOLEAN: return "		"+buildConfigVarName(c)+" = config.getBoolean(\""+c.getNode()+"\", "+c.getDefaultValue()+");\n";
			case BOOLEAN_LIST: return buildListInit(c);
			case DOUBLE: return "		"+buildConfigVarName(c)+" = config.getDouble(\""+c.getNode()+"\", "+c.getDefaultValue()+");\n";
			case DOUBLE_LIST: return buildListInit(c);
			case INT: return "		"+buildConfigVarName(c)+" = config.getInt(\""+c.getNode()+"\", "+c.getDefaultValue()+");\n";
			case INT_LIST: return buildListInit(c);
			case STRING: return "		"+buildConfigVarName(c)+" = config.getString(\""+c.getNode()+"\", \""+c.getDefaultValue()+"\");\n";
			case STRING_LIST: return buildListInit(c);
		}
		return "";
	}
	
	private String buildListInit(ConfigurationNodeContainer c){
		String ret ="";
		switch (c.getType()) {
		case BOOLEAN_LIST:
			ret=ret+ "		ArrayList<Boolean> init_"+c.getNode().replace(".", "_")+" = new ArrayList<Boolean>();\n";
			for (String listinit : c.getDefaultValue().split(",")) {
				ret=ret+"		init_"+c.getNode().replace(".", "_")+".add("+listinit.trim()+");\n";
			}
			ret=ret+"		"+buildConfigVarName(c)+".addAll(config.getBooleanList(\""+c.getNode()+"\", init_"+c.getNode().replace(".", "_")+"));\n";
			break;
		case DOUBLE_LIST: 	
			ret=ret+ "		ArrayList<Double> init_"+c.getNode().replace(".", "_")+" =new ArrayList<Double>();\n";
			for (String listinit : c.getDefaultValue().split(",")) {
				ret=ret+"		init_"+c.getNode().replace(".", "_")+".add("+listinit.trim()+"D);\n";
			}
			ret=ret+"		"+buildConfigVarName(c)+".addAll(config.getDoubleList(\""+c.getNode()+"\", init_"+c.getNode().replace(".", "_")+"));\n";
			break;
		case INT_LIST:		
			ret=ret+ "		ArrayList<Integer> init_"+c.getNode().replace(".", "_")+" = new ArrayList<Integer>();\n";
			for (String listinit : c.getDefaultValue().split(",")) {
				ret=ret+"		init_"+c.getNode().replace(".", "_")+".add("+listinit.trim()+");\n";
			}
			ret=ret+"		"+buildConfigVarName(c)+".addAll(config.getIntList(\""+c.getNode()+"\", init_"+c.getNode().replace(".", "_")+"));\n";
			break;
		case STRING_LIST:	
			ret=ret+ "		ArrayList<String> init_"+c.getNode().replace(".", "_")+" =new ArrayList<String>();\n";
			for (String listinit : c.getDefaultValue().split(",")) {
				ret=ret+"		init_"+c.getNode().replace(".", "_")+".add(\""+listinit.trim()+"\");\n";
			}
			ret=ret+"		"+buildConfigVarName(c)+".addAll(config.getStringList(\""+c.getNode()+"\", init_"+c.getNode().replace(".", "_")+"));\n";
			break;
		}
		return ret;
	}

}