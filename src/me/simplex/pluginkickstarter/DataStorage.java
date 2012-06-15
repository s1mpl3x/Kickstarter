package me.simplex.pluginkickstarter;

import java.util.ArrayList;

import me.simplex.pluginkickstarter.storage.CommandContainer;
import me.simplex.pluginkickstarter.storage.ConfigurationNodeContainer;
import me.simplex.pluginkickstarter.storage.FileContainer;
import me.simplex.pluginkickstarter.storage.ListenerContainer;
import me.simplex.pluginkickstarter.storage.PermissionContainer;
import me.simplex.pluginkickstarter.storage.TaskContainer;

public class DataStorage {
	private String pluginname;
	private String author;
	private String package_;
	private String description;
	private String website;
	private String version;
	private String depends;
	private String softdepends;
	private String cfgfileheader;
	
	private boolean gen_listener;
	private boolean gen_commands;
	private boolean gen_configuration;
	private boolean gen_tasks;
	
	private ArrayList<TaskContainer> tasks;
	private ArrayList<CommandContainer> commands;
	private ArrayList<ListenerContainer> listener;
	private ArrayList<ConfigurationNodeContainer> configNodes;
	
	private ArrayList<FileContainer> buildFiles;
	private PermissionContainer permissionContainer;
	
	public DataStorage() {
		this.tasks = new ArrayList<TaskContainer>();
		this.commands = new ArrayList<CommandContainer>();
		this.listener = new ArrayList<ListenerContainer>();
		this.buildFiles = new ArrayList<FileContainer>();
		this.configNodes = new ArrayList<ConfigurationNodeContainer>();
		this.permissionContainer = new PermissionContainer();
		
		gen_listener = true;
		gen_commands = true;
		gen_configuration = true;
		gen_tasks = true;
	}
	
	public CommandContainer getCmdContainerByName(String name){
		for (CommandContainer cmd : commands) {
			if (cmd.getCommand().equals(name)) {
				return cmd;
			}
		}
		return null;
	}
	

	public void toggleListener(ListenerContainer listenerContainer) {
		if (listener.contains(listenerContainer)) {
			listener.remove(listenerContainer);
		}
		else {
			listener.add(listenerContainer);
		}
		
	}
	
	public String getPluginname() {
		return pluginname;
	}
	
	public void setPluginname(String pluginname) {
		this.pluginname = pluginname;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	

	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(ArrayList<TaskContainer> tasks) {
		this.tasks = tasks;
	}

	/**
	 * @return the tasks
	 */
	public ArrayList<TaskContainer> getTasks() {
		return tasks;
	}

	/**
	 * @param commands the commands to set
	 */
	public void setCommands(ArrayList<CommandContainer> commands) {
		this.commands = commands;
	}

	/**
	 * @return the commands
	 */
	public ArrayList<CommandContainer> getCommands() {
		return commands;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(ArrayList<ListenerContainer> listener) {
		this.listener = listener;
	}

	/**
	 * @return the listener
	 */
	public ArrayList<ListenerContainer> getListener() {
		return listener;
	}
	/**
	 * @return the buildFiles
	 */
	public ArrayList<FileContainer> getBuildFiles() {
		return buildFiles;
	}

	public void removeNullFiles() {
		ArrayList<Integer> toRemove = new ArrayList<Integer>();
		for (int i = buildFiles.size()-1; i >= 0; i--) {
			if (buildFiles.get(i)== null) {
				toRemove.add(i);
			}
		}
		for (int i : toRemove) {
			buildFiles.remove(i);
		}
	}

	public String getDepends() {
		return depends;
	}

	public void setDepends(String depends) {
		this.depends = depends;
	}

	public String getSoftdepends() {
		return softdepends;
	}

	public void setSoftdepends(String softdepends) {
		this.softdepends = softdepends;
	}
	
	public ArrayList<ConfigurationNodeContainer> getConfigNodes(){
		return configNodes;
	}

	public String getCfgfileheader() {
		return cfgfileheader;
	}

	public void setCfgfileheader(String cfgfileheader) {
		this.cfgfileheader = cfgfileheader;
	}

	public boolean isGen_listener() {
		return gen_listener;
	}

	public void setGen_listener(boolean genListener) {
		gen_listener = genListener;
	}

	public boolean isGen_commands() {
		return gen_commands;
	}

	public void setGen_commands(boolean genCommands) {
		gen_commands = genCommands;
	}

	public boolean isGen_configuration() {
		return gen_configuration;
	}

	public void setGen_configuration(boolean genConfiguration) {
		gen_configuration = genConfiguration;
	}

	public boolean isGen_tasks() {
		return gen_tasks;
	}

	public void setGen_tasks(boolean genTasks) {
		gen_tasks = genTasks;
	}

	/**
	 * @param package_ the package_ to set
	 */
	public void setPackage(String package_) {
		this.package_ = package_;
	}

	/**
	 * @return the package_
	 */
	public String getPackage() {
		return package_;
	}

	public PermissionContainer getPermissionsContainer() {
		return permissionContainer;
	}
	
	

}
