package ua.endertainment.quartzdefenders.kitsapi;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class KitConfig {
	
	private File file;
	private FileConfiguration cfg;
	
	public KitConfig(QuartzDefendersKitsAPI plugin) {		
		file = new File(plugin.getDataFolder() + File.separator, "kits.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        cfg = YamlConfiguration.loadConfiguration(file);
        if (!cfg.isConfigurationSection("Games")) {
            cfg.createSection("Games");
            saveKitsConfig();
        }
	}
	
	public void saveKitsConfig() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        cfg = YamlConfiguration.loadConfiguration(file);
    }
	
	public FileConfiguration getKitsConfig() {
		return cfg;
	}
}
