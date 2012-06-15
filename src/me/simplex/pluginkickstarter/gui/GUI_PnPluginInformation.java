package me.simplex.pluginkickstarter.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import me.simplex.pluginkickstarter.DataStorage;
import me.simplex.pluginkickstarter.gui.util.CommandListCellRenderer;
import me.simplex.pluginkickstarter.gui.util.ConfigNodeListCellRenderer;
import me.simplex.pluginkickstarter.gui.util.EventTable;
import me.simplex.pluginkickstarter.gui.util.TaskListCellRenderer;
import me.simplex.pluginkickstarter.storage.CommandContainer;
import me.simplex.pluginkickstarter.storage.ConfigurationNodeContainer;
import me.simplex.pluginkickstarter.storage.TaskContainer;
import me.simplex.pluginkickstarter.util.ListenerType;

public class GUI_PnPluginInformation extends JPanel {
	private GUI_Main_Window GUI;
	private static final long serialVersionUID = 1L;
	private JTabbedPane tpPluginData;
	private JPanel pnEvents;
	private JPanel pnCommands;
	private JPanel pnConfiguration;
	
	private JTabbedPane tpEvents;
	
	private JPanel pnPlayerEvents;
	private JPanel pnBlockEvents;
	private JPanel pnEntityEvents;
	private JPanel pnWorldEvents;
	private JPanel pnWeatherEvents;
	private JPanel pnInventoryEvents;
	private JPanel pnVehicleEvents;
	private JPanel pnServerEvents;
	private JPanel pnPaintingEvents;
	private JPanel pnEnchantmentEvents;
	
	private EventTable tblBlockEvents;
	private EventTable tblEntityEvents;
	private EventTable tblInventoryEvents;
	private EventTable tblPlayerEvents;
	private EventTable tblPaintingEvents;
	private EventTable tblServerEvents;
	private EventTable tblVehicleEvents;
	private EventTable tblWeatherEvents;
	private EventTable tblWorldEvents;
	private EventTable tblEnchantmentEvents;
	
	private JScrollPane spBlock;
	private JScrollPane spPlayer;
	private JScrollPane spEntity;
	private JScrollPane spWorld;
	private JScrollPane spWeather;
	private JScrollPane spInventory;
	private JScrollPane spVehicle;
	private JScrollPane spServer;
	private JScrollPane spPainting;
	private JScrollPane spEnchantment;
	
	private JLabel lblSelectTheEvents;
	private JPanel pnTasks;
	private JScrollPane spCommands;
	private JList listCommands;
	private JPanel pnCommandList;
	private JPanel pnCommandLabel;
	private JButton btNewCommand;
	private JButton btRemoveCommand;
	private JLabel lblItsSimpleWe;

	private DefaultListModel command_list_data;
	private DefaultListModel task_list_data;
	private DefaultListModel confignode_list_data;
	
	private JButton btEditCommand;
	private JPanel pnScroll;
	private JScrollPane spTasks;
	private JPanel pnTasksBtns;
	private JButton btNewTask;
	private JButton btEditTask;
	private JButton btRemoveTask;
	private JPanel pnTasksLabel;
	private JLabel lbTask;
	private JPanel pnTasksScroll;
	private JList listTasks;
	private JPanel pnButtons;
	private JPanel pnBtnConfig;
	private JPanel pnConfigTop;
	private JButton btnNewNode;
	private JButton btEditNode;
	private JButton btRemoveNode;
	private JLabel lbConfigDesc;
	private JLabel lbConfigHeader;
	private JTextField tfConfigHeaderline;
	private JPanel pnConfigTree;
	private JScrollPane spConfigTree;
	private JList listConfig;
	private JPanel pnScrollConfig;
	private JPanel pnResetConfigSel;
		
	public GUI_PnPluginInformation(GUI_Main_Window GUI) {
		this.GUI = GUI;
		initialize();
	}
	private void initialize() {
		this.setSize(800, 372);
		this.setLayout(null);
		this.setEnabled(true);
		this.add(getTpPluginData());
	}
	private JTabbedPane getTpPluginData() {
		if (tpPluginData == null) {
			tpPluginData = new JTabbedPane(JTabbedPane.TOP);
			tpPluginData.setBounds(10, 11, 780, 349);
			tpPluginData.addTab("Events", null, getPnEvents(), null);
			tpPluginData.addTab("Commands", null, getPnCommands(), null);
			tpPluginData.addTab("Configuration", null, getPnConfiguration(), null);
			tpPluginData.addTab("Tasks", null, getPnTasks(), null);
		}
		return tpPluginData;
	}
	
