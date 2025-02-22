package org.example.dmytrok.dkmenuplugin.playermenu.personalAccount;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class PersonalAccountEvents implements Listener {
    public static HashMap<UUID, String> pickUpSettings = new HashMap<>();

    @EventHandler
    public void onPlayerTakesFromPlayerAccount(InventoryClickEvent event) {
        if (!(isPlayerAccount(event.getInventory()))) {
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

        if (clickedItem.getType().equals(Material.INK_SACK)) {

            ItemStack redDye = new ItemStack(Material.INK_SACK, 1, (short) 1);
            ItemStack limeDye = new ItemStack(Material.INK_SACK, 1, (short) 10);
            if (clickedItem.getData().equals(redDye.getData())
                    && clickedItem.getItemMeta().getDisplayName().equals("§aEnable Pick Up Messages")) {
                player.closeInventory();
                pickUpSettings.put(player.getUniqueId(), "enable");
                player.performCommand("pickup enable");
            }

            if (clickedItem.getData().equals(limeDye.getData())
                    && clickedItem.getItemMeta().getDisplayName().equals("§cDisable Pick Up Messages")) {
                player.closeInventory();
                pickUpSettings.put(player.getUniqueId(), "disable");
                player.performCommand("pickup disable");
            }

        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        pickUpSettings.put(player.getUniqueId(), "disable");
    }

    public static String getPickUpSetting(Player player) {
        return pickUpSettings.get(player.getUniqueId());
    }

    private boolean isPlayerAccount(Inventory inventory) {
        if (!(inventory.getTitle().equals("§9Personal Account"))) {
            return false;
        }
        if (inventory.getSize() != 45) {
            return false;
        }
        return true;
    }
}
