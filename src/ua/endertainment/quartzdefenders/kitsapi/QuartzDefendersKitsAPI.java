package ua.endertainment.quartzdefenders.kitsapi;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import ua.endertainment.quartzdefenders.QuartzDefenders;

public class QuartzDefendersKitsAPI extends JavaPlugin {

	private static QuartzDefendersKitsAPI main;
	public static QuartzDefendersKitsAPI getInstance() {
		return main;
	}	
	 
	private Plugin quartzDefendersPlugin;
	
	private KitConfig kitConfig;
	
	private List<Kit> kits;
	
	@Override
	public void onEnable() {
		main = this;
		
		this.quartzDefendersPlugin = Bukkit.getPluginManager().getPlugin("QuartzDefenders");
		
		if(quartzDefendersPlugin == null) {
			loadFail();
			return;
		}
		
		if(!(quartzDefendersPlugin instanceof QuartzDefenders)) {
			loadFail();
			return;
		}
		
		QuartzDefenders.hook(main);
		
		this.kits = new ArrayList<>();
		
		this.kitConfig = new KitConfig(this);
		
		this.loadKitsFromConfig();
		
	}
	
	
	@Override
	public void onDisable() {
		
		QuartzDefenders.unhook(main);
		main = null;
	}
	
	
	public void loadKitsFromConfig() {
		if(!getKitConfig().isConfigurationSection("kits")) {
			getKitConfig().createSection("kits");
			loadFail();
			return;
		}		
		for(String kitID : getKitConfig().getConfigurationSection("kits").getKeys(false)) {
			kits.add(new Kit(kitID));
		}
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Kits loading success. Loaded " + kits.size() + " kits.");
	}
	
	private void loadFail() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error while loading plugin. QuartzDefenders plugin does not exist.");
		this.getPluginLoader().disablePlugin(main);
	}
	
	public FileConfiguration getKitConfig() {
		return kitConfig.getKitsConfig();
	}
	
	public List<Kit> getLoadedKits() {
		return kits;
	}
}
