package org.example.dmytrok.dkmenuplugin.playermenu.backpack;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BackpackEvents implements Listener {

    @EventHandler
    public void onPlayerTakesFromMenu(InventoryClickEvent event) {
        if (!(isBackpack(event.getInventory()))) {
            return;
        }
        Inventory inventory = event.getInventory();

        if (inventory == null) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            return;
        }

        if (clickedItem.getType().equals(Material.PRISMARINE_SHARD)
                && clickedItem.getItemMeta().getDisplayName().equals("§c<- Back")) {
            event.setCancelled(true);
            player.closeInventory();
            player.performCommand("menu");
        }

        if (clickedItem.getType().equals(Material.STAINED_GLASS_PANE)
                && clickedItem.getItemMeta().getDisplayName().equals(" ")) {
            event.setCancelled(true);
        }

        if (clickedItem.getItemMeta().getDisplayName().equals("§4Trash Can")) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();

        if (!isBackpack(inventory)) return;

        if (event.getSlot() == 4) {
            event.setCancelled(true);
            ItemStack item = event.getCursor();
            String itemName = (item.getItemMeta() != null && item.getItemMeta().hasDisplayName())
                    ? item.getItemMeta().getDisplayName()
                    : item.getType().toString();
            if (item != null && item.getType() != Material.AIR) {

                player.setItemOnCursor(null);
                player.updateInventory();

                player.sendMessage("§c" + itemName + " deleted");

                player.getWorld().spawnParticle(Particle.SMOKE_LARGE, player.getLocation(), 10);
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
            }
        }
    }


    private boolean isBackpack(Inventory inventory) {
        if (!(inventory.getTitle().equals("§3Backpack"))) {
            return false;
        }
        return true;
    }
}
