package org.example.dmytrok.dkmenuplugin.adminMenu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AdminMenuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You must be a player");
            return false;
        }

        Player player = (Player) commandSender;

        if (!(player.hasPermission("adminMenu.admin"))) {
            commandSender.sendMessage("You must be an admin");
            return false;
        }

        Inventory adminMenu = Bukkit.createInventory(player, getRows(5), "§l§5Admin Menu");

        //Entity

        ItemStack monarch = new ItemStack(Material.MONSTER_EGG, 1, (short) 58);
        ItemMeta monarchMeta = monarch.getItemMeta();
        monarchMeta.setDisplayName("§5§lSummon Monarch");
        monarch.setItemMeta(monarchMeta);

        ItemStack fireElementKing = new ItemStack(Material.MONSTER_EGG, 1, (short) 61);
        ItemMeta fekMeta = fireElementKing.getItemMeta();
        fekMeta.setDisplayName("§c§lSummon Fire Element King");
        fireElementKing.setItemMeta(fekMeta);

        ItemStack guardianOfColdLands = new ItemStack(Material.MONSTER_EGG, 1, (short) 102);
        ItemMeta goclMeta = guardianOfColdLands.getItemMeta();
        goclMeta.setDisplayName("§b§lSummon Guardian of Cold Lands");
        guardianOfColdLands.setItemMeta(goclMeta);

        ItemStack stick = new ItemStack(Material.STICK);
        ItemMeta stickM = stick.getItemMeta();
        stickM.setDisplayName("§lGet Boss Killer");
        stickM.addEnchant(Enchantment.DAMAGE_ALL, 100, true);
        stick.setItemMeta(stickM);

        //Entity

        //


        adminMenu.setItem(8, monarch);
        adminMenu.setItem(17, fireElementKing);
        adminMenu.setItem(26, guardianOfColdLands);
        adminMenu.setItem(7, stick);

        player.openInventory(adminMenu);
        return true;
    }

    private int getRows(int numberOfRows) {
        return numberOfRows * 9;
    }
}
