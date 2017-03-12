package de.philipgrabow.helpitem.listener;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinE implements Listener {
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onJoin(PlayerJoinEvent e) {
		File file = new File("plugins/HelpItem", "UUID.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		Player p = e.getPlayer();
		String uid = p.getUniqueId().toString();
		if(file.exists()) {
		} else {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(cfg.contains(e.getPlayer().getName() + ".UUID")) {
			if(cfg.getString(e.getPlayer().getName() + ".UUID").equalsIgnoreCase(uid)) {
				return;
			}
		}
		cfg.set(p.getName() + ".UUID", uid);
		try {
			cfg.save(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
