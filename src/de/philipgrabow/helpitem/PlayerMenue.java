package de.philipgrabow.helpitem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerMenue {
	
	public static void openInventory(Player p, String name) {
		org.bukkit.inventory.Inventory inv = Bukkit.createInventory(p, 54, name);
		loadItems(inv);
		p.openInventory(inv);
	}
	public static void loadItems(org.bukkit.inventory.Inventory inv) {
		ItemStack wheat = weizen();
		ItemStack dia = diamond();
		
		inv.setItem(0, dia);
		inv.setItem(1, wheat);
	}
	public static ItemStack weizen() {
		ItemStack wheat = new ItemStack(Material.WHEAT);
		ItemMeta metawheat = wheat.getItemMeta();
		metawheat.setDisplayName("Hunger stillen!");
		wheat.setItemMeta(metawheat);
		return wheat;
	}
	public static ItemStack diamond() {
		ItemStack dia = new ItemStack(Material.DIAMOND);
		ItemMeta meta = dia.getItemMeta();
		meta.setDisplayName("Heilt den Spieler!");
		dia.setItemMeta(meta);
		return dia;
	}
}
