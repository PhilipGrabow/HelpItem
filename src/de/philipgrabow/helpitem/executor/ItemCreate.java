package de.philipgrabow.helpitem.executor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.philipgrabow.helpitem.Item;

public class ItemCreate implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("helpitem")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(args.length == 0) {
					if(p.hasPermission("helpitem.create.self")) {
						Item.createItem(p);
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
