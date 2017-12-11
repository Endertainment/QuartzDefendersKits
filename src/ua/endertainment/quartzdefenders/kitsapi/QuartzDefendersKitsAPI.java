package ua.endertainment.quartzdefenders.kitsapi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class QuartzDefendersKitsAPI extends JavaPlugin {

	private QuartzDefendersKitsAPI main;
	public QuartzDefendersKitsAPI getInstance() {
		return main;
	}
	
	 
	private Plugin quartzDefenders;
	
	@Override
	public void onEnable() {
		this.main = this;
		
		this.quartzDefenders = Bukkit.getPluginManager().getPlugin("QuartzDefenders");
		
		if(quartzDefenders == null) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "ERRORE WHILE LOADING PLUGIN. QUARTZDEFENDERS PLUGIN DOES NOT EXIST.");
			Bukkit.getPluginManager().disablePlugin(main);
			return;
		}
		
	}
	
	
	@Override
	public void onDisable() {
		
		
		this.main = null;
	}
	
}
