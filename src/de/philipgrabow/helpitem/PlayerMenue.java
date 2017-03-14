package de.philipgrabow.helpitem;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerMenue {

	public static void openInventory(Player p, String name) {
		org.bukkit.inventory.Inventory inv = Bukkit.createInventory(p, 54, name);
		loadItems(inv, name);
		p.openInventory(inv);
	}

	public static void loadItems(org.bukkit.inventory.Inventory inv, String name) {
		ItemStack wheat = weizen();
		ItemStack dia = diamond();
		ItemStack tnts = tntblock();
		ItemStack gm0 = papiergm0(name);
		ItemStack gm1 = papiergm1(name);
		ItemStack gm2 = papiergm2(name);
		ItemStack gm3 = papiergm3(name);
		ItemStack compass = compass(name);

		inv.setItem(0, dia);
		inv.setItem(1, wheat);
		inv.setItem(4, tnts);
		inv.setItem(11, gm0);
		inv.setItem(12, gm1);
		inv.setItem(13, gm2);
		inv.setItem(14, gm3);
		inv.setItem(5, compass);

	}
	//Hunger stillen
	public static ItemStack weizen() {
		ItemStack wheat = new ItemStack(Material.WHEAT);
		ItemMeta metawheat = wheat.getItemMeta();
		metawheat.setDisplayName("Hunger stillen!");
		wheat.setItemMeta(metawheat);
		return wheat;
	}
	//Heilen
	public static ItemStack diamond() {
		ItemStack dia = new ItemStack(Material.DIAMOND);
		ItemMeta meta = dia.getItemMeta();
		meta.setDisplayName("Heilt den Spieler!");
		dia.setItemMeta(meta);
		return dia;
	}
	//INV leeren
	public static ItemStack tntblock() {
		ItemStack is = new ItemStack(Material.TNT);
		ItemMeta meta = is.getItemMeta();
		meta.setDisplayName("§cLeert das Inventar von diesem Spieler!");
		ArrayList<String> list = new ArrayList<String>();
		list.add("VORSICHTIG MIT");
		list.add("DIESER FUNKTION!");
		meta.setLore(list);
		meta.addEnchant(Enchantment.DURABILITY, 9999999, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		is.setItemMeta(meta);
		return is;
	}
	//GM 0
	public static ItemStack papiergm0(String name) {
		ItemStack is = new ItemStack(Material.PAPER);
		ItemMeta meta = is.getItemMeta();
		meta.setDisplayName("Gamemode Survival(0)");
		ArrayList<String> list = new ArrayList<String>();
		File file = new File("plugins/HelpItem", "UUID.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		if (cfg.contains(name)) {
			String uid = cfg.getString(name + ".UUID");
			Player p2 = Bukkit.getPlayer(UUID.fromString(uid));
			list.add("Aktueller Gamemode: " + p2.getGameMode());
			meta.setLore(list);

		}
		is.setItemMeta(meta);
		return is;
	}
	//GM 1
	public static ItemStack papiergm1(String name) {
		ItemStack is = new ItemStack(Material.PAPER);
		ItemMeta meta = is.getItemMeta();
		meta.setDisplayName("Gamemode Creative(1)");
		ArrayList<String> list = new ArrayList<String>();
		File file = new File("plugins/HelpItem", "UUID.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		if (cfg.contains(name)) {
			String uid = cfg.getString(name + ".UUID");
			Player p2 = Bukkit.getPlayer(UUID.fromString(uid));
			list.add("Aktueller Gamemode: " + p2.getGameMode());
			meta.setLore(list);
		}
		is.setItemMeta(meta);
		return is;
	}
	//GM 2
	public static ItemStack papiergm2(String name) {
		ItemStack is = new ItemStack(Material.PAPER);
		ItemMeta meta = is.getItemMeta();
		meta.setDisplayName("Gamemode Adventure(2)");
		ArrayList<String> list = new ArrayList<String>();
		File file = new File("plugins/HelpItem", "UUID.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		if (cfg.contains(name)) {
			String uid = cfg.getString(name + ".UUID");
			Player p2 = Bukkit.getPlayer(UUID.fromString(uid));
			list.add("Aktueller Gamemode: " + p2.getGameMode());
			meta.setLore(list);
		}
		is.setItemMeta(meta);
		return is;
	}
	//GM 3
	public static ItemStack papiergm3(String name) {
		ItemStack is = new ItemStack(Material.PAPER);
		ItemMeta meta = is.getItemMeta();
		meta.setDisplayName("Gamemode Spectator(3)");
		ArrayList<String> list = new ArrayList<String>();
		File file = new File("plugins/HelpItem", "UUID.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		if (cfg.contains(name)) {
			String uid = cfg.getString(name + ".UUID");
			Player p2 = Bukkit.getPlayer(UUID.fromString(uid));
			list.add("Aktueller Gamemode: " + p2.getGameMode());
			meta.setLore(list);
		}
		is.setItemMeta(meta);
		return is;
	}
	//Kompass (Koordinatenanzeiger)
	public static ItemStack compass(String name) {
		ItemStack is = new ItemStack(Material.COMPASS);
		ItemMeta meta = is.getItemMeta();
		meta.setDisplayName("KOORDINATEN:");
		ArrayList<String> list = new ArrayList<String>();
		File file = new File("plugins/HelpItem", "UUID.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		if(cfg.contains(name)) {
			String uid = cfg.getString(name + ".UUID");
			Player p2 = Bukkit.getPlayer(UUID.fromString(uid));
			list.add("§cSpieler: " + p2.getName());
			list.add("§cKoordinaten:");
			list.add("§cX: " + p2.getLocation().getBlockX());
			list.add("§cY: " + p2.getLocation().getBlockY());
			list.add("§cZ: " + p2.getLocation().getBlockZ());
			list.add("§cYAW: " + p2.getLocation().getYaw());
			list.add("§cPITCH: " + p2.getLocation().getPitch());
			list.add("§6§lLINKS-KLICK ZUM TELEPORT!!!");
			meta.setLore(list);
		}
		is.setItemMeta(meta);
		return is;
	}
}