package org.example.dmytrok.dkmenuplugin.adminMenu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class AdminMenuEvents implements Listener {

    @EventHandler
    public void onPlayerTakesFromAdminMenu(InventoryClickEvent event) {
        if (!(isAdminMenu(event.getInventory()))) {
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

        if (clickedItem.getType().equals(Material.GOLD_AXE)
                && clickedItem.getItemMeta().getDisplayName().equals("§c§lGet Grave Breaker")) {
            player.performCommand("getgravebreaker");
        }

        if (clickedItem.getType().equals(Material.TOTEM)
                && clickedItem.getItemMeta().getDisplayName().equals("§5Get Super Key")) {
            player.performCommand("getsuperkey");
        }

        if (clickedItem.getType().equals(Material.MONSTER_EGG)) {
            ItemStack monarch = new ItemStack(Material.MONSTER_EGG, 1, (short) 58);
            ItemStack fireElementKing = new ItemStack(Material.MONSTER_EGG, 1, (short) 61);
            ItemStack guardianOfColdLands = new ItemStack(Material.MONSTER_EGG, 1, (short) 102);

            if (clickedItem.getItemMeta().getDisplayName().equals("§5§lSummon Monarch")) {
                player.closeInventory();
                player.performCommand("zombieboss");
            }

            if (clickedItem.getItemMeta().getDisplayName().equals("§c§lSummon Fire Element King")) {
                player.closeInventory();
                player.performCommand("blazeboss");
            }

            if (clickedItem.getItemMeta().getDisplayName().equals("§b§lSummon Guardian of Cold Lands")) {
                player.closeInventory();
                player.performCommand("golemboss");
            }
        }

        if (clickedItem.getType().equals(Material.STICK)
                && clickedItem.getItemMeta().getDisplayName().equals("§lGet Boss Killer")) {
            player.performCommand("bosskiller");
        }

        event.setCancelled(true);
    }

    private boolean isAdminMenu(Inventory inventory) {
        if (!(inventory.getTitle().equals("§l§5Admin Menu"))) {
            return false;
        }
        if (inventory.getSize() != 45) {
            return false;
        }
        return true;
    }
}
