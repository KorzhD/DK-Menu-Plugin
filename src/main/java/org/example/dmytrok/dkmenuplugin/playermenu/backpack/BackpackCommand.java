package org.example.dmytrok.dkmenuplugin.playermenu.backpack;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.dmytrok.dkmenuplugin.DK_Menu_Plugin;

import java.util.HashMap;
import java.util.Map;

public class BackpackCommand implements CommandExecutor, Listener {

    private static final Map<Player, Long> lastMessageTime = new HashMap<>();
    private static HashMap<Player, Inventory> playerBackpack = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You must be a player");
            return false;
        }

        Player player = (Player) commandSender;

        Inventory backpack = null;
        if (playerBackpack.get(player) == null) {
            backpack = Bukkit.createInventory(player, getRows(6), "§3Backpack");
        } else {
            backpack = playerBackpack.get(player);
        }

        playerBackpack.put(player, backpack);
        player.openInventory(backpack);
        return true;
    }

    private int getRows(int numberOfRows) {
        return numberOfRows * 9;
    }

    public static boolean addToBackpack(Player player, ItemStack item) {
        if (playerBackpack.get(player) != null) {
            Inventory inventory = playerBackpack.get(player);
            if (!(isInventoryFull(inventory))) {
                inventory.addItem(item);
                playerBackpack.put(player, inventory);
                player.sendMessage("§aSword successful move to Backpack");
                return true;
            } else {
                sendDelayedMessage(player, "§cClean your Backpack!");
            }
        } else {
            sendDelayedMessage(player, "§cOpen your backpack first!");
            return false;
        }
        return false;
    }

    private static void sendDelayedMessage(Player player, String message) {
        long currentTime = System.currentTimeMillis();
        if (!lastMessageTime.containsKey(player) || currentTime - lastMessageTime.get(player) >= 4000) {
            lastMessageTime.put(player, currentTime);
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendMessage(message);
                }
            }.runTaskLater(DK_Menu_Plugin.getInstance(), 80);
        }
    }


    private static boolean isInventoryFull(Inventory inventory) {
        return inventory.firstEmpty() == -1;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(!(playerBackpack.containsKey(event.getPlayer()))) {
            Player player = event.getPlayer();
            Inventory backpack = Bukkit.createInventory(player, getRows(6), "§3Backpack");
            playerBackpack.put(player, backpack);
        } else {
            return;
        }
    }
}
