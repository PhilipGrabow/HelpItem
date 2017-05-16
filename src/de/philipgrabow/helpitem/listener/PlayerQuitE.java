package de.philipgrabow.helpitem.listener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitE implements Listener {
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onQuit(PlayerQuitEvent e) {
		//Letzten Login aufschreiben!
				Date today1 = Calendar.getInstance().getTime();
				SimpleDateFormat tm = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.GERMAN);
				  String reportTime = tm.format(today1);
				File file1 = new File("plugins/HelpItem", "LastLogin.yml");
				if(!(file1.exists())) {
					try {
						file1.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				FileConfiguration cfg1 = YamlConfiguration.loadConfiguration(file1);
				cfg1.set(e.getPlayer().getUniqueId().toString(), reportTime);
				try {
					cfg1.save(file1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	}

}
