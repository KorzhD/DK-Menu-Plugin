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

        if(!(player.hasPermission("adminMenu.admin"))) {
            commandSender.sendMessage("You must be an admin");
            return false;
        }

        Inventory adminMenu = Bukkit.createInventory(player, getRows(5),  "§l§5Admin Menu");

        ItemStack graveBreaker = new ItemStack(Material.GOLD_AXE);
        ItemMeta graveBreakerMeta = graveBreaker.getItemMeta();
        List<String> list = new ArrayList<>();
        list.add("§4Nightmare of Graveyards....");
            graveBreakerMeta.setDisplayName("§c§lGet Grave Breaker");
            graveBreakerMeta.setLore(list);
            graveBreakerMeta.setUnbreakable(true);
            graveBreaker.setItemMeta(graveBreakerMeta);

        ItemStack magicWand = new ItemStack(Material.WOOD_AXE, 1);
        ItemMeta magicWandMeta = magicWand.getItemMeta();
        List<String> list1 = new ArrayList<>();
        list.add("§6The most powerful thing in the World!");
        magicWandMeta.setDisplayName("§5§lGet Magic Wand");
        magicWandMeta.setLore(list1);
        magicWandMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        magicWandMeta.setUnbreakable(true);
        magicWand.setItemMeta(magicWandMeta);

        ItemStack pickUpOff = new ItemStack(Material.INK_SACK, 1, (short) 1);
        ItemMeta pickUpOffMeta = pickUpOff.getItemMeta();
        pickUpOffMeta.setDisplayName("§aEnable Pick Up Messages");
        pickUpOff.setItemMeta(pickUpOffMeta);
        ItemStack pickUpOn = new ItemStack(Material.INK_SACK, 1, (short) 10);
        ItemMeta pickUpOnMeta = pickUpOff.getItemMeta();
        pickUpOnMeta.setDisplayName("§aDisable Pick Up Messages");
        pickUpOn.setItemMeta(pickUpOnMeta);


        adminMenu.setItem(0, graveBreaker);
        adminMenu.setItem(1, magicWand);

        String pickUpSetting = AdminMenuEvents.pickUpSetting;
        if (pickUpSetting.equals("disable")) {
            adminMenu.setItem(36, pickUpOff);
        } else if (pickUpSetting.equals("enable")) {
            adminMenu.setItem(36, pickUpOn);
        }


        player.openInventory(adminMenu);
        return true;
    }

    private int getRows(int numberOfRows) {
        return numberOfRows * 9;
    }
}
