package org.example.dmytrok.dkmenuplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.example.dmytrok.dkmenuplugin.adminMenu.AdminMenuCommand;
import org.example.dmytrok.dkmenuplugin.adminMenu.AdminMenuEvents;
import org.example.dmytrok.dkmenuplugin.playermenu.PlayerMenuCommand;
import org.example.dmytrok.dkmenuplugin.playermenu.PlayerMenuEvents;
import org.example.dmytrok.dkmenuplugin.playermenu.personalAccount.PersonalAccountEvents;
import org.example.dmytrok.dkmenuplugin.playermenu.personalAccount.PesonalAccountCommand;

public final class DK_Menu_Plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("DK_Menu_Plugin enabled");

        getServer().getPluginManager().registerEvents(new AdminMenuEvents(), this);
        getServer().getPluginManager().registerEvents(new PlayerMenuEvents(), this);
        getServer().getPluginManager().registerEvents(new PersonalAccountEvents(), this);

        if(getCommand("adminMenu") != null) {
            getCommand("adminMenu").setExecutor(new AdminMenuCommand());
        } else {
            getLogger().info("Something WRONG");
        }
        if(getCommand("menu") != null) {
            getCommand("menu").setExecutor(new PlayerMenuCommand());
        } else {
            getLogger().info("Something WRONG");
        }
        if(getCommand("personalAccount") != null) {
            getCommand("personalAccount").setExecutor(new PesonalAccountCommand());
        } else {
            getLogger().info("Something WRONG");
        }


    }

    @Override
    public void onDisable() {
        getLogger().info("DK_Disabled_Plugin enabled");
    }
}
