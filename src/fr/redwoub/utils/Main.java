package fr.redwoub.utils;

import fr.redwoub.utils.commands.*;
import fr.redwoub.utils.listeners.PlayerInterract;
import fr.redwoub.utils.listeners.PlayerQuit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main extends JavaPlugin {

    public static List<UUID> flying = new ArrayList<>();

    @Override
    public void onEnable() {
        getCommand("bc").setExecutor(new BcCMD());
        getCommand("gm").setExecutor(new GmCMD());
        getCommand("skull").setExecutor(new SkullCMD());
        getCommand("discord").setExecutor(new DiscordCMD());
        getCommand("fly").setExecutor(new FlyCMD());

        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
        getServer().getPluginManager().registerEvents(new PlayerInterract(), this);
    }
        ConfigurationSection
}
