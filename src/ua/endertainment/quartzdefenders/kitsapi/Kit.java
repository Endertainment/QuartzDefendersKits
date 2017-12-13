package ua.endertainment.quartzdefenders.kitsapi;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class Kit {

	private String kitID;
	private String name;
	private String colorName;
	private KitUnlockType unlockType;
	private int price;
	private int unlockLvl;
	// TODO Achievement unlock;
	private List<String> description;
	private List<KitItem> items;
	
	
	
	public Kit(String kitID) {
		FileConfiguration cfg = QuartzDefendersKitsAPI.getInstance().getKitConfig();
		if(!cfg.isConfigurationSection("kits")) {
			cfg.createSection("kits");
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error while loading kits. Check kits.yml and reload plugin");
			Bukkit.getPluginManager().disablePlugin(QuartzDefendersKitsAPI.getInstance());
			return;
		}
		
//		for(String kID : cfg.getConfigurationSection("kits").getKeys(false)) {
//			
//		}
		
		this.kitID = kitID;
		this.name = cfg.getString("kits." + kitID + ".name");
		this.colorName = cfg.getString("kits." + kitID + ".color_name");
		this.unlockType = KitUnlockType.valueOf(cfg.getString("kits." + kitID + ".unlock_type"));
		this.price = cfg.getInt("kits." + kitID + ".price");
		this.unlockLvl = cfg.getInt("kits." + kitID + ".unlock_lvl");
		
		this.description = cfg.getStringList("kits." + kitID + ".description");
		
		for(String itemID : cfg.getConfigurationSection("kits." + kitID + ".items").getKeys(false)) {
			items.add(new KitItem(kitID, itemID));
	    }
		
	}
	
	
	
	public enum KitUnlockType {
		PRICE, ACHIEVEMENT, GIFT
	}
	
}
