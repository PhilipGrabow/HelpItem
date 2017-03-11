package de.philipgrabow.helpitem;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.philipgrabow.helpitem.executor.ItemCreate;
import de.philipgrabow.helpitem.listener.InteractE;
import de.philipgrabow.helpitem.listener.InventoryClickE;

public class Main extends JavaPlugin {

	@Override
	public void onDisable() {
		this.getLogger().info("disabled!");
	}

	@Override
	public void onEnable() {
		registerCmd();
		registerListener();
		this.getLogger().info("enabled!");
	}
	public void registerCmd() {
		getCommand("helpitem").setExecutor(new ItemCreate());
	}
	public void registerListener() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new InteractE(), this);
		pm.registerEvents(new InventoryClickE(), this);
	}
	
	

}
