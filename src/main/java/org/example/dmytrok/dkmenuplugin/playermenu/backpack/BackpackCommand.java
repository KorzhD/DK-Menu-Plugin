package org.example.dmytrok.dkmenuplugin.playermenu.backpack;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.dmytrok.dkmenuplugin.DK_Menu_Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            backpack = Bukkit.createInventory(player, getRows(3), "§3Backpack");
        } else {
            backpack = playerBackpack.get(player);
        }

        //

        // Back Button

        ItemStack backButton = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta backMeta = backButton.getItemMeta();
        backMeta.setDisplayName("§c<- Back");
        backButton.setItemMeta(backMeta);

        // Back Button

        //

        // Trash

        ItemStack trash = new ItemStack(Material.HOPPER_MINECART);
        ItemMeta trashMeta = trash.getItemMeta();
        trashMeta.setDisplayName("§4Trash Can");
        List<String> trashLore = new ArrayList<>();
        trashLore.add("§6Drag an item here to remove it");
        trashMeta.setLore(trashLore);
        trash.setItemMeta(trashMeta);
        // Trash

        //

        // Glass

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 4);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glass.setItemMeta(glassMeta);

        // Glass

        //

        backpack.setItem(0, backButton);
        backpack.setItem(1, glass);
        backpack.setItem(2, glass);
        backpack.setItem(3, glass);
        backpack.setItem(4, trash);
        backpack.setItem(5, glass);
        backpack.setItem(6, glass);
        backpack.setItem(7, glass);
        backpack.setItem(8, glass);

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
                player.playSound(player.getLocation(), Sound.ENTITY_HORSE_SADDLE, 2, 2);
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
                    player.playSound(player.getLocation(), Sound.ENTITY_CAT_HURT, 1, 2);
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
