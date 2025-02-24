package org.example.dmytrok.dkmenuplugin.inventoryrules;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.dmytrok.dkmenuplugin.DK_Menu_Plugin;
import org.example.dmytrok.dkmenuplugin.playermenu.backpack.BackpackCommand;

import java.util.HashSet;
import java.util.Set;

public class TwoSwordsRule implements Listener {

    private final Set<Player> warnedPlayers = new HashSet<>();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if(event.getClickedInventory() instanceof PlayerInventory) {
            return;
        }

        if(isPlayerHasTwoSwords(player) == 2 && isSword(clickedItem)) {
            if(event.getClickedInventory().getTitle().equals("§3Backpack")) {
                player.sendMessage("§4First remove the sword from your inventory");
                event.setCancelled(true);
                return;
            }
            event.setCancelled(true);

            if (!warnedPlayers.contains(player)) {
                player.sendMessage("§4You cannot carry more than 2 swords in your inventory!");
                warnedPlayers.add(player);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        warnedPlayers.remove(player);
                    }
                }.runTaskLater(DK_Menu_Plugin.getInstance(), 80);
            }

            ItemStack swordCopy = new ItemStack(clickedItem);
            if(BackpackCommand.addToBackpack(player, swordCopy)) {
                event.setCurrentItem(null);
            }
        }
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if(!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();
        ItemStack item = event.getItem().getItemStack();

        if(isPlayerHasTwoSwords(player) == 2 && isSword(item)) {
            event.setCancelled(true);

            if (!warnedPlayers.contains(player)) {
                player.sendMessage("§4You cannot carry more than 2 swords in your inventory!");
                warnedPlayers.add(player);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        warnedPlayers.remove(player);
                    }
                }.runTaskLater(DK_Menu_Plugin.getInstance(), 80);
            }

            ItemStack swordCopy = new ItemStack(item);
            if(BackpackCommand.addToBackpack(player, swordCopy)) {
                event.getItem().remove();
            }
        }
    }

    private int isPlayerHasTwoSwords(Player player) {
        int swordCounter = 0;
        for (ItemStack item : player.getInventory().getContents()) {
            if (isSword(item)) {
                swordCounter++;
            }
        }
        return swordCounter;
    }

    private boolean isSword(ItemStack item) {
        if(item != null && !(item.getType().equals(Material.AIR))) {
            if (item.getType().equals(Material.DIAMOND_SWORD)
                    || item.getType().equals(Material.IRON_SWORD)
                    || item.getType().equals(Material.GOLD_SWORD)
                    || item.getType().equals(Material.STONE_SWORD)
                    || item.getType().equals(Material.WOOD_SWORD)) {
                return true;
            }
        }
        return false;
    }
}
