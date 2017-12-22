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
	
	private List<KitA> kits;
	
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
		
		this.registerKits();
		
	}
	
	
	@Override
	public void onDisable() {
		if(quartzDefendersPlugin != null) {
			QuartzDefenders.unhook(main);
		}		
		main = null;
	}
	
	
	private void loadKitsFromConfig() {
		if(!getKitConfig().isConfigurationSection("kits")) {
			getKitConfig().createSection("kits");
			loadFail();
			return;
		}		
		for(String kitID : getKitConfig().getConfigurationSection("kits").getKeys(false)) {
			try {
				kits.add(new KitA(kitID));
			} catch(Exception e) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Kit " + kitID + " load failed.");
			}
		}
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Kits loading finished. Loaded " + kits.size()
					+ " of " + getKitConfig().getConfigurationSection("kits").getKeys(false).size() + " kits.");
		
	}
	
	private void registerKits() {
		for(KitA kit : kits)
			QuartzDefenders.getInstance().getKitManager().registerKit(kit, main);
	}
	
	private void loadFail() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error while loading plugin. QuartzDefenders plugin does not exist.");
		this.getPluginLoader().disablePlugin(main);
	}
	
	public FileConfiguration getKitConfig() {
		return kitConfig.getKitsConfig();
	}
	
	public List<KitA> getLoadedKits() {
		return kits;
	}
}
