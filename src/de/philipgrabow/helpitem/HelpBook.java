package de.philipgrabow.helpitem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class HelpBook {

	public static ItemStack loadBook(ItemStack book) {
		book = new ItemStack(Material.WRITTEN_BOOK, 1);
		File file = new File("plugins/HelpItem/Book", "HelpBook.yml");
		if (file.exists()) {
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
			BookMeta meta = (BookMeta) book.getItemMeta();
			meta.setTitle(cfg.getString("HelpBook.Title"));
			meta.setAuthor(cfg.getString("HelpBook.Author"));
			ArrayList<String> lore = new ArrayList<String>();
			lore.add("Zum Verständnis!");
			meta.setLore(lore);
			meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
			List<String> pages = cfg.getStringList("HelpBook.Pages");
			meta.setPages(pages);
			book.setItemMeta(meta);
		} else {
			BookMeta meta = (BookMeta) book.getItemMeta();
			meta.setDisplayName("HelpBook");
			meta.setAuthor("Philip_Grabow");
			ArrayList<String> lore = new ArrayList<String>();
			lore.add("Zum Verständnis!");
			meta.setLore(lore);
			meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
			List<String> pages = new ArrayList<String>();
			pages.add("Hilfemenü! \n" + "1.Funktion: \n" + "Zeit ändern: \n" + "Funktion für alle! \n"
					+ "Varianten: Tag/Nacht \n" + "Linksklick auf die gewünschte Uhr im Hilfemenü!");
			meta.setPages(pages);
			book.setItemMeta(meta);
		}
		return book;
	}

}
