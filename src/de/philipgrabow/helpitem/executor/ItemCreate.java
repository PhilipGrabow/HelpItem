package de.philipgrabow.helpitem.executor;

import java.util.ArrayList;

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

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("helpitem")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				ArrayList<String> list = new ArrayList<String>();
				if (args.length == 0) {
					if (p.hasPermission("helpitem.create.self")) {
						if (list.contains(p.getName())) {
							p.sendMessage("Beende erst den Vorherigen Vorgang!");
							return true;
						} else {
							TextComponent message = new TextComponent("<JA>");
							message.setColor(ChatColor.GREEN);
							TextComponent message1 = new TextComponent("<NEIN>");
							message1.setColor(ChatColor.RED);
							message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/helpitem w"));
							message1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/helpitem s"));
							message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM,
									new ComponentBuilder("Klicke hier für JA!").create()));
							message1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM,
									new ComponentBuilder("Klicke hier für NEIN!").create()));
							message.addExtra(message1);
							p.sendMessage(ChatColor.LIGHT_PURPLE + "###############################################");
							p.sendMessage("§cWillst du dieses Item wirklich?");
							p.sendMessage(ChatColor.YELLOW + "Klicke JA oder NEIN unter diesem Text!");
							list.add(p.getName());
							p.spigot().sendMessage(message);
							p.sendMessage(ChatColor.LIGHT_PURPLE + "###############################################");
							return true;
						}
					}
				}
				if (args[0].equalsIgnoreCase("w")) {
					if(list.contains(p.getName())) {
						Item.createItem(p);
						list.remove(p.getName());
						p.sendMessage("Item erfolgreich erstellt!");
						return true;
					} else {
						p.sendMessage("Kein Vorgang vorhanden!");
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("s")) {
					if(list.contains(p.getName())) {
						p.sendMessage("Vorgang abgebrochen!");
						list.remove(p.getName());
						return true;
					} else {
						p.sendMessage("Kein Vorgang vorhanden!");
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
