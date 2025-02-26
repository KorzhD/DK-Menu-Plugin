package org.example.dmytrok.dkmenuplugin.tradesystem;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TradeEvent implements Listener {

    @EventHandler
    public void onPlayerTakesFromMenu(InventoryClickEvent event) {
        if (!(isTradeMenu(event.getInventory()))) {
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

        if (clickedItem.getType().equals(Material.SKULL_ITEM)) {
            event.setCancelled(true);
            player.closeInventory();
            String playerName = clickedItem.getItemMeta().getDisplayName().substring(2);
            player.performCommand("trade " + playerName);
        }

    }
    private boolean isTradeMenu(Inventory inventory) {
        if (!(inventory.getTitle().equals("§2Select a player to Trade"))) {
            return false;
        }
        if (inventory.getSize() != 45) {
            return false;
        }
        return true;
    }

}
