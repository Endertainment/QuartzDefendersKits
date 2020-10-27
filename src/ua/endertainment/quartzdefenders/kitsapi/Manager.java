package ua.endertainment.quartzdefenders.kitsapi;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Manager {
	
	
	private QuartzDefendersKitsAPI plugin;
	private List<KitA> kits;

	public Manager(QuartzDefendersKitsAPI plugin) {
		this.plugin = plugin;
		this.kits = new ArrayList<>();
		
		this.loadKitsFromConfig();
		
		this.registerKits();
	}

	
	public List<KitA> getLoadedKits() {
		return kits;
	}
	
	private void registerKits() {
		for(KitA kit : (List<KitA>)kits)
			ua.endertainment.quartzdefenders.QuartzDefenders.getInstance().getKitManager().registerKit(kit, plugin);
	}
	
	private void loadKitsFromConfig() {
		if(!plugin.getKitConfig().isConfigurationSection("kits")) {
			plugin.getKitConfig().createSection("kits");
			plugin.loadFail();
			return;
		}		
		for(String kitID : plugin.getKitConfig().getConfigurationSection("kits").getKeys(false)) {
			try {
				kits.add(new KitA(kitID));
			} catch(Exception e) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Kit " + kitID + " load failed.");
			}
		}
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Kits loading finished. Loaded " + kits.size()
					+ " of " + plugin.getKitConfig().getConfigurationSection("kits").getKeys(false).size() + " kits.");
		
	}
	
}
