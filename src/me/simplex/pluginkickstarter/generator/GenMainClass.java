package me.simplex.pluginkickstarter.generator;

import java.util.ArrayList;

import me.simplex.pluginkickstarter.PluginKickstarter;
import me.simplex.pluginkickstarter.storage.CommandContainer;
import me.simplex.pluginkickstarter.storage.ListenerContainer;
import me.simplex.pluginkickstarter.storage.TaskContainer;
import me.simplex.pluginkickstarter.util.ListenerType;

public class GenMainClass extends Generator {
	ArrayList<ListenerType> types_to_handle;
	
	public GenMainClass(PluginKickstarter main) {
		super(main);
		types_to_handle = new ArrayList<ListenerType>();
		int count = 0;
		for (ListenerType type : ListenerType.values()) {
			for (ListenerContainer con : main.getData().getListener()) {
				if (con.getFile().equals(type)) {
					count++;
				}
			}
			if (count >0) {
				types_to_handle.add(type);
			}
			count = 0;
		}
	}
	
	public String buildVariables(){
		String ret ="";
		//General
		ret=ret+"	private Logger log;\n";
		ret=ret+"	private PluginDescriptionFile description;\n";
		ret=ret+"\n";
		ret=ret+"	private String prefix;\n";
		// Config
		if (main.getData().isGen_configuration() && main.getData().getConfigNodes().size() > 0) {
			ret += "	private Config config;\n";
		}
		
		// Listeners 
		/*
		for (ListenerType t : types_to_handle) {
			ret=ret+"	private Listener_"+StringToClassName(t.toString())+" listener"+StringToClassName(t.toString())+";\n";
		}*/

		return ret;
	}
	
	public String buildInit(){
		String ret ="";
		
		//general
		ret=ret+"		log = Logger.getLogger(\"Minecraft\");\n";
		ret=ret+"		description = getDescription();\n";	
		ret=ret+"		prefix = \"[\"+description.getName()+\"] \";\n";
		ret=ret+"\n";
		ret=ret+"		log(\"loading \"+description.getFullName());\n";
		ret=ret+"\n";
		
		// Config
		if (main.getData().isGen_configuration() && main.getData().getConfigNodes().size() > 0) {
			ret=ret+"		setupConfiguration();\n";
		}
		
		if(types_to_handle.size() > 0) ret += "		registerEvents();\n";
		
		// Listeners
		/*
		for (ListenerType t : types_to_handle) {
			ret +="		listener"+StringToClassName(t.toString())+" = new Listener_"+StringToClassName(t.toString())+"(this);\n";
		} */
		return ret; 
	}
	
	public String buildRegister_Events(){
		if(types_to_handle.size() == 0) return "";
		
		String ret = "	private void registerEvents(){\n";
		for (ListenerType t : types_to_handle) {
			ret +="		new Listener_" + StringToClassName(t.toString()) + "(this);\n";
		}
		ret += "	}\n";
		
		return ret;
	}
	
	public String buildRegister_Commands(){
		String commandRegisters = "";
		for (CommandContainer c : main.getData().getCommands()) {
			commandRegisters += "		getCommand(\""+c.getCommand().toLowerCase()+"\").setExecutor(new "+"CommandExecutor_"+StringToClassName(c.getCommand())+"(this));\n";
		}
		return commandRegisters;
	}
	
	public String buildSchedule_Tasks(){
		String ret="";
		for (TaskContainer task : main.getData().getTasks()) {
			if (task.isRegisterAtOnEnable()) {
				switch (task.getType()) {
					case AsyncTask: 			ret=ret+  "		getServer().getScheduler().scheduleAsyncDelayedTask(this, new Task_"+StringToClassName(task.getTaskname())+"(this));\n";break;
					case AsyncDelayedTask: 		ret=ret+  "		getServer().getScheduler().scheduleAsyncDelayedTask(this, new Task_"+StringToClassName(task.getTaskname())+"(this), 20*"+task.getPeriod()+");\n";break;
					case AsyncRepeatingTask: 	ret=ret+  "		getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Task_"+StringToClassName(task.getTaskname())+"(this), 20*"+task.getPeriod()+", 20*"+task.getPeriod()+");\n";break;
					case SyncTask:				ret=ret+  "		getServer().getScheduler().scheduleSyncDelayedTask(this, new Task_"+StringToClassName(task.getTaskname())+"(this));\n";break;
					case SyncDelayedTask:		ret=ret+  "		getServer().getScheduler().scheduleSyncDelayedTask(this, new Task_"+StringToClassName(task.getTaskname())+"(this), 20*"+task.getPeriod()+");\n";break;
					case SyncRepeatingTask: 	ret=ret+  "		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Task_"+StringToClassName(task.getTaskname())+"(this), 20*"+task.getPeriod()+", 20*"+task.getPeriod()+");\n";break;
					default: break;
				}
			}
		}
		return ret;
	}
	
