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
		ItemStack wheat = new ItemStack(Material.WHEAT);
		ItemMeta metawheat = wheat.getItemMeta();
		metawheat.setDisplayName("Hunger stillen!");
		wheat.setItemMeta(metawheat);
		
		inv.setItem(0, wheat);
	}

}
