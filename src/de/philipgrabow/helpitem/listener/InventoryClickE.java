package de.philipgrabow.helpitem.listener;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import de.philipgrabow.helpitem.HelpBook;
import de.philipgrabow.helpitem.PlayerMenue;

public class InventoryClickE implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void onInvClick(InventoryClickEvent e) {
		String invname = e.getInventory().getName();
		if (invname.equalsIgnoreCase("Hilfemenü")) {
			// Inventar Slot 0 (Zeit zu Tag)

			if (e.getCurrentItem().getType() == Material.WATCH) {
				ItemMeta meta = e.getCurrentItem().getItemMeta();
				if (e.getClick().isLeftClick()) {
					if (meta.getDisplayName().contains("Zeit: Tag!")) {
						Player p = (Player) e.getWhoClicked();
						p.getWorld().setTime(1000);
						p.sendMessage("§6Du hast es zu Tag gemacht!");
						p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 20, 1);
						p.closeInventory();
					}
					if (meta.getDisplayName().contains("Zeit: Nacht!")) {
						Player p = (Player) e.getWhoClicked();
						p.getWorld().setTime(13000);
						p.sendMessage("§6Du hast es zu Nacht gemacht!");
						p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 20, 1);
						p.closeInventory();
					}
				} else {
					e.getWhoClicked().sendMessage("§cNur Linksklick erlaubt!");
				}
			} else if (e.getCurrentItem().getType() == Material.WRITTEN_BOOK) {
				BookMeta meta = (BookMeta) e.getCurrentItem().getItemMeta();
				if (e.getClick().isLeftClick()) {
					if (meta.getDisplayName().contains("Hilfemenü-Erklärung!")) {
						Player p = (Player) e.getWhoClicked();
						ItemStack book = null;
						p.getInventory().addItem(HelpBook.loadBook(book));
					}
				} else {
					e.getWhoClicked().sendMessage("§cNur Linksklick erlaubt!");
				}
			} else if (e.getCurrentItem().getType() == Material.GOLDEN_APPLE) {
				ItemMeta meta = e.getCurrentItem().getItemMeta();
				if (e.getClick().isLeftClick()) {
					if (meta.getDisplayName().contains("Heilen!")) {
						Player p = (Player) e.getWhoClicked();
						if (p.hasPermission("helpitem.heal")) {
							p.setHealth(20.0);
							p.sendMessage("§6Du hast dich geheilt!");
							p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 20, 1);
							p.closeInventory();
						} else {
							p.sendMessage("§cDu hast keine Berechtigung zu dieser Funktion!");
						}
					}
				} else {
					e.getWhoClicked().sendMessage("§cNur Linksklick erlaubt!");
				}
			} else if (e.getCurrentItem().getType() == Material.GOLDEN_CARROT) {
				ItemMeta meta = e.getCurrentItem().getItemMeta();
				if (e.getClick().isLeftClick()) {
					if (meta.getDisplayName().contains("Hunger stillen!")) {
						Player p = (Player) e.getWhoClicked();
						if (p.hasPermission("helpitem.hunger")) {
							p.setFoodLevel(20);
							p.sendMessage("§6Du bist jetzt satt!");
							p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 20, 1);
							p.closeInventory();
						} else {
							p.sendMessage("§cDu hast keine Berechtigung zu dieser Funktion!");
						}
					}
				} else {
					e.getWhoClicked().sendMessage("§cNur Linksklick erlaubt!");
				}
			} else if (e.getCurrentItem().getType() == Material.TNT) {
				ItemMeta meta = e.getCurrentItem().getItemMeta();
				if (e.getClick().isLeftClick()) {
					if (meta.getDisplayName().contains("Sterben!")) {
						Player p = (Player) e.getWhoClicked();
						if (p.hasPermission("helpitem.kill")) {
							p.setHealth(0.0);
							p.sendMessage("§6Du bist gestorben!");
							p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 20, 1);
							p.closeInventory();
						} else {
							p.sendMessage("§cDu hast keine Berechtigung zu dieser Funktion!");
						}
					}
				} else {
					e.getWhoClicked().sendMessage("§cNur Linksklick erlaubt!");
				}
			} else if (e.getCurrentItem().getType() == Material.PAPER) {
				ItemMeta meta = e.getCurrentItem().getItemMeta();
				if (e.getClick().isLeftClick()) {
					if (meta.getDisplayName().contains("Über dieses Plugin!")) {
					}
				} else {
					e.getWhoClicked().sendMessage("§cNur Linksklick erlaubt!");
				}
			} else if (e.getCurrentItem().getType() == Material.SKULL_ITEM) {
				if (e.getClick().isLeftClick()) {
					ArrayList<String> list = new ArrayList<String>();
					for (Player p : Bukkit.getOnlinePlayers()) {
						list.add(p.getName());
					}
					ItemMeta meta = e.getCurrentItem().getItemMeta();
					if (list.contains(meta.getDisplayName())) {
						PlayerMenue.openInventory((Player) e.getWhoClicked(), meta.getDisplayName());
					}
					if (meta.getDisplayName().contains("Dich zum Mitglied machen!")) {
						Player p = (Player) e.getWhoClicked();
						if (p.hasPermission("helpitem.mitglied")) {
							p.getServer().dispatchCommand(p.getServer().getConsoleSender(),
									"pex user " + p.getName() + " group set Mitglieder");
							p.sendMessage("§6Du bist jetzt Mitglied!");
							p.setOp(false);
							p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 20, 1);
							p.closeInventory();
						} else {
							p.sendMessage("§cDu hast keine Berechtigung zu dieser Funktion!");
						}
					}
					if (meta.getDisplayName().contains("Dich zum Owner machen!")) {
						Player p = (Player) e.getWhoClicked();
						if (p.hasPermission("helpitem.owner")) {
							p.getServer().dispatchCommand(p.getServer().getConsoleSender(),
									"pex user " + p.getName() + " group set Owner");
							p.sendMessage("§6Du bist jetzt Owner!");
							p.setOp(true);
							p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 20, 1);
							p.closeInventory();
						} else {
							p.sendMessage("§cDu hast keine Berechtigung zu dieser Funktion!");
						}
					}
				} else {
					e.getWhoClicked().sendMessage("§cNur Linksklick erlaubt!");
				}
			} else {
				e.getWhoClicked().sendMessage(ChatColor.RED + "Dieses Item hat noch keine Funktion!");
			}
			e.setCancelled(true);
		}
		ArrayList<String> list = new ArrayList<String>();
		for (Player p : Bukkit.getOnlinePlayers()) {
			list.add(p.getName());
		}
		///////////////////////////////////////// Player-Menü//////////////////////////////////////////////////////////////////////////////////
		if (list.contains(invname)) {
			if (e.getCurrentItem().getType() == Material.WHEAT) {
				if (e.getClick().isLeftClick()) {
					ItemMeta meta = e.getCurrentItem().getItemMeta();
					if (meta.getDisplayName().equalsIgnoreCase("Hunger stillen!")) {
						Player p = (Player) e.getWhoClicked();
						if (p.hasPermission("helpitem.playermenue.hunger")) {
							File file = new File("plugins/HelpItem", "UUID.yml");
							FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
							if (cfg.contains(invname)) {
								String uid = cfg.getString(invname + ".UUID");
								Player p2 = Bukkit.getPlayer(UUID.fromString(uid));
								p2.setFoodLevel(20);
								p2.sendMessage("§6Dein Hunger wurde gestillt!");
								p.closeInventory();
								p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 20, 1);
							} else {
								p.sendMessage("§cFehler in der Verarbeitung!");
							}
						} else {
							p.sendMessage("§cDu hast keine Berechtigung zu dieser Funktion!");
						}
					}
				} else {
					e.getWhoClicked().sendMessage("§cNur Linksklick erlaubt!");
				}
			} else if (e.getCurrentItem().getType() == Material.DIAMOND) {
				if (e.getClick().isLeftClick()) {
					ItemMeta meta = e.getCurrentItem().getItemMeta();
					if (meta.getDisplayName().equalsIgnoreCase("Heilt den Spieler!")) {
						Player p = (Player) e.getWhoClicked();
						if (p.hasPermission("helpitem.playermenue.heal")) {
							File file = new File("plugins/HelpItem", "UUID.yml");
							FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
							if (cfg.contains(invname)) {
								String uid = cfg.getString(invname + ".UUID");
								Player p2 = Bukkit.getPlayer(UUID.fromString(uid));
								p2.setHealth(20.0);
								p2.sendMessage("§6Du wurdest geheilt!");
								p.closeInventory();
								p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 20, 1);
							} else {
								p.sendMessage("§cFehler in der Verarbeitung!");
							}
						} else {
							p.sendMessage("§cDu hast keine Berechtigung zu dieser Funktion!");
						}
					}
				} else {
					e.getWhoClicked().sendMessage("§cNur Linksklick erlaubt!");
				}
			} else if (e.getCurrentItem().getType() == Material.TNT) {
				if (e.getClick().isLeftClick()) {
					ItemMeta meta = e.getCurrentItem().getItemMeta();
					if (meta.getDisplayName().contains("Leert das Inventar von diesem Spieler!")) {
						Player p = (Player) e.getWhoClicked();
						if (p.hasPermission("helpitem.playermenue.invclear")) {
							File file = new File("plugins/HelpItem", "UUID.yml");
							FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
							if (cfg.contains(invname)) {
								String uid = cfg.getString(invname + ".UUID");
								Player p2 = Bukkit.getPlayer(UUID.fromString(uid));
								p2.getInventory().clear();
								p2.sendMessage("§6Dein Inventar wurde geleert!");
								p.closeInventory();
								p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 20, 1);
							} else {
								p.sendMessage("§cFehler in der Verarbeitung!");
							}
						} else {
							p.sendMessage("§cDu hast keine Berechtigung zu dieser Funktion!");
						}
					}
			} else if (e.getCurrentItem().getType() == Material.PAPER) {
					if (e.getClick().isLeftClick()) {
						ItemMeta meta = e.getCurrentItem().getItemMeta();
						if (meta.getDisplayName().equalsIgnoreCase("Gamemode Survival(0)")) {
							Player p = (Player) e.getWhoClicked();
							if (p.hasPermission("helpitem.playermenue.gm0")) {
								File file = new File("plugins/HelpItem", "UUID.yml");
								FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
								if (cfg.contains(invname)) {
									String uid = cfg.getString(invname + ".UUID");
									Player p2 = Bukkit.getPlayer(UUID.fromString(uid));
									p2.setGameMode(GameMode.SURVIVAL);
									p2.sendMessage("§6Dein GameMode wurde auf Survival geändert!");
									p.closeInventory();
									p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 20, 1);
								} else {
									p.sendMessage("§cFehler in der Verarbeitung!");
								}
							} else {
								p.sendMessage("§cDu hast keine Berechtigung zu dieser Funktion!");
							}
						} else if (meta.getDisplayName().equalsIgnoreCase("Gamemode Creative(1)")) {
							Player p = (Player) e.getWhoClicked();
							if (p.hasPermission("helpitem.playermenue.gm1")) {
								File file = new File("plugins/HelpItem", "UUID.yml");
								FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
								if (cfg.contains(invname)) {
									String uid = cfg.getString(invname + ".UUID");
									Player p2 = Bukkit.getPlayer(UUID.fromString(uid));
									p2.setGameMode(GameMode.CREATIVE);
									p2.sendMessage("§6Dein GameMode wurde auf Creative geändert!");
									p.closeInventory();
									p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 20, 1);
								} else {
									p.sendMessage("§cFehler in der Verarbeitung!");
								}
							} else {
								p.sendMessage("§cDu hast keine Berechtigung zu dieser Funktion!");
							}
						} else if (meta.getDisplayName().equalsIgnoreCase("Gamemode Adventure(2)")) {
							Player p = (Player) e.getWhoClicked();
							if (p.hasPermission("helpitem.playermenue.gm2")) {
								File file = new File("plugins/HelpItem", "UUID.yml");
								FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
								if (cfg.contains(invname)) {
									String uid = cfg.getString(invname + ".UUID");
									Player p2 = Bukkit.getPlayer(UUID.fromString(uid));
									p2.setGameMode(GameMode.ADVENTURE);
									p2.sendMessage("§6Dein GameMode wurde auf Adventure geändert!");
									p.closeInventory();
									p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 20, 1);
								} else {
									p.sendMessage("§cFehler in der Verarbeitung!");
								}
							} else {
								p.sendMessage("§cDu hast keine Berechtigung zu dieser Funktion!");
							}
						} else if (meta.getDisplayName().equalsIgnoreCase("Gamemode Spectator(3)")) {
							Player p = (Player) e.getWhoClicked();
							if (p.hasPermission("helpitem.playermenue.gm3")) {
								File file = new File("plugins/HelpItem", "UUID.yml");
								FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
								if (cfg.contains(invname)) {
									String uid = cfg.getString(invname + ".UUID");
									Player p2 = Bukkit.getPlayer(UUID.fromString(uid));
									p2.setGameMode(GameMode.SPECTATOR);
									p2.sendMessage("§6Dein GameMode wurde auf Spectator geändert!");
									p.closeInventory();
									p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 20, 1);
								} else {
									p.sendMessage("§cFehler in der Verarbeitung!");
								}
							} else {
								p.sendMessage("§cDu hast keine Berechtigung zu dieser Funktion!");
							}
						}
					}
				} else {
					e.getWhoClicked().sendMessage("§cNur Linksklick erlaubt!");
				}
			} else if(e.getCurrentItem().getType() == Material.COMPASS) {
				if(e.getClick().isLeftClick()) {
					ItemMeta meta = e.getCurrentItem().getItemMeta();
					if(meta.getDisplayName().equalsIgnoreCase("KOORDINATEN:")) {
						Player p = (Player) e.getWhoClicked();
						if(p.hasPermission("helpitem.playermenue.tele")) {
							File file = new File("plugins/HelpItem", "UUID.yml");
							FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
							if(cfg.contains(invname)) {
								String uid = cfg.getString(invname + ".UUID");
								Player p2 = Bukkit.getPlayer(UUID.fromString(uid));
								Location loc = p2.getLocation();
								p.teleport(loc);
								p.sendMessage("§6Du wurdest zu Spieler '" + p2.getName() + "' teleportiert!");
								p2.sendMessage("§6Spieler '" + p.getName() + "' wurde zu dir teleportiert!");
								p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 20, 1);
								p.closeInventory();
							} else {
								p.sendMessage("§cFehler in der Verarbeitung!");
							}
						} else {
							p.sendMessage("§cDu hast keine Berechtigung zu dieser Funktion!");
						}
					}
				} else {
					e.getWhoClicked().sendMessage("§cNur Linksklick erlaubt!");
				}
			} else {
				e.getWhoClicked().sendMessage(ChatColor.RED + "Dieses Item hat noch keine Funktion!");
			}
			e.setCancelled(true);
		}
		return;
	}
}
