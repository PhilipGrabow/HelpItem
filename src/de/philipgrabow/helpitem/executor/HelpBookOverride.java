package de.philipgrabow.helpitem.executor;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.BookMeta;

public class HelpBookOverride implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("applybook")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (args.length == 0) {
					if (p.hasPermission("helpitem.helpbook.help")) {
						p.sendMessage(ChatColor.RED + "Du bist im Men� wo du das HilfeBuch von 'HelpItem' ver�ndern kannst!");
						p.sendMessage(ChatColor.GOLD + "Hier siehst du ALLE M�GLICHKEITEN VON DIESEM COMMAND: ");
						p.sendMessage(ChatColor.LIGHT_PURPLE + "1.M�glichkeit: /applybook confirm");
						p.sendMessage(ChatColor.GOLD + "�bernimmt das Buch was du in der Hand h�lst!");
						p.sendMessage(ChatColor.LIGHT_PURPLE + "2.M�glichkeit: /applybook inventory");
						p.sendMessage(ChatColor.GOLD + 
								"�bernimmt das Buch was in deinem Inventar liegt es muss aber den Namen 'HELPBOOK' haben!");
						p.sendMessage(ChatColor.LIGHT_PURPLE + "3.M�glichkeit: /applybook new");
						p.sendMessage(ChatColor.GOLD + "Erstellt dir ein neues Buch mit passendem Namen f�r die 2.M�glichkeit!");
						return true;
					} else {
						sender.sendMessage("Keine Berechtigung!");
						return true;
					}
				}
				if (args.length == 1 && args[0].equalsIgnoreCase("confirm")) {
					if (p.hasPermission("helpitem.helpbook.confirm")) {
						if (p.getInventory().contains(Material.WRITTEN_BOOK)) {
							if (p.getInventory().getItemInMainHand().getType() == Material.WRITTEN_BOOK) {
								BookMeta meta = (BookMeta) p.getInventory().getItemInMainHand().getItemMeta();
								File file = new File("plugins/HelpItem/Book", "HelpBook.yml");
								if (!(file.exists())) {
									try {
										file.createNewFile();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
								String material = Material.WRITTEN_BOOK.toString();
								String title = meta.getTitle();
								String author = meta.getAuthor();
								int seitenzahl = meta.getPageCount();
								List<String> list = meta.getPages();
								cfg.set("HelpBook.Title", title);
								cfg.set("HelpBook.Material", material);
								cfg.set("HelpBook.Author", author);
								cfg.set("HelpBook.Sites", seitenzahl);
								cfg.set("HelpBook.Pages", list);
								try {
									cfg.save(file);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								p.sendMessage("Erfolgreich gespeichert!");
								return true;
							} else {
								p.sendMessage("Du hast nicht den passenden Gegenstand in deiner Hand!");
								return true;
							}
						} else {
							p.sendMessage("Dein Inventar beinhaltet kein solchen Gegenstand!");
							return true;
						}
					} else {
						p.sendMessage("Du hast keine Berechtigung!");
						return true;
					}
				}
			} else {
				sender.sendMessage("Du bist kein Spieler!");
				return true;
			}
		}
		return false;
	}

}
