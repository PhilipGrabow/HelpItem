package de.philipgrabow.helpitem;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {
	// Stolperdraht Haken

	// Spieler kriegt Das modifizierte Item!
	public static void createItem(Player p) {
		ItemStack is = new ItemStack(Material.TRIPWIRE_HOOK, 1);
		ItemMeta meta = is.getItemMeta();
		ArrayList<String> description = new ArrayList<String>();
		description.add("Öffnet die");
		description.add("Hilfe im");
		description.add("im Spiel!");
		meta.setDisplayName("HILFE!");
		meta.setLore(description);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		is.setItemMeta(meta);
		p.getInventory().addItem(is);
	}
}
