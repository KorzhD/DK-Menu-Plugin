package org.example.dmytrok.dkmenuplugin.adminMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class AdminMenuEvents implements Listener {

    public static String pickUpSetting = "disable";

    @EventHandler
    public void onPlayerTakesFromAdminMenu(InventoryClickEvent event) {
        if(!(isAdminMenu(event.getInventory()))) {
            return;
        }
        Inventory inventory = event.getInventory();

        if (inventory == null) {
            return;
        }

        if(event.getClick().isKeyboardClick()) {
            event.setCancelled(true);
            return;
        }
        if(event.getClick().isRightClick()) {
            event.setCancelled(true);
            return;
        }
        if(event.getClick().isShiftClick()) {
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
            player.closeInventory();
            player.performCommand("getgravebreaker");
        }
        if (clickedItem.getType().equals(Material.WOOD_AXE)
                && clickedItem.getItemMeta().getDisplayName().equals("§5§lGet Magic Wand")) {
            player.closeInventory();
            player.performCommand("getmagicwand");
        }
        ItemStack redDye = new ItemStack(Material.INK_SACK, 1, (short) 1);
        if (clickedItem.getType().equals(Material.INK_SACK) && clickedItem.getData().equals(redDye.getData())
                && clickedItem.getItemMeta().getDisplayName().equals("§aEnable Pick Up Messages")) {
            player.closeInventory();
            pickUpSetting = "enable";
            player.performCommand("pickup enable");
        }
        ItemStack limeDye = new ItemStack(Material.INK_SACK, 1, (short) 10);
        if (clickedItem.getType().equals(Material.INK_SACK) && clickedItem.getData().equals(limeDye.getData())
                && clickedItem.getItemMeta().getDisplayName().equals("§aDisable Pick Up Messages")) {
            player.closeInventory();
            player.performCommand("pickup disable");
            pickUpSetting = "disable";
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
