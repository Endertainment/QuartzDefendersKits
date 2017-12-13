package ua.endertainment.quartzdefenders.kitsapi;

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
	
	@Override
	public void onEnable() {
		this.main = this;
		
		this.quartzDefendersPlugin = Bukkit.getPluginManager().getPlugin("QuartzDefenders");
		
		if(quartzDefendersPlugin == null) {
			loadFail();
			return;
		}
		
		if(!(quartzDefendersPlugin instanceof QuartzDefenders)) {
			loadFail();
			return;
		}
		
		this.kitConfig = new KitConfig(this);
		
		
	}
	
	
	@Override
	public void onDisable() {
		
		
		this.main = null;
	}
	
	
	private void loadFail() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error while loading plugin. QuartzDefenders plugin does not exist.");
		Bukkit.getPluginManager().disablePlugin(main);
	}
	
	public FileConfiguration getKitConfig() {
		return kitConfig.getKitsConfig();
	}
}
