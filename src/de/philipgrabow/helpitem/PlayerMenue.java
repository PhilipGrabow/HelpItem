package de.philipgrabow.helpitem;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PlayerMenue {

	public static void openInventory(Player p, String name) {
		org.bukkit.inventory.Inventory inv = Bukkit.createInventory(p, 54, name);
		loadItems(inv, name);
		p.openInventory(inv);
	}

	public static void loadItems(org.bukkit.inventory.Inventory inv, String name) {
		ItemStack skull = skull(name);
		ItemStack wheat = weizen();
		ItemStack dia = diamond();
		ItemStack tnts = tntblock();
		ItemStack gm0 = papiergm0(name);
		ItemStack gm1 = papiergm1(name);
		ItemStack gm2 = papiergm2(name);
		ItemStack gm3 = papiergm3(name);
		ItemStack compass = compass(name);
		ItemStack smaragd = smaragd(name);

		inv.setItem(0, skull);
		inv.setItem(1, dia);
		inv.setItem(2, wheat);
		inv.setItem(3, redglasspane());
		inv.setItem(4, redglasspane());
		inv.setItem(5, tnts);
		inv.setItem(6, compass);
		inv.setItem(7, redglasspane());
		inv.setItem(8, redglasspane());
		inv.setItem(9, redglasspane());
		inv.setItem(10, redglasspane());
		inv.setItem(11, gm0);
		inv.setItem(12, gm1);
		inv.setItem(13, gm2);
		inv.setItem(14, gm3);
		inv.setItem(15, redglasspane());
		inv.setItem(16, redglasspane());
		inv.setItem(17, redglasspane());
		inv.setItem(18, redglasspane());
		inv.setItem(19, redglasspane());
		inv.setItem(20, redglasspane());
		inv.setItem(21, redglasspane());
		inv.setItem(22, redglasspane());
		inv.setItem(23, redglasspane());
		inv.setItem(24, redglasspane());
		inv.setItem(25, redglasspane());
		inv.setItem(26, redglasspane());
		inv.setItem(27, redglasspane());
		inv.setItem(28, redglasspane());
		inv.setItem(29, redglasspane());
		inv.setItem(30, redglasspane());
		inv.setItem(31, redglasspane());
		inv.setItem(32, redglasspane());
		inv.setItem(33, redglasspane());
		inv.setItem(34, redglasspane());
		inv.setItem(35, redglasspane());
		inv.setItem(36, redglasspane());
		inv.setItem(37, redglasspane());
		inv.setItem(38, redglasspane());
		inv.setItem(39, redglasspane());
		inv.setItem(40, redglasspane());
		inv.setItem(41, redglasspane());
		inv.setItem(42, redglasspane());
		inv.setItem(43, redglasspane());
		inv.setItem(44, redglasspane());
		inv.setItem(45, smaragd);
		inv.setItem(46, redglasspane());
		inv.setItem(47, redglasspane());
		inv.setItem(48, redglasspane());
		inv.setItem(49, redglasspane());
		inv.setItem(50, redglasspane());
		inv.setItem(51, redglasspane());
		inv.setItem(52, redglasspane());
		inv.setItem(53, redglasspane());
		
	}
	public static ItemStack smaragd(String name) {
		ItemStack sm = new ItemStack(Material.EMERALD);
		ItemMeta meta = sm.getItemMeta();
		meta.setDisplayName("Aktueller Rang: ...");
		meta.addEnchant(Enchantment.DURABILITY, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		ArrayList<String> list = new ArrayList<>();
		if(PermissionsEx.isAvailable()) {
//			File file = new File("plugins/HelpItem", "UUID.yml");
//			FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
//			UUID uid = UUID.fromString(cfg.getString(name + ".UUID"));
//			Bukkit.broadcastMessage(name + ": " + uid.toString());
			@SuppressWarnings("deprecation")
			String group = PermissionsEx.getUser(name).getGroups()[0].getName();
			list.add(ChatColor.GREEN + "Aktueller Spieler-Rang auf diesem Server: ");
			list.add("Rang: " + group);
		} else {
			list.add("Nicht Verfügbar!");
		}
		meta.setLore(list);
		sm.setItemMeta(meta);
		return sm;
	}
	public static ItemStack redglasspane() {
		ItemStack redglass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);
		ItemMeta meta = redglass.getItemMeta();
		meta.setDisplayName(ChatColor.RED + "Keine Funktion!");
		redglass.setItemMeta(meta);
		return redglass;
	}
	public static ItemStack skull(String name) {
		ItemStack skullis = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		File file = new File("plugins/HelpItem", "UUID.yml");
		FileConfiguration cfg2 = YamlConfiguration.loadConfiguration(file);
		if (cfg2.contains(name)) {
			String uid = cfg2.getString(name + ".UUID");
			Player p = Bukkit.getPlayer(UUID.fromString(uid));
			
			SkullMeta meta = (SkullMeta) skullis.getItemMeta();
			meta.setOwner(p.getName());
			meta.setDisplayName(p.getName());
			ArrayList<String> list = new ArrayList<String>();
			list.add("§aLeben: §c§l" + p.getHealth() + "§a von §c§l20.0 §aLebenspunkten");
			list.add("§aHunger: §c§l" + p.getFoodLevel() + "§a von §c§l20 §aHungerpunkten");
			list.add("§aGamemode: §c§l" + p.getGameMode());
			list.add("§aFliegen erlaubt: §c§l" + p.getAllowFlight());
			list.add("§aOperator: §c§l" + p.isOp());
			list.add("§aWelt: §c§l" + p.getWorld().getName());
			File playerfile = new File("plugins/BuildcraftPrivat/PlayerOnlineTime", "OnlineTimes.yml");
			if (playerfile.exists()) {
				FileConfiguration cfg = YamlConfiguration.loadConfiguration(playerfile);
				String time = "OnlineTime." + p.getName();
				int onlinetime = cfg.getInt(time);
				list.add("§aOnlineZeit: §c§l" + onlinetime + " Minuten!");
			}
			list.add("§aUUID: §c§l" + p.getUniqueId().toString());
			File file1 = new File("plugins/HelpItem", "LastLogin.yml");
			if(file1.exists()) {
				FileConfiguration cfg1 = YamlConfiguration.loadConfiguration(file1);
				list.add("§aLetzter Login: §c§l" + cfg1.getString(p.getUniqueId().toString()));
			} else {
				list.add("§aLetzter Login:§c§l NICHT VORHANDEN!");
			}
			meta.setLore(list);
			skullis.setItemMeta(meta);
		}
		return skullis;
	}

	// Hunger stillen
	public static ItemStack weizen() {
		ItemStack wheat = new ItemStack(Material.WHEAT);
		ItemMeta metawheat = wheat.getItemMeta();
		metawheat.setDisplayName("Hunger stillen!");
		wheat.setItemMeta(metawheat);
		return wheat;
	}

	// Heilen
	public static ItemStack diamond() {
		ItemStack dia = new ItemStack(Material.DIAMOND);
		ItemMeta meta = dia.getItemMeta();
		meta.setDisplayName("Heilt den Spieler!");
		dia.setItemMeta(meta);
		return dia;
	}

	// INV leeren
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

	// GM 0
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

	// GM 1
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

	// GM 2
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

	// GM 3
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

	// Kompass (Koordinatenanzeiger)
	public static ItemStack compass(String name) {
		ItemStack is = new ItemStack(Material.COMPASS);
		ItemMeta meta = is.getItemMeta();
		meta.setDisplayName("KOORDINATEN:");
		ArrayList<String> list = new ArrayList<String>();
		File file = new File("plugins/HelpItem", "UUID.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		if (cfg.contains(name)) {
			String uid = cfg.getString(name + ".UUID");
			Player p2 = Bukkit.getPlayer(UUID.fromString(uid));
			list.add("§cSpieler: " + p2.getName());
			list.add("§cKoordinaten:");
			list.add("§cWelt: " + p2.getWorld().getName());
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