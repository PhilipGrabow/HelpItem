package de.philipgrabow.helpitem.executor;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.philipgrabow.helpitem.Item;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ItemCreate implements CommandExecutor {
	
	public HashMap<String, Boolean> map = new HashMap<String, Boolean>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("helpitem")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (args.length == 0) {
					if (p.hasPermission("helpitem.create.self")) {
						if (map.containsKey(p.getName())) {
							p.sendMessage("Beende erst den Vorherigen Vorgang!");
							return true;
						} else {
							TextComponent message = new TextComponent("<JA>");
							message.setColor(ChatColor.GREEN);
							TextComponent message1 = new TextComponent("<NEIN>");
							message1.setColor(ChatColor.RED);
							message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/helpitem w"));
							message1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/helpitem s"));
							message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
									new ComponentBuilder(ChatColor.GOLD + "Klicke hier für JA!").create()));
							message1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
									new ComponentBuilder(ChatColor.GOLD + "Klicke hier für NEIN!").create()));
							message.addExtra(message1);
							p.sendMessage(ChatColor.LIGHT_PURPLE + "###############################################");
							p.sendMessage("§cWillst du dieses Item wirklich?");
							p.sendMessage(ChatColor.YELLOW + "Klicke JA oder NEIN unter diesem Text!");
							map.put(p.getName(), true);
							p.spigot().sendMessage(message);
							p.sendMessage(ChatColor.LIGHT_PURPLE + "###############################################");
							return true;
						}
					}
				}
				if (args[0].equalsIgnoreCase("w")) {
					if(map.containsKey(p.getName())) {
						if(p.getInventory().contains(Item.hook())) {
							p.sendMessage(ChatColor.RED + "Du besitzt schon ein Item!");
							map.remove(p.getName());
							return true;
						} else {
						Item.createItem(p);
						map.remove(p.getName());
						p.sendMessage(ChatColor.GREEN + "Item erfolgreich erstellt!");
						return true;
						}
					} else {
						p.sendMessage(ChatColor.RED + "Kein Vorgang vorhanden!");
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("s")) {
					if(map.containsKey(p.getName())) {
						p.sendMessage(ChatColor.RED + "Vorgang abgebrochen!");
						map.remove(p.getName());
						return true;
					} else {
						p.sendMessage(ChatColor.RED + "Kein Vorgang vorhanden!");
						return true;
					}
				}
				return false;
			}
			return false;
		}
		return false;
	}

}
