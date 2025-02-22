package org.example.dmytrok.dkmenuplugin.playermenu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerMenuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You must be a player");
            return false;
        }

        Player player = (Player) commandSender;

        if (!(player.hasPermission("menu.user"))) {
            commandSender.sendMessage("You must be an user");
            return false;
        }

        Inventory menu = Bukkit.createInventory(player, getRows(5), "§l§bMenu");

        // Player's Cabinet

        ItemStack playerCabinet = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) playerCabinet.getItemMeta();
        skullMeta.setOwner(player.getCustomName());
        skullMeta.setDisplayName("§6Personal Account");
        playerCabinet.setItemMeta(skullMeta);

        // Player's Cabinet

        //


        menu.setItem(8, playerCabinet);
        player.openInventory(menu);
        return true;
    }

    private int getRows(int numberOfRows) {
        return numberOfRows * 9;
    }

}
