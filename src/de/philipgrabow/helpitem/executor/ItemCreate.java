package de.philipgrabow.helpitem.executor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.philipgrabow.helpitem.Item;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ItemCreate implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("helpitem")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(args.length == 0) {
					if(p.hasPermission("helpitem.create.self")) {
						TextComponent message = new TextComponent( "<JA>" );
						message.setColor(ChatColor.GREEN);
						TextComponent message1 = new TextComponent( "<NEIN>" );
						message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/helpitem w"));
						message1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/helpitem s"));
						message.addExtra(message1);
						p.sendMessage("§cWillst du dieses Item wirklich?");
						p.sendMessage("§8Klicke JA oder NEIN unter diesem Text!");
						p.spigot().sendMessage(message);
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("w")) {
					Item.createItem(p);
					p.sendMessage("Item erfolgreich erstellt!");
					return true;
				}
				if(args[0].equalsIgnoreCase("s")) {
					p.sendMessage("Vorgang abgebrochen!");
					return true;
				}
				return false;
			}
			return false;
		}
		return false;
	}
	
	

}
