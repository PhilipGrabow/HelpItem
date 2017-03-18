package de.philipgrabow.helpitem.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import de.philipgrabow.helpitem.Inventory;
import de.philipgrabow.helpitem.Main;

public class InteractE implements Listener {
	
	private Main plugin;
	public InteractE(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void InteractEv(PlayerInteractEvent e) {
		try {
			if (e.getItem().getType() == Material.TRIPWIRE_HOOK) {
				if (e.getAction() == Action.RIGHT_CLICK_AIR) {
					// e.getPlayer().sendMessage(".");
					if (e.getItem() == null) {
						return;
					} else {
						ItemMeta meta = e.getItem().getItemMeta();
						if (meta.getDisplayName().contains("HILFE!")) {
							// e.getPlayer().sendMessage("..");
							e.setCancelled(true);
							if (meta.hasItemFlag(ItemFlag.HIDE_UNBREAKABLE)) {
								// e.getPlayer().sendMessage("...");
								Player p = e.getPlayer();
								if (p.hasPermission("helpitem.openinventory")) {
									Inventory.openInventory(p);
									Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
										@Override
										public void run() {
											Inventory.openInventory(p);
										}
									}, 5);
									return;
								} else {
									p.sendMessage("Du hast keine Berechtigung zum Öffnen des Menü´s");
									return;
								}
							}
						}
					}
				}

			} else {
				return;
			}
		} catch (NullPointerException ex) {
			return;
		}
		return;
	}

}
