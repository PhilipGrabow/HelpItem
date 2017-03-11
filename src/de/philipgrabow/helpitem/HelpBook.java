package de.philipgrabow.helpitem;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class HelpBook {

	public static ItemStack loadBook(ItemStack book) {
		book = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta meta = (BookMeta) book.getItemMeta();
		meta.setDisplayName("Hilfemenü-Erklärung!");
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
		return book;
	}

}
