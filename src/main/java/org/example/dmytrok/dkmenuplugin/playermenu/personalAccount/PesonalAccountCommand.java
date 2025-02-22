package org.example.dmytrok.dkmenuplugin.playermenu.personalAccount;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.example.dmytrok.dkmenuplugin.adminMenu.AdminMenuEvents;

public class PesonalAccountCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You must be a player");
            return false;
        }

        Player player = (Player) commandSender;

        Inventory personalAccount = Bukkit.createInventory(player, getRows(5),"§9Personal Account");

        //

        // PickUp
        ItemStack pickUpOff = new ItemStack(Material.INK_SACK, 1, (short) 1);
        ItemMeta pickUpOffMeta = pickUpOff.getItemMeta();
        pickUpOffMeta.setDisplayName("§aEnable Pick Up Messages");
        pickUpOff.setItemMeta(pickUpOffMeta);

        ItemStack pickUpOn = new ItemStack(Material.INK_SACK, 1, (short) 10);
        ItemMeta pickUpOnMeta = pickUpOn.getItemMeta();
        pickUpOnMeta.setDisplayName("§cDisable Pick Up Messages");
        pickUpOn.setItemMeta(pickUpOnMeta);

        // PickUp

        //

        String setting = null;

        if (!(PersonalAccountEvents.getPickUpSetting(player) == null)) {
            setting = PersonalAccountEvents.getPickUpSetting(player);
            if (setting.equals("disable")) {
                personalAccount.setItem(36, pickUpOff);
            } else if (setting.equals("enable")) {
                personalAccount.setItem(36, pickUpOn);
            }
        } else {
            setting = "disable";
        }
        if (setting.equals("disable")) {
            personalAccount.setItem(36, pickUpOff);
        } else if (setting.equals("enable")) {
            personalAccount.setItem(36, pickUpOn);
        }

        player.openInventory(personalAccount);
        return true;
    }

    private int getRows(int numberOfRows) {
        return numberOfRows * 9;
    }
}
