package ua.endertainment.quartzdefenders.kitsapi;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class KitItemA implements ua.endertainment.quartzdefenders.kits.KitItem {

	private String kitID;
	private String itemID;
	private Material material;
	private int amount;
	private short durability;
	private Map<Enchantment, Integer> enchantments;
	
	private ItemStack item;
	
	public KitItemA(String kitID, String itemID) {		
		FileConfiguration c = QuartzDefendersKitsAPI.getInstance().getKitConfig();
		
		this.kitID = kitID;
		this.itemID = itemID;
		this.material = Material.valueOf(c.getString("kits." + kitID + ".items." + itemID + ".material", "PRICE"));
		this.amount = c.getInt("kits." + kitID + ".items." + itemID + ".amount", 1);
		this.durability = (short) c.getInt("kits." + kitID + ".items." + itemID + ".durability", 1);
		
		this.item = new ItemStack(material);
		item.setAmount(amount);
		item.setDurability(durability);
		
		if(!c.isList("kits." + kitID + ".items." + itemID + ".enchantments")) return;
		
		this.enchantments = new HashMap<>();
		
		for(String s : c.getStringList("kits." + kitID + ".items." + itemID + ".enchantments")) {
			this.enchantments.put(Enchantment.getByName(s.split(":")[0]), Integer.parseInt(s.split(":")[1]));
		}	
		
		item.addUnsafeEnchantments(enchantments);        		
	}
	
	@Override
	public String getKitID() {
		return kitID;
	}
	
	@Override
	public String getItemID() {
		return itemID;
	}
	
	public Material getMaterial() {
		return material;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public short getDurability() {
		return durability;
	}
	
	public Map<Enchantment, Integer> getEnchantments() {
		return enchantments;
	}
	
	@Override
	public ItemStack getItem() {
		return item;
	}
}
