package ua.endertainment.quartzdefenders.kitsapi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class QuartzDefendersKitsAPI extends JavaPlugin {

	private static QuartzDefendersKitsAPI main;
	public static QuartzDefendersKitsAPI getInstance() {
		return main;
	}	
	 
	private Plugin quartzDefendersPlugin;
	
	private KitConfig kitConfig;
	
	Manager mng;
		
	@Override
	public void onEnable() {
		main = this;
		
		this.quartzDefendersPlugin = Bukkit.getPluginManager().getPlugin("QuartzDefenders");
		
		if(quartzDefendersPlugin == null) {
			loadFail();
			return;
		}
		
		if(!(quartzDefendersPlugin instanceof ua.endertainment.quartzdefenders.QuartzDefenders)) {
			loadFail();
			return;
		}
		
		ua.endertainment.quartzdefenders.QuartzDefenders.hook(main);		
		
		this.kitConfig = new KitConfig(this);		
		
		this.mng = new Manager(this);
	}
	
	
	@Override
	public void onDisable() {
		if(quartzDefendersPlugin != null) {
			ua.endertainment.quartzdefenders.QuartzDefenders.unhook(main);
		}		
		main = null;
	}
		
	void loadFail() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error while loading plugin. QuartzDefenders plugin does not exist.");
		this.getPluginLoader().disablePlugin(main);
	}
	
	public FileConfiguration getKitConfig() {
		return kitConfig.getKitsConfig();
	}
	
	
	
}