	public void updateTabs(){
		DataStorage s = GUI.getMain().getData();
		if (!s.isGen_listener()) {
			tpPluginData.setEnabledAt(0, false);
		}
		else {
			tpPluginData.setEnabledAt(0, true);
		}
		if (!s.isGen_commands()) {
			tpPluginData.setEnabledAt(1, false);
		}
		else {
			tpPluginData.setEnabledAt(1, true);
		}
		if (!s.isGen_configuration()) {
			tpPluginData.setEnabledAt(2, false);
		}
		else {
			tpPluginData.setEnabledAt(2, true);
		}
		if (!s.isGen_tasks()) {
			tpPluginData.setEnabledAt(3, false);
		}
		else {
			tpPluginData.setEnabledAt(3, true);
		}
	}
	
	public void updateConfigMsg(){
		GUI.getMain().getData().setCfgfileheader(tfConfigHeaderline.getText());
	}
	
	private JPanel getPnEvents() {
		if (pnEvents == null) {
			pnEvents = new JPanel();
			pnEvents.setLayout(new BorderLayout(0, 0));
			pnEvents.add(getLblSelectTheEvents(), BorderLayout.NORTH);
			pnEvents.add(getTpEvents());
		}
		return pnEvents;
	}
	private JPanel getPnCommands() {
		if (pnCommands == null) {
			pnCommands = new JPanel();
			pnCommands.setLayout(new BorderLayout(0, 0));
			pnCommands.add(getPnCommandLabel(), BorderLayout.NORTH);
			pnCommands.add(getPnButtons(), BorderLayout.EAST);
			pnCommands.add(getPnCommandList(), BorderLayout.CENTER);
		}
		return pnCommands;
	}
	private JPanel getPnConfiguration() {
		if (pnConfiguration == null) {
			pnConfiguration = new JPanel();
			pnConfiguration.setLayout(new BorderLayout(0, 0));
			pnConfiguration.add(getPnBtnConfig(), BorderLayout.EAST);
			pnConfiguration.add(getPnConfigTop(), BorderLayout.NORTH);
			pnConfiguration.add(getPnConfigTree(), BorderLayout.CENTER);
		}
		return pnConfiguration;
	}
	private JTabbedPane getTpEvents() {
		if (tpEvents == null) {
			tpEvents = new JTabbedPane(JTabbedPane.TOP);
			tpEvents.setBorder(new EmptyBorder(5, 5, 5, 5));
			tpEvents.addTab("Block events", null, getPnBlockEvents(), null);
			tpEvents.addTab("Entity events", null, getPnEntityEvents(), null);
			tpEvents.addTab("Inventory events", null, getPnInventoryEvents(), null);
			tpEvents.addTab("Enchantment events", null, getPnEnchantmentEvents(), null);
			tpEvents.addTab("Player events", null, getPnPlayerEvents(), null);
			tpEvents.addTab("Painting events", null, getPnPaintingEvents(), null);
			tpEvents.addTab("Vehicle events", null, getPnVehicleEvents(), null);
			tpEvents.addTab("Server events", null, getPnServerEvents(), null);
			tpEvents.addTab("Weather events", null, getPnWeatherEvents(), null);
			tpEvents.addTab("World events", null, getPnWorldEvents(), null);
		}
		return tpEvents;
	}
	
	private JPanel getPnPlayerEvents() {
		if (pnPlayerEvents == null) {
			pnPlayerEvents = new JPanel();
			pnPlayerEvents.setLayout(new BorderLayout(0, 0));
			pnPlayerEvents.add(getSpPlayer(), BorderLayout.CENTER);
		}
		return pnPlayerEvents;
	}
	
	private JPanel getPnEnchantmentEvents(){
		if(pnEnchantmentEvents == null){
			pnEnchantmentEvents = new JPanel();
			pnEnchantmentEvents.setLayout(new BorderLayout(0, 0));
			pnEnchantmentEvents.add(getSpEnchantment(), BorderLayout.CENTER);
		}
		return pnEnchantmentEvents;
	}
	
	private JPanel getPnPaintingEvents() {
		if (pnPaintingEvents == null) {
			pnPaintingEvents = new JPanel();
			pnPaintingEvents.setLayout(new BorderLayout(0, 0));
			pnPaintingEvents.add(getSpPainting(), BorderLayout.CENTER);
		}
		return pnPaintingEvents;
	}
	
