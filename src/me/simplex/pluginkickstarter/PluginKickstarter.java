package me.simplex.pluginkickstarter;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import me.simplex.pluginkickstarter.gui.GUI_Main_Window;
import me.simplex.pluginkickstarter.storage.CommandContainer;
import me.simplex.pluginkickstarter.storage.ListenerData;
import me.simplex.pluginkickstarter.storage.TaskContainer;
import me.simplex.pluginkickstarter.util.ListenerType;

public class PluginKickstarter {
	private GUI_Main_Window GUI;
	private DataStorage data;
	private FileBuilder builder;
	private FileCreater creater;
	private ListenerData listener_data;
	
	private final String VERSION = "0.0.2 ALPHA";

	public PluginKickstarter() {
		lookAndFeel();
		data = new DataStorage();
		builder = new FileBuilder(this);
		creater = new FileCreater(this);
		listener_data = new ListenerData();
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				startGUI();
			}
		});
	}
	
	private void startGUI(){
		GUI = new GUI_Main_Window(this);
	}
	
	public static void main(String[] args) {
		new PluginKickstarter();
	}
	
	public GUI_Main_Window getGUI() {
		return GUI;
	}

	public DataStorage getData() {
		return data;
	}
	
	private void lookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	public void buildPlugin(String dir, boolean showFolder){
		getGUI().addToLog("begin class generation:",true);
		long timestart = System.currentTimeMillis();
		data.getBuildFiles().clear();
		// plugin.yml
		
		data.getBuildFiles().add(builder.build_Plugin_File());
		getGUI().addToLog("generated plugin.yml",true);
		
		// commandExecutors
		if (data.getCommands().size() > 0) {
			for (CommandContainer c : data.getCommands()) {
				data.getBuildFiles().add(builder.build_Command_File(c));
			}
		}
		getGUI().addToLog("generated CommandExecutors",true);
		
		// listeners
		
		if (data.getListener().size() > 0) {
			for (ListenerType type: ListenerType.values()) {
				data.getBuildFiles().add(builder.build_Listener_File(type));
			}
		}
		getGUI().addToLog("generated listeners",true);
		
		//Config
		
		if(data.getConfigNodes().size() > 0){
			data.getBuildFiles().add(builder.build_Config_File());
			getGUI().addToLog("generated configuration", true);
		}
		
		// tasks
		if (data.getTasks().size() > 0) {
			for (TaskContainer c : data.getTasks()) {
				data.getBuildFiles().add(builder.build_Task_File(c));
			}
		}
		getGUI().addToLog("generated tasks",true);
		
		//<Pluginname>.java
		data.getBuildFiles().add(builder.build_MainClass_File());
		
		getGUI().addToLog("generated main class:",true);
		
		data.removeNullFiles();
		
		long timedelta = System.currentTimeMillis()-timestart;
		getGUI().addToLog("created classes. Time past: "+timedelta+"ms",true);
		
		creater.createProject(dir, showFolder);
	}

	public String getVersion() {
		return VERSION;
	}

	public ListenerData getListenerData() {
		return listener_data;
	}
}
