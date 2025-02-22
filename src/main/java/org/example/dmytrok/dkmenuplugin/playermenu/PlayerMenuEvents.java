package org.example.dmytrok.dkmenuplugin.playermenu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class PlayerMenuEvents implements Listener {

    @EventHandler
    public void onPlayerTakesFromMenu(InventoryClickEvent event) {
        if (!(isMenu(event.getInventory()))) {
            return;
        }
        Inventory inventory = event.getInventory();

        if (inventory == null) {
            return;
        }

        if (event.getClick().isKeyboardClick()) {
            event.setCancelled(true);
            return;
        }
        if (event.getClick().isRightClick()) {
            event.setCancelled(true);
            return;
        }
        if (event.getClick().isShiftClick()) {
            event.setCancelled(true);
            return;
        }

        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            return;
        }

        if (clickedItem.getType().equals(Material.SKULL_ITEM)
                && clickedItem.getItemMeta().getDisplayName().equals("§6Personal Account")) {
            player.closeInventory();
            player.performCommand("personalAccount");
        }
        event.setCancelled(true);
    }

    private boolean isMenu(Inventory inventory) {
        if (!(inventory.getTitle().equals("§l§bMenu"))) {
            return false;
        }
        if (inventory.getSize() != 45) {
            return false;
        }
        return true;
    }
}


