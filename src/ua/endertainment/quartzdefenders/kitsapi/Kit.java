package ua.endertainment.quartzdefenders.kitsapi;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import ua.endertainment.quartzdefenders.game.GamePlayer;
import ua.endertainment.quartzdefenders.utils.ColorFormat;
import ua.endertainment.quartzdefenders.utils.ItemUtil;

public class Kit extends ua.endertainment.quartzdefenders.kits.Kit{

	public enum KitUnlockType {
		PRICE, ACHIEVEMENT, GIFT, PERMISSION
	}
	
	private String kitID;
	private String name;
	private String colorName;
	private KitUnlockType unlockType;
	private int price;
	private int unlockLvl;
	private String unlockAchievemet;
	private List<String> description;
	private List<KitItem> items;
	private ItemStack itemToRepresent;
	
	
	public Kit(String kitID) {
		FileConfiguration cfg = QuartzDefendersKitsAPI.getInstance().getKitConfig();
				
		this.kitID = kitID;
		this.name = cfg.getString("kits." + kitID + ".name", kitID + " name");
		this.colorName = cfg.getString("kits." + kitID + ".color_name", kitID + " color_name");
		this.unlockType = KitUnlockType.valueOf(cfg.getString("kits." + kitID + ".unlock_type", KitUnlockType.PRICE.toString()));
		this.price = cfg.getInt("kits." + kitID + ".price", 100);
		this.unlockLvl = cfg.getInt("kits." + kitID + ".unlock_lvl", 10);
		
		if(cfg.isList("kits." + kitID + ".description"))
		this.description = cfg.getStringList("kits." + kitID + ".description");
		
		for(String itemID : cfg.getConfigurationSection("kits." + kitID + ".items").getKeys(false)) {
			items.add(new KitItem(kitID, itemID));
	    }
		
		this.colorName = new ColorFormat(colorName).format();
		this.description = new ColorFormat(description).getFormatedList();
		
		this.itemToRepresent = ItemUtil.newItem(colorName, description, items.get(0).getMaterial(), 1, 0);
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
	
	public KitUnlockType getUnlockType() {
		return unlockType;
	}
	
	@Override
	public int getPrice() {
		return price;
	}
		
	public String getUnlockAchievemet() {
		return unlockAchievemet;
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
	public List<ItemStack> getItems() {
		List<ItemStack> l = new ArrayList<>();
		for(KitItem i : items) l.add(i.getItem());
		return l;
	}

	@Override
	public void apply(GamePlayer arg0) {
		// TODO Auto-generated method stub
		
	}

}