	private JPanel getPnBlockEvents() {
		if (pnBlockEvents == null) {
			pnBlockEvents = new JPanel();
			pnBlockEvents.setLayout(new BorderLayout(0, 0));
			pnBlockEvents.add(getSpBlock(), BorderLayout.CENTER);
		}
		return pnBlockEvents;
	}
	private JPanel getPnEntityEvents() {
		if (pnEntityEvents == null) {
			pnEntityEvents = new JPanel();
			pnEntityEvents.setLayout(new BorderLayout(0, 0));
			pnEntityEvents.add(getSpEntity(), BorderLayout.CENTER);
		}
		return pnEntityEvents;
	}
	private JPanel getPnWorldEvents() {
		if (pnWorldEvents == null) {
			pnWorldEvents = new JPanel();
			pnWorldEvents.setLayout(new BorderLayout(0, 0));
			pnWorldEvents.add(getSpWorld(), BorderLayout.CENTER);
		}
		return pnWorldEvents;
	}
	private JPanel getPnWeatherEvents() {
		if (pnWeatherEvents == null) {
			pnWeatherEvents = new JPanel();
			pnWeatherEvents.setLayout(new BorderLayout(0, 0));
			pnWeatherEvents.add(getSpWeather(), BorderLayout.CENTER);
		}
		return pnWeatherEvents;
	}
	private JPanel getPnInventoryEvents() {
		if (pnInventoryEvents == null) {
			pnInventoryEvents = new JPanel();
			pnInventoryEvents.setLayout(new BorderLayout(0, 0));
			pnInventoryEvents.add(getSpInventory(), BorderLayout.CENTER);
		}
		return pnInventoryEvents;
	}
	private EventTable getTblBlockEvents() {
		if (tblBlockEvents == null) {
			tblBlockEvents = new EventTable(ListenerType.Block, GUI);
		}
		return tblBlockEvents;
	}
	
	private EventTable getTblEntityEvents() {
		if (tblEntityEvents == null) {
			tblEntityEvents = new EventTable(ListenerType.Entity, GUI);
		}
		return tblEntityEvents;
	}
	
	private EventTable getTblPaintingEvents() {
		if (tblPaintingEvents == null) {
			tblPaintingEvents = new EventTable(ListenerType.Painting, GUI);
		}
		return tblPaintingEvents;
	}
	
	private EventTable getTblInventoryEvents() {
		if (tblInventoryEvents == null) {
			tblInventoryEvents = new EventTable(ListenerType.Inventory, GUI);
		}
		return tblInventoryEvents;
	}
	
	private EventTable getTblWeatherEvents() {
		if (tblWeatherEvents == null) {
			tblWeatherEvents = new EventTable(ListenerType.Weather, GUI);
		}
		return tblWeatherEvents;
	}
	
	private EventTable getTblWorldEvents() {
		if (tblWorldEvents == null) {
			tblWorldEvents = new EventTable(ListenerType.World, GUI);
		}
		return tblWorldEvents;
	}
	
	private EventTable getTblServerEvents() {
		if (tblServerEvents == null) {
			tblServerEvents = new EventTable(ListenerType.Server, GUI);
		}
		return tblServerEvents;
	}
	
	private EventTable getTblVehicleEvents() {
		if (tblVehicleEvents == null) {
			tblVehicleEvents = new EventTable(ListenerType.Vehicle, GUI);
		}
		return tblVehicleEvents;
	}
	
	private EventTable getTblPlayerEvents() {
		if (tblPlayerEvents == null) {
			tblPlayerEvents = new EventTable(ListenerType.Player, GUI);
		}
		return tblPlayerEvents;
	}
	
	private EventTable getTblEnchantmentEvents(){
		if(tblEnchantmentEvents == null){
			tblEnchantmentEvents = new EventTable(ListenerType.Enchantment, GUI);
		}
		return tblEnchantmentEvents;
	}
	