	public String buildImports(){
		String ret="";
		//general imports
		ret=ret+"import java.util.logging.Logger;\n";
		ret=ret+"import org.bukkit.plugin.PluginDescriptionFile;\n";
		
		//Config Imports
		if (main.getData().isGen_configuration() && main.getData().getConfigNodes().size() > 0) {
			ret += "import " + main.getData().getPackage() + ".configuration.Config;\n";
		}
		//Command Imports
		for (CommandContainer c : main.getData().getCommands()) {
			ret=ret+"import "+main.getData().getPackage()+".commands.CommandExecutor_"+StringToClassName(c.getCommand())+";\n";
		}
		
		//Task Imports
		for (TaskContainer task : main.getData().getTasks()) {
			if (task.isRegisterAtOnEnable()) {
				ret=ret+"import "+main.getData().getPackage()+".tasks.Task_"+StringToClassName(task.getTaskname())+";\n";
			}
		}
		
		//Listener Imports
		if (types_to_handle.size() > 0) {	
			for (ListenerType t : types_to_handle) {
				ret=ret+"import "+main.getData().getPackage()+".listeners.Listener_"+StringToClassName(t.toString())+";\n";
			}
		}
		
		return ret; 
	}
	
	public String buildDisable(){
		return 	"		log(\"disabled \"+description.getFullName());\n";
	}
	
	public String buildSetupConfig(){
		String ret="";
		if (main.getData().isGen_configuration() && main.getData().getConfigNodes().size() > 0) {
			ret=ret+"	private void setupConfiguration(){\n";
			ret=ret+"		config = new Config(this);\n";
			ret=ret+"	}\n";
		}

		return ret;
	}
		
	public String buildConfigGetter(){
		if(main.getData().getConfigNodes().size() == 0) return "";
		
		String ret ="	public Config interactConfig(){\n";
		ret += 		"		return config;\n";
		ret += 		"	}\n";
		
		/*if (main.getData().isGen_configuration()) {
			for (ConfigurationNodeContainer c : main.getData().getConfigNodes()) {
				switch (c.getType()) {
				case BOOLEAN: 		
					ret=ret+"	public boolean get"+StringToClassName(buildConfigVarName(c))+"(){\n";
					ret=ret+"		return "+buildConfigVarName(c)+";\n";
					ret=ret+"	}\n\n";
					break;
				case BOOLEAN_LIST: 
					ret=ret+"	public ArrayList<Boolean> get"+StringToClassName(buildConfigVarName(c))+"(){\n";
					ret=ret+"		return "+buildConfigVarName(c)+";\n";
					ret=ret+"	}\n\n";
					break;
				case DOUBLE:
					ret=ret+"	public double get"+StringToClassName(buildConfigVarName(c))+"(){\n";
					ret=ret+"		return "+buildConfigVarName(c)+";\n";
					ret=ret+"	}\n\n";
					break;
				case DOUBLE_LIST:
					ret=ret+"	public ArrayList<Double> get"+StringToClassName(buildConfigVarName(c))+"(){\n";
					ret=ret+"		return "+buildConfigVarName(c)+";\n";
					ret=ret+"	}\n\n";
					break;
				case INT: 	
					ret=ret+"	public int get"+StringToClassName(buildConfigVarName(c))+"(){\n";
					ret=ret+"		return "+buildConfigVarName(c)+";\n";
					ret=ret+"	}\n\n";
					break;
				case INT_LIST:
					ret=ret+"	public ArrayList<Integer> get"+StringToClassName(buildConfigVarName(c))+"(){\n";
					ret=ret+"		return "+buildConfigVarName(c)+";\n";
					ret=ret+"	}\n\n";
					break;
				case STRING:
					ret=ret+"	public String get"+StringToClassName(buildConfigVarName(c))+"(){\n";
					ret=ret+"		return "+buildConfigVarName(c)+";\n";
					ret=ret+"	}\n\n";
					break;
				case STRING_LIST:
					ret=ret+"	public ArrayList<String> get"+StringToClassName(buildConfigVarName(c))+"(){\n";
					ret=ret+"		return "+buildConfigVarName(c)+";\n";
					ret=ret+"	}\n\n";
					break;
				}
			}	
		}*/
		return ret;
	}
	
	public String buildLogMethod(){
		String ret="";
		ret=ret+"	public void log(String message){\n";
		ret=ret+"		log.info(prefix+message);\n";
		ret=ret+"	}\n";	
		return ret;
	}

	@Override
	public String buildClassname() {
		return StringToClassName(main.getData().getPluginname());
	}
}
