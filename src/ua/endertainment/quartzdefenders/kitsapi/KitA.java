package ua.endertainment.quartzdefenders.kitsapi;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import ua.endertainment.quartzdefenders.game.GamePlayer;
import ua.endertainment.quartzdefenders.kits.Kit;
import ua.endertainment.quartzdefenders.kits.KitItem;
import ua.endertainment.quartzdefenders.kits.KitUnlockType;
import ua.endertainment.quartzdefenders.utils.ColorFormat;
import ua.endertainment.quartzdefenders.utils.ItemUtil;

public class KitA implements Kit {
	
	private String kitID;
	private String name;
	private String colorName;
	private KitUnlockType[] unlockTypes;
	private int price;
	private int unlockLvl;
	private String unlockAchievement;
	private String unlockPermission;
	private List<String> description;
	private List<KitItem> items;
	private ItemStack itemToRepresent;
	
	
	public KitA(String kitID) {
		FileConfiguration cfg = QuartzDefendersKitsAPI.getInstance().getKitConfig();
				
		this.items = new ArrayList<>();
		this.kitID = kitID;
		this.name = cfg.getString("kits." + kitID + ".name", kitID + " name");
		this.colorName = cfg.getString("kits." + kitID + ".color_name", kitID + " color_name");
		List<KitUnlockType> l = new ArrayList<>();
		for(String s : cfg.getStringList("kits." + kitID + ".unlock_types")) l.add(KitUnlockType.valueOf(s));
		this.unlockTypes = l.toArray(new KitUnlockType[l.size()]);		
		this.price = cfg.getInt("kits." + kitID + ".price", 100);
		this.unlockLvl = cfg.getInt("kits." + kitID + ".unlock_lvl", 10);
		this.unlockPermission = cfg.getString("kits." + kitID + ".unlock_permission", "QuartzDefendersKits.unlockPermission." + kitID);
		this.unlockAchievement = cfg.getString("kits." + kitID + ".unlock_achievement", "Achv");
		
		if(cfg.isList("kits." + kitID + ".description"))
		this.description = cfg.getStringList("kits." + kitID + ".description");
		
		for(String itemID : cfg.getConfigurationSection("kits." + kitID + ".items").getKeys(false)) {
			items.add(new KitItemA(kitID, itemID));
	    }
		
		this.colorName = new ColorFormat(colorName).format();
		this.description = new ColorFormat(description).getFormatedList();
		
		this.itemToRepresent = ItemUtil.newItem(colorName, description, items.get(0).getItem().getType(), 1, 0);
	}
	
	public String getKitID() {
		return kitID;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getDisplayName() {
		return colorName;
	}
	
	@Override
	public int getPrice() {
		return price;
	}
			
	@Override
	public List<String> getDescription() {
		return description;
	}

	@Override
	public int getLevel() {
		return unlockLvl;
	}

	@Override
	public ItemStack getPreviewItem() {
		return itemToRepresent;
	}

	@Override
	public List<KitItem> getItems() {
		return items;
	}

	@Override
	public void apply(GamePlayer arg0) {
		for(KitItem i : items) {
			arg0.getPlayer().getInventory().addItem(i.getItem());
		}
	}

	@Override
	public String getAchievement() {
		return unlockAchievement;
	}

	@Override
	public String getPermission() {
		return unlockPermission;
	}

	@Override
	public KitUnlockType[] getUnlockTypes() {
		return unlockTypes;
	}

}
