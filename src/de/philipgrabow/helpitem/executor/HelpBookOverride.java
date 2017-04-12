package de.philipgrabow.helpitem.executor;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
						p.sendMessage("Du bist im Menü wo du das HilfeBuch von 'HelpItem' verändern kannst!");
						p.sendMessage("Hier siehst du ALLE MÖGLICHKEITEN VON DIESEM COMMAND: ");
						p.sendMessage("1.Möglichkeit: /applybook confirm");
						p.sendMessage("Übernimmt das Buch was du in der Hand hälst!");
						p.sendMessage("2.Möglichkeit: /applybook inventory");
						p.sendMessage(
								"Übernimmt das Buch was in deinem Inventar liegt es muss aber den Namen 'HELPBOOK' haben!");
						p.sendMessage("3.Möglichkeit: /applybook new");
						p.sendMessage("Erstellt dir ein neues Buch mit passendem Namen für die 2.Möglichkeit!");
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
								String displayname = meta.getDisplayName();
								String title = meta.getTitle();
								String author = meta.getAuthor();
								int seitenzahl = meta.getPageCount();
								List<String> list = meta.getPages();
								cfg.set("HelpBook.Name", displayname);
								cfg.set("HelpBook.Title", title);
								cfg.set("HelpBook.Material", material);
								cfg.set("HelpBook.author", author);
								cfg.set("HelpBook.Sites", seitenzahl);
								cfg.set("HelpBook.pages", list);
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
