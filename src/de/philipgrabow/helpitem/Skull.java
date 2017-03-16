package de.philipgrabow.helpitem;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;


public class Skull {

	public static ItemStack listHead(Player p) {
		String name = p.getName();
		ItemStack skull = new ItemStack(Material.SKULL, 1, (short)SkullType.PLAYER.ordinal());
		SkullMeta  meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
		meta.setOwner(name);
		meta.setDisplayName(name);
		skull.setItemMeta(meta);
		return skull;
	}
	public static ArrayList<String> erstelleArray() {
		ArrayList<String> list = new ArrayList<String>();
		for(Player p1 : Bukkit.getOnlinePlayers()) {
			list.add(p1.getName());
		}
		return list;
	}
	public static void CreateHeads(ArrayList<String> list) {
		for(String playername : list) {
			Player p = Bukkit.getPlayer(playername);
			listHead(p);
		}
		return;
	}
}
