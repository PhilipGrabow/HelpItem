package de.philipgrabow.helpitem;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Inventory {

	@SuppressWarnings("deprecation")
	public static void openInventory(Player p) {
		org.bukkit.inventory.Inventory inv = Bukkit.createInventory(p, 54, "Hilfemenü");
		loadItems(inv);
		p.openInventory(inv);
		Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("HelpItem"), new Runnable() {

			@Override
			public void run() {
				p.updateInventory();
			}

		}, 100);
	}

	@SuppressWarnings("deprecation")
	public static void loadItems(org.bukkit.inventory.Inventory inv) {
		ItemStack goldenapple = new ItemStack(Material.GOLDEN_APPLE);
		ItemStack goldencarrot = new ItemStack(Material.GOLDEN_CARROT);
		ItemStack tnt = new ItemStack(Material.TNT);
		ItemStack dayclock = new ItemStack(Material.WATCH);
		ItemStack nightclock = new ItemStack(Material.WATCH);
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
		ItemStack paper = new ItemStack(Material.PAPER);
		ItemStack steve = new ItemStack(Material.SKULL_ITEM);
		ItemStack steve2 = new ItemStack(Material.SKULL_ITEM);
		book = HelpBook.loadBook(book);
		ItemMeta metaday = dayclock.getItemMeta();
		ItemMeta metanight = nightclock.getItemMeta();
		ItemMeta metaapple = goldenapple.getItemMeta();
		ItemMeta metacarrot = goldencarrot.getItemMeta();
		ItemMeta metatnt = tnt.getItemMeta();
		ItemMeta metapaper = paper.getItemMeta();
		ItemMeta metakopf = steve.getItemMeta();
		ItemMeta metakopf2 = steve2.getItemMeta();
		ArrayList<String> descriptionday = new ArrayList<String>();
		ArrayList<String> descriptionnight = new ArrayList<String>();
		ArrayList<String> descriptionpaper = new ArrayList<String>();
		ArrayList<String> descriptionkopf = new ArrayList<String>();
		ArrayList<String> descriptionkopf2 = new ArrayList<String>();
		descriptionday.add("Setzt die");
		descriptionday.add("Zeit auf Tag!");
		descriptionnight.add("Setzt die");
		descriptionnight.add("Zeit auf Nacht!");
		descriptionpaper.add("Du benutzt das Plugin 'HelpItem'!");
		descriptionpaper.add("Autor: Philip_Grabow");
		descriptionpaper.add("Du kannst nur die vorhandenen Item´s nutzen mit Linksklick!");
		descriptionpaper.add("Manche Items benötigen bestimmte Rechte!");
		descriptionpaper.add("Viel Spass beim Erforschen!");
		descriptionkopf.add("Setzt den Spieler auf Rang: MITGLIED!");
		descriptionkopf.add("Deoppt den Spieler!");
		descriptionkopf.add("NUR FÜR ADMINS!");
		descriptionkopf2.add("Setzt den Spieler auf Rang: Owner!");
		descriptionkopf2.add("Oppt den Spieler!");
		descriptionkopf2.add("NUR FÜR ADMINS!");
		metaday.setLore(descriptionday);
		metanight.setLore(descriptionnight);
		metapaper.setLore(descriptionpaper);
		metakopf.setLore(descriptionkopf);
		metakopf2.setLore(descriptionkopf2);
		metaday.setDisplayName(ChatColor.YELLOW + "Zeit: Tag!");
		metanight.setDisplayName(ChatColor.BLUE + "Zeit: Nacht!");
		metaapple.setDisplayName(ChatColor.RED + "Heilen!");
		metacarrot.setDisplayName(ChatColor.GOLD + "Deinen Hunger stillen!");
		metatnt.setDisplayName(ChatColor.RED + "Sterben!");
		metapaper.setDisplayName(ChatColor.GREEN + "Über dieses Plugin!");
		metakopf.setDisplayName(ChatColor.GREEN + "Dich zum Mitglied machen!");
		metakopf2.setDisplayName(ChatColor.RED + "Dich zum Owner machen!");
		metapaper.addEnchant(Enchantment.DURABILITY, 1, true);
		metapaper.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		dayclock.setItemMeta(metaday);
		nightclock.setItemMeta(metanight);
		goldenapple.setItemMeta(metaapple);
		goldencarrot.setItemMeta(metacarrot);
		tnt.setItemMeta(metatnt);
		paper.setItemMeta(metapaper);
		steve.setItemMeta(metakopf);
		steve2.setItemMeta(metakopf2);
		//Player-MENÜ ///////////////////////////////////////////////////////////////////////////////////
		int slot = 27;
		for (Player p : Bukkit.getOnlinePlayers()) {
			ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
			SkullMeta meta = (SkullMeta) skull.getItemMeta();
			meta.setOwner(Bukkit.getPlayer(p.getName()).getUniqueId().toString());
			meta.setDisplayName(p.getName() + "`s Spieler Menü");
			skull.setItemMeta(meta);
			p.sendMessage(p.getName() + " : " + slot);
			inv.setItem(slot, skull);
			slot++;
		}
		//////////////////////////////////////////////////////////////////////////////////////////////////
		inv.setItem(0, dayclock);
		inv.setItem(1, nightclock);
		inv.setItem(8, book);
		inv.setItem(9, goldenapple);
		inv.setItem(10, goldencarrot);
		inv.setItem(12, tnt);
		inv.setItem(53, paper);
		inv.setItem(45, steve);
		inv.setItem(46, steve2);
	}
}
