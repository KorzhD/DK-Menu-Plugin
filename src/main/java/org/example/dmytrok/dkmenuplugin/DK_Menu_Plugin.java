package org.example.dmytrok.dkmenuplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.example.dmytrok.dkmenuplugin.adminMenu.AdminMenuCommand;
import org.example.dmytrok.dkmenuplugin.adminMenu.AdminMenuEvents;

public final class DK_Menu_Plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("DK_Menu_Plugin enabled");

        getServer().getPluginManager().registerEvents(new AdminMenuEvents(), this);

        if(getCommand("adminMenu") != null) {
            getCommand("adminMenu").setExecutor(new AdminMenuCommand());
        } else {
            getLogger().info("Something WRONG");
        }

    }

    @Override
    public void onDisable() {
        getLogger().info("DK_Disabled_Plugin enabled");
    }
}
