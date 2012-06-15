package me.simplex.pluginkickstarter.generator;

import java.util.ArrayList;

import me.simplex.pluginkickstarter.PluginKickstarter;
import me.simplex.pluginkickstarter.storage.ListenerContainer;
import me.simplex.pluginkickstarter.util.ListenerType;
import me.simplex.pluginkickstarter.util.PriorityType;

public class GenListeners extends Generator {
	private ListenerType type;
	private ArrayList<ListenerContainer> listeners;

	public GenListeners(PluginKickstarter main, ListenerType type) {
		super(main);
		this.type = type;
		listeners = new ArrayList<ListenerContainer>();
		for (ListenerContainer c : main.getData().getListener()) {
			if (c.getFile().equals(type)) {
				listeners.add(c);
			}
		}
		System.out.println(type+" has to handle: "+listeners.size());
	}

	@Override
	public String buildClassname() {
		return "Listener_"+StringToClassName(type.toString());
	}

	public String buildImports() {
		String ret = "";
			for (ListenerContainer c : listeners) {
				ret=ret+"import org.bukkit.event."+c.getFile().toString().toLowerCase()+"."+c.getEvent()+";\n";
			}
		return ret;
	}

	public String buildListeners() {
		String ret = "";
		for (ListenerContainer c : listeners) {
			ret=ret+"	@EventHandler" + getPriorityString(c) + "\n";
			ret=ret+"	public void "+c.getName()+"("+c.getEvent()+" event){"+"\n";
			ret=ret+"		// TODO handle that event\n";
			ret=ret+"	}\n\n";
		}
		return ret;
	}
	
	private String getPriorityString(ListenerContainer listener){
		PriorityType type = listener.getPriority();
		if(type == PriorityType.Normal) return "";
		
		String priorityString = "(priority  = EventPriority.";
		priorityString += type.name().toUpperCase() + ")";
				
		return priorityString;
	}
	
	public String buildListenerImport(){
		/*switch (type) {
		case Block: return "import org.bukkit.event.block.BlockListener;\n";
		case Entity: return "import org.bukkit.event.entity.EntityListener;\n";
		case Inventory: return "import org.bukkit.event.inventory.InventoryListener;\n";
		case Player:  return "import org.bukkit.event.player.PlayerListener;\n";
		case Server: return "import org.bukkit.event.server.ServerListener;\n";
		case Vehicle: return "import org.bukkit.event.vehicle.VehicleListener;\n";
		case Weather: return "import org.bukkit.event.weather.WeatherListener;\n";
		case World: return "import org.bukkit.event.world.WorldListener;\n";
		}*/
		String importString = "import org.bukkit.event.Listener; \n"
				             + "import org.bukkit.event.EventHandler; \n";
		
		for(ListenerContainer listener : listeners){
			if(listener.getPriority() != PriorityType.Normal){
				importString += "import org.bukkit.event.EventPriority; \n";
				break;
			}
		}
		
		return importString;
		
		//return "";
	}

	public CharSequence buildSuperclass() {
		/*switch (type) {
		case Block: return "Listener";
		case Entity: return "Listener";
		case Inventory: return "Listener";
		case Player:  return "Listener";
		case Server: return "Listener";
		case Vehicle: return "Listener";
		case Weather: return "Listener";
		case World: return "Listener";
		}
		return "";*/
		return "Listener";
	}
}