package me.simplex.pluginkickstarter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import me.simplex.pluginkickstarter.generator.GenCommand;
import me.simplex.pluginkickstarter.generator.GenConfiguration;
import me.simplex.pluginkickstarter.generator.GenListeners;
import me.simplex.pluginkickstarter.generator.GenPlugin;
import me.simplex.pluginkickstarter.generator.GenMainClass;
import me.simplex.pluginkickstarter.generator.GenTask;
import me.simplex.pluginkickstarter.generator.Generator;
import me.simplex.pluginkickstarter.storage.CommandContainer;
import me.simplex.pluginkickstarter.storage.FileContainer;
import me.simplex.pluginkickstarter.storage.ListenerContainer;
import me.simplex.pluginkickstarter.storage.PermissionContainer;
import me.simplex.pluginkickstarter.storage.TaskContainer;
import me.simplex.pluginkickstarter.util.ListenerType;
import me.simplex.pluginkickstarter.util.TemplateType;

public class FileBuilder {
	PluginKickstarter main;
	
	public FileBuilder(PluginKickstarter main) {
		this.main = main;
	}
	
	public String loadTemplateFile(TemplateType type){
		ArrayList<String> content= new ArrayList<String>();
		try {
			BufferedReader bffrdRdr = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("templates/"+type.giveFilename())));
			while(true){
				String line = bffrdRdr.readLine();
				if(line==null){
					break;
				}
				content.add(line);
			}
			bffrdRdr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String file = "";
		for (String line : content) {
			file=file+line+"\n";
		}
		return file;
	}
	
	public FileContainer build_MainClass_File(){
		GenMainClass gen = new GenMainClass(main);
		String content = loadTemplateFile(TemplateType.MainClass);
		
		content = content.replace("$author", 				gen.buildAuthor());
		content = content.replace("$website", 				gen.buildWebsite());
		content = content.replace("$pluginname", 			gen.buildPluginName());
		content = content.replace("$package", 				gen.buildPackage(TemplateType.MainClass,true));
		content = content.replace("$imports", 				gen.buildImports());
		content = content.replace("$classname", 			gen.buildClassname());
		content = content.replace("$variables", 			gen.buildVariables());
		content = content.replace("$init", 					gen.buildInit());
		content = content.replace("$register_events", 		gen.buildRegister_Events());
		content = content.replace("$register_commands", 	gen.buildRegister_Commands());
		content = content.replace("$schedule_tasks", 		gen.buildSchedule_Tasks());
		content = content.replace("$disable", 				gen.buildDisable());
		content = content.replace("$setupConfig", 			gen.buildSetupConfig());
		content = content.replace("$configGetter", 			gen.buildConfigGetter());
		content = content.replace("$logger", 				gen.buildLogMethod());
		
		return new FileContainer(Generator.StringToClassName(main.getData().getPluginname())+".java", gen.buildFilepath(TemplateType.MainClass), content);
	}
	
	public FileContainer build_Plugin_File(){
		GenPlugin gen = new GenPlugin(main);
		String content = loadTemplateFile(TemplateType.Plugin);
		
		content = content.replace("$pluginname", 			gen.buildPluginName());
		content = content.replace("$mainclass", 			gen.buildMainClass());
		content = content.replace("$version", 				gen.buildVersion());
		content = content.replace("$author", 				gen.buildAuthor());
		content = content.replace("$website", 				gen.buildWebsite());
		content = content.replace("$description", 			gen.buildDescription());
		content = content.replace("$commands", 				gen.buildCommands());
		content = content.replace("$depends", 				gen.buildDepends());
		content = content.replace("$softdepends", 			gen.buildSoftdepends());
		
		return new FileContainer("plugin.yml", "", content);
	}
	
	public FileContainer build_Task_File(TaskContainer c){
		GenTask gen = new GenTask(main, c);
		String content = loadTemplateFile(TemplateType.Task);
		
		content = content.replace("$author", 				gen.buildAuthor());
		content = content.replace("$website", 				gen.buildWebsite());
		content = content.replace("$pluginname", 			gen.buildPluginName());
		content = content.replace("$mainclassimport", 		gen.buildMainClassImport());
		content = content.replace("$mainclasstype", 		gen.buildMainClassType());
		content = content.replace("$package", 				gen.buildPackage(TemplateType.Task,true));
		content = content.replace("$classname", 			gen.buildClassname());
		
		return new FileContainer(gen.buildFileName(), gen.buildFilepath(TemplateType.Task), content);
	}
	
	public FileContainer build_Command_File(CommandContainer c){
		GenCommand gen = new GenCommand(main, c);
		String content = loadTemplateFile(TemplateType.Command);
		
		content = content.replace("$author", 				gen.buildAuthor());
		content = content.replace("$website", 				gen.buildWebsite());
		content = content.replace("$pluginname", 			gen.buildPluginName());
		content = content.replace("$playeronly", 			gen.buildPlayerOnly());
		content = content.replace("$mainclassimport", 		gen.buildMainClassImport());
		content = content.replace("$mainclasstype", 		gen.buildMainClassType());
		content = content.replace("$package", 				gen.buildPackage(TemplateType.Command,true));
		content = content.replace("$playerimport", 			gen.buildPlayerImport());
		content = content.replace("$classname", 			gen.buildClassname());
		content = content.replace("$command", 				gen.buildCommand());
		
		return new FileContainer(gen.buildFileName(), gen.buildFilepath(TemplateType.Command), content);
	}
	
	public FileContainer build_Listener_File(ListenerType type){
		int count = 0;
		for (ListenerContainer con : main.getData().getListener()) {
			if (con.getFile().equals(type)) {
				count++;
			}
		}
		
		if (count>0) {
			return build_Listener_File_rly(type);
		}
		return null;
	}

	private FileContainer build_Listener_File_rly(ListenerType type) {
		GenListeners gen = new GenListeners(main,type);
		String content = loadTemplateFile(TemplateType.Listener);
		
		content = content.replace("$author", 				gen.buildAuthor());
		content = content.replace("$website", 				gen.buildWebsite());
		content = content.replace("$pluginname", 			gen.buildPluginName());
		content = content.replace("$classname", 			gen.buildClassname());
		content = content.replace("$mainclassimport", 		gen.buildMainClassImport());
		content = content.replace("$mainclasstype", 		gen.buildMainClassType());
		content = content.replace("$listenerimports", 		gen.buildImports());
		content = content.replace("$listeners", 			gen.buildListeners());
		content = content.replace("$typeListener", 			gen.buildListenerImport());
		content = content.replace("$superclass", 			gen.buildSuperclass());		
		content = content.replace("$package", 				gen.buildPackage(TemplateType.Listener,true));
		
		return new FileContainer(gen.buildFileName(), gen.buildFilepath(TemplateType.Listener), content);
	}
	
	public FileContainer build_Config_File(){
		if(main.getData().getConfigNodes().size() > 0)
			return build_Config_File_rly();
		else
			return null;
	}
	
	private FileContainer build_Config_File_rly(){
		GenConfiguration gen = new GenConfiguration(main);
		String content = loadTemplateFile(TemplateType.Configuration);
		
		content = content.replace("$package", 				gen.buildPackage(TemplateType.Configuration,true));
		content = content.replace("$author", 				gen.buildAuthor());
		content = content.replace("$website", 				gen.buildWebsite());
		content = content.replace("$pluginname", 			gen.buildPluginName());
		content = content.replace("$classname", 			gen.buildClassname());
		content = content.replace("$mainclassimport", 		gen.buildMainClassImport());
		content = content.replace("$mainclasstype", 		gen.buildMainClassType());
		content = content.replace("$config_vars", 			gen.buildVars());
		content = content.replace("$config_default", 		gen.buildDefaults());
		content = content.replace("$config_reload", 		gen.buildReload());
		content = content.replace("$config_getter", 		gen.buildGetter());
		
		return new FileContainer(gen.buildFileName(), gen.buildFilepath(TemplateType.Configuration), content);
	}
	
	public ArrayList<FileContainer> build_Permission_Files(){
		if(main.getData().getPermissionsContainer().getAllTypes().size() != 0)
			return buildPermissionContainer();
		else
			return null;
	}
	
	private ArrayList<FileContainer> buildPermissionContainer(){
		ArrayList<FileContainer> files = new ArrayList<FileContainer>();
		PermissionContainer container = main.getData().getPermissionsContainer();
		
		String content = loadTemplateFile(TemplateType.PermissionsMain);
		
		return files;
	}
}