	private JScrollPane getSpBlock() {
		if (spBlock == null) {
			spBlock = new JScrollPane();
			spBlock.setViewportView(getTblBlockEvents());
		}
		return spBlock;
	}
	private JPanel getPnVehicleEvents() {
		if (pnVehicleEvents == null) {
			pnVehicleEvents = new JPanel();
			pnVehicleEvents.setLayout(new BorderLayout(0, 0));
			pnVehicleEvents.add(getSpVehicle(), BorderLayout.CENTER);
		}
		return pnVehicleEvents;
	}
	private JPanel getPnServerEvents() {
		if (pnServerEvents == null) {
			pnServerEvents = new JPanel();
			pnServerEvents.setLayout(new BorderLayout(0, 0));
			pnServerEvents.add(getSpServer(), BorderLayout.NORTH);
		}
		return pnServerEvents;
	}
	private JScrollPane getSpPlayer() {
		if (spPlayer == null) {
			spPlayer = new JScrollPane();
			spPlayer.setViewportView(getTblPlayerEvents());
		}
		return spPlayer;
	}
	private JScrollPane getSpEnchantment(){
		if(spEnchantment == null){
			spEnchantment = new JScrollPane();
			spEnchantment.setViewportView(getTblEnchantmentEvents());
		}
		return spEnchantment;
	}
	private JScrollPane getSpPainting() {
		if (spPainting == null) {
			spPainting = new JScrollPane();
			spPainting.setViewportView(getTblPaintingEvents());
		}
		return spPainting;
	}
	private JScrollPane getSpEntity() {
		if (spEntity == null) {
			spEntity = new JScrollPane();
			spEntity.setViewportView(getTblEntityEvents());
		}
		return spEntity;
	}
	private JScrollPane getSpWorld() {
		if (spWorld == null) {
			spWorld = new JScrollPane();
			spWorld.setViewportView(getTblWorldEvents());
		}
		return spWorld;
	}
	private JScrollPane getSpWeather() {
		if (spWeather == null) {
			spWeather = new JScrollPane();
			spWeather.setViewportView(getTblWeatherEvents());
		}
		return spWeather;
	}
	private JScrollPane getSpInventory() {
		if (spInventory == null) {
			spInventory = new JScrollPane();
			spInventory.setViewportView(getTblInventoryEvents());
		}
		return spInventory;
	}
	private JScrollPane getSpVehicle() {
		if (spVehicle == null) {
			spVehicle = new JScrollPane();
			spVehicle.setViewportView(getTblVehicleEvents());
		}
		return spVehicle;
	}
	private JScrollPane getSpServer() {
		if (spServer == null) {
			spServer = new JScrollPane();
			spServer.setViewportView(getTblServerEvents());
		}
		return spServer;
	}
	private JLabel getLblSelectTheEvents() {
		if (lblSelectTheEvents == null) {
			lblSelectTheEvents = new JLabel("Select the events you want to listen to.");
			lblSelectTheEvents.setBorder(new EmptyBorder(5, 5, 5, 0));
		}
		return lblSelectTheEvents;
	}
	private JPanel getPnTasks() {
		if (pnTasks == null) {
			pnTasks = new JPanel();
			pnTasks.setLayout(new BorderLayout(0, 0));
			pnTasks.add(getSpTasks(), BorderLayout.CENTER);
			pnTasks.add(getPnTasksBtns(), BorderLayout.EAST);
			pnTasks.add(getPnTasksLabel(), BorderLayout.NORTH);
		}
		return pnTasks;
	}
	private JScrollPane getSpCommands() {
		if (spCommands == null) {
			spCommands = new JScrollPane();
			spCommands.setViewportView(getPnScroll());
			spCommands.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		}
		return spCommands;
	}
	private JList getListCommands() {
		if (listCommands == null) {
			command_list_data = new DefaultListModel();
			listCommands = new JList(command_list_data);
			listCommands.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listCommands.setCellRenderer(new CommandListCellRenderer());
			listCommands.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (listCommands.getSelectedValue() != null) {

						getBtEditCommand().setEnabled(true);
						getBtRemoveCommand().setEnabled(true);
					}
					else {
						getBtEditCommand().setEnabled(false);
						getBtRemoveCommand().setEnabled(false);
					}
			          for(int x = 0; x < command_list_data.size(); x++) 
			          { 
			        	  command_list_data.setElementAt(command_list_data.getElementAt(x),x); 
			          } 
					
				}
			});
		}
		return listCommands;
	}
	private JPanel getPnCommandList() {
		if (pnCommandList == null) {
			pnCommandList = new JPanel();
			pnCommandList.setBorder(null);
			pnCommandList.setLayout(new BorderLayout(0, 0));
			pnCommandList.add(getSpCommands(), BorderLayout.CENTER);
			
		}
		return pnCommandList;
	}
	private JPanel getPnCommandLabel() {
		if (pnCommandLabel == null) {
			pnCommandLabel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnCommandLabel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnCommandLabel.add(getLblItsSimpleWe());
		}
		return pnCommandLabel;
	}
	private JButton getBtNewCommand() {
		if (btNewCommand == null) {
			btNewCommand = new JButton("New Command");
			btNewCommand.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					performClickNewCmd();
				}
			});
		}
		return btNewCommand;
	}
		
	private JButton getBtRemoveCommand() {
		if (btRemoveCommand == null) {
			btRemoveCommand = new JButton("Remove Command");
			btRemoveCommand.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int input = JOptionPane.showConfirmDialog(GUI, "Delete this command?", "Confirm command delete", JOptionPane.OK_CANCEL_OPTION);
					if (input != 0) {
						return;
					}
					CommandContainer c = (CommandContainer)getListCommands().getSelectedValue();
					GUI.getMain().getData().getCommands().remove(c);
					command_list_data.removeElement(c);
					
				}
			});
			
			btRemoveCommand.setEnabled(false);
		}
		return btRemoveCommand;
	}
	private JLabel getLblItsSimpleWe() {
		if (lblItsSimpleWe == null) {
			lblItsSimpleWe = new JLabel("It's simple. We kill the batman. You may add some commands here also.");
		}
		return lblItsSimpleWe;
	}

	private JButton getBtEditCommand() {
		if (btEditCommand == null) {
			btEditCommand = new JButton("Edit Command");
			btEditCommand.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

				performClickEditCommand();
				}
			});
			btEditCommand.setEnabled(false);
		}
		return btEditCommand;
	}
	private JPanel getPnScroll() {
		if (pnScroll == null) {
			pnScroll = new JPanel();
			pnScroll.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
						getListCommands().clearSelection();
				}
			});
			pnScroll.setLayout(new BorderLayout(0, 0));
			pnScroll.add(getListCommands(), BorderLayout.NORTH);
		}
		return pnScroll;
	}
	private JScrollPane getSpTasks() {
		if (spTasks == null) {
			spTasks = new JScrollPane();
			spTasks.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			spTasks.setViewportView(getPnTasksScroll());
		}
		return spTasks;
	}
	private JPanel getPnTasksBtns() {
		if (pnTasksBtns == null) {
			pnTasksBtns = new JPanel();
			pnTasksBtns.setBorder(new EmptyBorder(5, 5, 0, 5));
			GridBagLayout gbl_pnTasksBtns = new GridBagLayout();
			gbl_pnTasksBtns.columnWidths = new int[]{0, 0};
			gbl_pnTasksBtns.rowHeights = new int[]{0, 0, 0, 0, 0};
			gbl_pnTasksBtns.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_pnTasksBtns.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			pnTasksBtns.setLayout(gbl_pnTasksBtns);
			GridBagConstraints gbc_btNewTask = new GridBagConstraints();
			gbc_btNewTask.fill = GridBagConstraints.HORIZONTAL;
			gbc_btNewTask.insets = new Insets(0, 0, 5, 0);
			gbc_btNewTask.gridx = 0;
			gbc_btNewTask.gridy = 0;
			pnTasksBtns.add(getBtNewTask(), gbc_btNewTask);
			GridBagConstraints gbc_btEditTask = new GridBagConstraints();
			gbc_btEditTask.fill = GridBagConstraints.HORIZONTAL;
			gbc_btEditTask.insets = new Insets(0, 0, 5, 0);
			gbc_btEditTask.gridx = 0;
			gbc_btEditTask.gridy = 1;
			pnTasksBtns.add(getBtEditTask(), gbc_btEditTask);
			GridBagConstraints gbc_btRemoveTask = new GridBagConstraints();
			gbc_btRemoveTask.insets = new Insets(0, 0, 5, 0);
			gbc_btRemoveTask.gridx = 0;
			gbc_btRemoveTask.gridy = 2;
			pnTasksBtns.add(getBtRemoveTask(), gbc_btRemoveTask);
		}
		return pnTasksBtns;
	}
	private JButton getBtNewTask() {
		if (btNewTask == null) {
			btNewTask = new JButton("New Task");
			btNewTask.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					performClickNewTask();
				}
			});
		}
		return btNewTask;
	}
	private void performClickNewTask(){
		TaskContainer c = new TaskContainer();
		GUI.getMain().getData().getTasks().add(c);
		task_list_data.addElement(c);
		new GUI_TaskEditor(this, false, c);
	}
	
	private void performClickNewCmd(){
		CommandContainer c = new CommandContainer();
		GUI.getMain().getData().getCommands().add(c);
		command_list_data.addElement(c);
		new GUI_CommandEditor(this, false, c);
	}
	
	private void performClickNewNode(){
		ConfigurationNodeContainer c = new ConfigurationNodeContainer();
		GUI.getMain().getData().getConfigNodes().add(c);
		
		confignode_list_data.addElement(c);
		
		new GUI_ConfigurationNodeEditor(this, false, c);
	}
	
	private JButton getBtEditTask() {
		if (btEditTask == null) {
			btEditTask = new JButton("Edit Task");
			btEditTask.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					performClickEditTask();
				}
			});
			btEditTask.setEnabled(false);
		}
		return btEditTask;
	}
	
	private void performClickEditTask(){
		TaskContainer c = (TaskContainer) getListTasks().getSelectedValue();
		new GUI_TaskEditor(this, true, c);
	}
	

	private void performClickEditCommand(){
		CommandContainer c = (CommandContainer) getListCommands().getSelectedValue();
		new GUI_CommandEditor(this, true, c);
	}
	
	private void performClickEditNode(){
		ConfigurationNodeContainer c = (ConfigurationNodeContainer) getListConfig().getSelectedValue();
		new GUI_ConfigurationNodeEditor(this, true, c);
	}
	
	
	private JButton getBtRemoveTask() {
		if (btRemoveTask == null) {
			btRemoveTask = new JButton("Remove Task");
			btRemoveTask.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int input = JOptionPane.showConfirmDialog(GUI, "Delete this task?", "Confirm task delete", JOptionPane.OK_CANCEL_OPTION);
					if (input != 0) {
						return;
					}
					TaskContainer c = (TaskContainer) getListTasks().getSelectedValue();
					task_list_data.removeElement(c);
					GUI.getMain().getData().getTasks().remove(c);
				}
			});
			btRemoveTask.setEnabled(false);
		}
		return btRemoveTask;
	}
	private JPanel getPnTasksLabel() {
		if (pnTasksLabel == null) {
			pnTasksLabel = new JPanel();
			pnTasksLabel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			pnTasksLabel.add(getLbTask());
		}
		return pnTasksLabel;
	}
	private JLabel getLbTask() {
		if (lbTask == null) {
			lbTask = new JLabel("Derp me a river and create some tasks if you want");
		}
		return lbTask;
	}
	private JPanel getPnTasksScroll() {
		if (pnTasksScroll == null) {
			pnTasksScroll = new JPanel();
			pnTasksScroll.setLayout(new BorderLayout(0, 0));
			pnTasksScroll.add(getListTasks(), BorderLayout.NORTH);
			pnTasksScroll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getListTasks().clearSelection();
			}
			});
		}
		return pnTasksScroll;
	}
	private JList getListTasks() {
		if (listTasks == null) {
			listTasks = new JList();
			task_list_data = new DefaultListModel();
			listTasks.setModel(task_list_data);
			listTasks.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent arg0) {
					if (listTasks.getSelectedValue() != null) {
						btEditTask.setEnabled(true);
						btRemoveTask.setEnabled(true);
					}
					else {
						btEditTask.setEnabled(false);
						btRemoveTask.setEnabled(false);
					}
			          for(int x = 0; x < task_list_data.size(); x++) 
			          { 
			        	  task_list_data.setElementAt(task_list_data.getElementAt(x),x); 
			          } 
				}
			});
			listTasks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listTasks.setCellRenderer(new TaskListCellRenderer());
			
		}
		return listTasks;
	}
	

	
	public void updateTasks(){
		getListTasks().repaint();
	}
	
	public void cancelTask(TaskContainer c){
		task_list_data.removeElement(c);
		GUI.getMain().getData().getTasks().remove(c);
	}
	public GUI_Main_Window getGUI() {
		return GUI;
	}
	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			pnButtons.setBorder(new EmptyBorder(5, 5, 5, 5));
			GridBagLayout gbl_pnButtons = new GridBagLayout();
			gbl_pnButtons.columnWidths = new int[]{53, 0};
			gbl_pnButtons.rowHeights = new int[]{23, 0, 0, 0};
			gbl_pnButtons.columnWeights = new double[]{0.0, Double.MIN_VALUE};
			gbl_pnButtons.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			pnButtons.setLayout(gbl_pnButtons);
			GridBagConstraints gbc_btNewCommand = new GridBagConstraints();
			gbc_btNewCommand.fill = GridBagConstraints.HORIZONTAL;
			gbc_btNewCommand.insets = new Insets(0, 0, 5, 0);
			gbc_btNewCommand.anchor = GridBagConstraints.NORTH;
			gbc_btNewCommand.gridx = 0;
			gbc_btNewCommand.gridy = 0;
			pnButtons.add(getBtNewCommand(), gbc_btNewCommand);
			GridBagConstraints gbc_btEdit = new GridBagConstraints();
			gbc_btEdit.fill = GridBagConstraints.HORIZONTAL;
			gbc_btEdit.insets = new Insets(0, 0, 5, 0);
			gbc_btEdit.gridx = 0;
			gbc_btEdit.gridy = 1;
			pnButtons.add(getBtEditCommand(), gbc_btEdit);
			GridBagConstraints gbc_btRemoveCommand = new GridBagConstraints();
			gbc_btRemoveCommand.gridx = 0;
			gbc_btRemoveCommand.gridy = 2;
			pnButtons.add(getBtRemoveCommand(), gbc_btRemoveCommand);
		}
		return pnButtons;
	}
	public void updateCommands(){
		getListCommands().repaint();
	}
	
	public void cancelCommand(CommandContainer c){
		command_list_data.removeElement(c);
		GUI.getMain().getData().getCommands().remove(c);
	}
	private JPanel getPnBtnConfig() {
		if (pnBtnConfig == null) {
			pnBtnConfig = new JPanel();
			pnBtnConfig.setBorder(new EmptyBorder(5, 5, 5, 5));
			GridBagLayout gbl_pnBtnConfig = new GridBagLayout();
			gbl_pnBtnConfig.columnWidths = new int[]{0, 0};
			gbl_pnBtnConfig.rowHeights = new int[]{0, 0, 0, 0};
			gbl_pnBtnConfig.columnWeights = new double[]{0.0, Double.MIN_VALUE};
			gbl_pnBtnConfig.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			pnBtnConfig.setLayout(gbl_pnBtnConfig);
			GridBagConstraints gbc_btnNewNode = new GridBagConstraints();
			gbc_btnNewNode.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnNewNode.insets = new Insets(0, 0, 5, 0);
			gbc_btnNewNode.gridx = 0;
			gbc_btnNewNode.gridy = 0;
			pnBtnConfig.add(getBtnNewNode(), gbc_btnNewNode);
			GridBagConstraints gbc_btnEditNode = new GridBagConstraints();
			gbc_btnEditNode.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnEditNode.insets = new Insets(0, 0, 5, 0);
			gbc_btnEditNode.gridx = 0;
			gbc_btnEditNode.gridy = 1;
			pnBtnConfig.add(getBtEditNode(), gbc_btnEditNode);
			GridBagConstraints gbc_btnRemoveNode = new GridBagConstraints();
			gbc_btnRemoveNode.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnRemoveNode.gridx = 0;
			gbc_btnRemoveNode.gridy = 2;
			pnBtnConfig.add(getBtRemoveNode(), gbc_btnRemoveNode);
		}
		return pnBtnConfig;
	}
	private JPanel getPnConfigTop() {
		if (pnConfigTop == null) {
			pnConfigTop = new JPanel();
			pnConfigTop.setBorder(new EmptyBorder(5, 5, 5, 5));
			GridBagLayout gbl_pnConfigTop = new GridBagLayout();
			gbl_pnConfigTop.columnWidths = new int[]{60, 51, 0};
			gbl_pnConfigTop.rowHeights = new int[]{15, 0, 0};
			gbl_pnConfigTop.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_pnConfigTop.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			pnConfigTop.setLayout(gbl_pnConfigTop);
			GridBagConstraints gbc_lbConfigDesc = new GridBagConstraints();
			gbc_lbConfigDesc.gridwidth = 2;
			gbc_lbConfigDesc.insets = new Insets(0, 0, 5, 0);
			gbc_lbConfigDesc.anchor = GridBagConstraints.NORTHWEST;
			gbc_lbConfigDesc.gridx = 0;
			gbc_lbConfigDesc.gridy = 0;
			pnConfigTop.add(getLbConfigDesc(), gbc_lbConfigDesc);
			GridBagConstraints gbc_lbConfigHeader = new GridBagConstraints();
			gbc_lbConfigHeader.anchor = GridBagConstraints.WEST;
			gbc_lbConfigHeader.insets = new Insets(0, 0, 0, 5);
			gbc_lbConfigHeader.gridx = 0;
			gbc_lbConfigHeader.gridy = 1;
			pnConfigTop.add(getLbConfigHeader(), gbc_lbConfigHeader);
			GridBagConstraints gbc_tfConfigHeaderline = new GridBagConstraints();
			gbc_tfConfigHeaderline.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfConfigHeaderline.gridx = 1;
			gbc_tfConfigHeaderline.gridy = 1;
			pnConfigTop.add(getTfConfigHeaderline(), gbc_tfConfigHeaderline);
		}
		return pnConfigTop;
	}
	private JButton getBtnNewNode() {
		if (btnNewNode == null) {
			btnNewNode = new JButton("New Node");
			btnNewNode.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					performClickNewNode();
				}
			});
		}
		return btnNewNode;
	}
	private JButton getBtEditNode() {
		if (btEditNode == null) {
			btEditNode = new JButton("Edit Node");
			btEditNode.setEnabled(false);
			btEditNode.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					performClickEditNode();
				}
			});
		}
		return btEditNode;
	}
	private JButton getBtRemoveNode() {
		if (btRemoveNode == null) {
			btRemoveNode = new JButton("Remove Node");
			btRemoveNode.setEnabled(false);
			btRemoveNode.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int input = JOptionPane.showConfirmDialog(GUI, "Delete this node?", "Confirm node delete", JOptionPane.OK_CANCEL_OPTION);
					if (input != 0) {
						return;
					}
					ConfigurationNodeContainer c = (ConfigurationNodeContainer) getListTasks().getSelectedValue();
					confignode_list_data.removeElement(c);
					GUI.getMain().getData().getConfigNodes().remove(c);
				}
			});
		}
		return btRemoveNode;
	}
	private JLabel getLbConfigDesc() {
		if (lbConfigDesc == null) {
			lbConfigDesc = new JLabel("Configurate the configuration of the plugin");
		}
		return lbConfigDesc;
	}
	private JLabel getLbConfigHeader() {
		if (lbConfigHeader == null) {
			lbConfigHeader = new JLabel("config.yml headerline:");
			lbConfigHeader.setLabelFor(getTfConfigHeaderline());
		}
		return lbConfigHeader;
	}
	private JTextField getTfConfigHeaderline() {
		if (tfConfigHeaderline == null) {
			tfConfigHeaderline = new JTextField();
			tfConfigHeaderline.setColumns(10);
			tfConfigHeaderline.setText("MahPluginz configuration xD!!111 s0 l33t:");
		}
		return tfConfigHeaderline;
	}
	private JPanel getPnConfigTree() {
		if (pnConfigTree == null) {
			pnConfigTree = new JPanel();
			pnConfigTree.setLayout(new BorderLayout(0, 0));
			pnConfigTree.add(getSpConfigTree(), BorderLayout.CENTER);
		}
		return pnConfigTree;
	}
	private JScrollPane getSpConfigTree() {
		if (spConfigTree == null) {
			spConfigTree = new JScrollPane();
			spConfigTree.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			spConfigTree.setViewportView(getPnScrollConfig());
		}
		return spConfigTree;
	}
	public void cancelConfigNode(ConfigurationNodeContainer c) {
		confignode_list_data.removeElement(c);
		GUI.getMain().getData().getConfigNodes().remove(c);
	}
	
	public void updateConfig() {
		getListConfig().repaint();
	}
	
	private JList getListConfig() {
		if (listConfig == null) {
			listConfig = new JList();
			listConfig.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			confignode_list_data = new DefaultListModel();
			listConfig.setModel(confignode_list_data);
			listConfig.setCellRenderer(new ConfigNodeListCellRenderer());
			listConfig.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent arg0) {
					if (listConfig.getSelectedValue() != null) {
						btEditNode.setEnabled(true);
						btRemoveNode.setEnabled(true);
					}
					else {
						btEditNode.setEnabled(false);
						btRemoveNode.setEnabled(false);
					}
			          for(int x = 0; x < confignode_list_data.size(); x++) 
			          { 
			        	  confignode_list_data.setElementAt(confignode_list_data.getElementAt(x),x); 
			          } 
				}
			});
		}
		return listConfig;
	}
	private JPanel getPnScrollConfig() {
		if (pnScrollConfig == null) {
			pnScrollConfig = new JPanel();
			pnScrollConfig.setLayout(new BorderLayout(0, 0));
			pnScrollConfig.add(getListConfig(), BorderLayout.NORTH);
			pnScrollConfig.add(getPnResetConfigSel(), BorderLayout.CENTER);
		}
		return pnScrollConfig;
	}
	private JPanel getPnResetConfigSel() {
		if (pnResetConfigSel == null) {
			pnResetConfigSel = new JPanel();
			pnResetConfigSel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					listConfig.clearSelection();
				}
			});
		}
		return pnResetConfigSel;
	}
}