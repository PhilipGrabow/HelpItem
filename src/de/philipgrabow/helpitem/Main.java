package de.philipgrabow.helpitem;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.philipgrabow.helpitem.executor.HelpBookOverride;
import de.philipgrabow.helpitem.executor.ItemCreate;
import de.philipgrabow.helpitem.listener.InteractE;
import de.philipgrabow.helpitem.listener.InventoryClickE;
import de.philipgrabow.helpitem.listener.PlayerJoinE;
import de.philipgrabow.helpitem.listener.PlayerQuitE;

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
		getCommand("applybook").setExecutor(new HelpBookOverride());
	}
	public void registerListener() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerJoinE(), this);
		pm.registerEvents(new InteractE(this), this);
		pm.registerEvents(new InventoryClickE(), this);
		pm.registerEvents(new PlayerQuitE(), this);
	}
	
	

}
