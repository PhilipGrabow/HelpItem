package de.philipgrabow.helpitem.listener;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import de.philipgrabow.helpitem.HelpBook;

public class InventoryClickE implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void onInvClick(InventoryClickEvent e) {
		String invname = e.getInventory().getName();
		if (invname.equalsIgnoreCase("Hilfemen�")) {
			// Inventar Slot 0 (Zeit zu Tag)

			if (e.getCurrentItem().getType() == Material.WATCH) {
				ItemMeta meta = e.getCurrentItem().getItemMeta();
				if (e.getClick().isLeftClick()) {
					if (meta.getDisplayName().contains("Zeit: Tag!")) {
						Player p = (Player) e.getWhoClicked();
						p.getWorld().setTime(1000);
						p.sendMessage("�6Du hast es zu Tag gemacht!");
						p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 20, 1);
						p.closeInventory();
					}
					if (meta.getDisplayName().contains("Zeit: Nacht!")) {
						Player p = (Player) e.getWhoClicked();
						p.getWorld().setTime(13000);
						p.sendMessage("�6Du hast es zu Nacht gemacht!");
						p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 20, 1);
						p.closeInventory();
					}
				} else {
					e.getWhoClicked().sendMessage("Nur Linksklick erlaubt!");
				}
			} else if (e.getCurrentItem().getType() == Material.WRITTEN_BOOK) {
				BookMeta meta = (BookMeta) e.getCurrentItem().getItemMeta();
				if (e.getClick().isLeftClick()) {
					if (meta.getDisplayName().contains("Hilfemen�-Erkl�rung!")) {
						Player p = (Player) e.getWhoClicked();
						ItemStack book = null;
						p.getInventory().addItem(HelpBook.loadBook(book));
					}
				} else {
					e.getWhoClicked().sendMessage("Nur Linksklick erlaubt!");
				}
			} else if (e.getCurrentItem().getType() == Material.GOLDEN_APPLE) {
				ItemMeta meta = e.getCurrentItem().getItemMeta();
				if (e.getClick().isLeftClick()) {
					if (meta.getDisplayName().contains("Heilen!")) {
						Player p = (Player) e.getWhoClicked();
						if (p.hasPermission("helpitem.heal")) {
							p.setHealth(20.0);
							p.sendMessage("�6Du hast dich geheilt!");
							p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 20, 1);
							p.closeInventory();
						} else {
							p.sendMessage("Du hast keine Berechtigung zu dieser Funktion!");
							return;
						}
					}
				} else {
					e.getWhoClicked().sendMessage("Nur Linksklick erlaubt!");
				}
			} else if (e.getCurrentItem().getType() == Material.GOLDEN_CARROT) {
				ItemMeta meta = e.getCurrentItem().getItemMeta();
				if (e.getClick().isLeftClick()) {
					if (meta.getDisplayName().contains("Hunger stillen!")) {
						Player p = (Player) e.getWhoClicked();
						if (p.hasPermission("helpitem.hunger")) {
							p.setFoodLevel(20);
							p.sendMessage("�6Du bist jetzt satt!");
							p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 20, 1);
							p.closeInventory();
						} else {
							p.sendMessage("Du hast keine Berechtigung zu dieser Funktion!");
							return;
						}
					}
				} else {
					e.getWhoClicked().sendMessage("Nur Linksklick erlaubt!");
				}
			} else if (e.getCurrentItem().getType() == Material.TNT) {
				ItemMeta meta = e.getCurrentItem().getItemMeta();
				if (e.getClick().isLeftClick()) {
					if (meta.getDisplayName().contains("Sterben!")) {
						Player p = (Player) e.getWhoClicked();
						if (p.hasPermission("helpitem.kill")) {
							p.setHealth(0.0);
							p.sendMessage("�6Du bist gestorben!");
							p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 20, 1);
							p.closeInventory();
						} else {
							p.sendMessage("Du hast keine Berechtigung zu dieser Funktion!");
							return;
						}
					}
				} else {
					e.getWhoClicked().sendMessage("Nur Linksklick erlaubt!");
				}
			} else if (e.getCurrentItem().getType() == Material.PAPER) {
				ItemMeta meta = e.getCurrentItem().getItemMeta();
				if (e.getClick().isLeftClick()) {
					if (meta.getDisplayName().contains("�ber dieses Plugin!")) {
					}
				} else {
					e.getWhoClicked().sendMessage("Nur Linksklick erlaubt!");
				}
			} else if (e.getCurrentItem().getType() == Material.SKULL_ITEM) {
				ItemMeta meta = e.getCurrentItem().getItemMeta();
				if (e.getClick().isLeftClick()) {
					if (meta.getDisplayName().contains("Dich zum Mitglied machen!")) {
						Player p = (Player) e.getWhoClicked();
						if (p.hasPermission("helpitem.mitglied")) {
							p.getServer().dispatchCommand(p.getServer().getConsoleSender(), "pex user " + p.getName() + " group set Mitglieder");
							p.sendMessage("�6Du bist jetzt Mitglied!");
							p.setOp(false);
							p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 20, 1);
							p.closeInventory();
						} else {
							p.sendMessage("Du hast keine Berechtigung zu dieser Funktion!");
							return;
						}
					}
					if (meta.getDisplayName().contains("Dich zum Owner machen!")) {
						Player p = (Player) e.getWhoClicked();
						if (p.hasPermission("helpitem.owner")) {
							p.getServer().dispatchCommand(p.getServer().getConsoleSender(), "pex user " + p.getName() + " group set Owner");
							p.sendMessage("�6Du bist jetzt Owner!");
							p.setOp(true);
							p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 20, 1);
							p.closeInventory();
						} else {
							p.sendMessage("Du hast keine Berechtigung zu dieser Funktion!");
							return;
						}
					}
				} else {
					e.getWhoClicked().sendMessage("Nur Linksklick erlaubt!");
				}
			} else {
				e.getWhoClicked().sendMessage("Nicht erkanntes Item gew�hlt!");
			}
			e.setCancelled(true);
			return;
		}
		return;
	}

}