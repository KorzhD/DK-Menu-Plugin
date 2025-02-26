package org.example.dmytrok.dkmenuplugin.tradesystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class TradeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You must be a player");
            return false;
        }

        Player player = (Player) commandSender;

        Inventory playerListMenu = Bukkit.createInventory(null, 45, "§2Select a player to Trade");

        List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
        onlinePlayers.sort(Comparator.comparing(Player::getName));

        for (int i = 0; i < onlinePlayers.size() && i < 27; i++) {
            if(onlinePlayers.get(i).equals(player)) {
                continue;
            }
            ItemStack playerHead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta playerHeadMeta = (SkullMeta) playerHead.getItemMeta();
            playerHeadMeta.setDisplayName("§3" + onlinePlayers.get(i).getName());
            playerHead.setItemMeta(playerHeadMeta);
            playerListMenu.addItem(playerHead);
        }

        player.openInventory(playerListMenu);
        return true;
    }
}

