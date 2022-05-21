package fr.redwoub.utils;


import fr.redwoub.utils.commands.*;
import fr.redwoub.utils.listeners.PlayerJoin;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.*;

public class Main extends JavaPlugin {

    public WeakHashMap<UUID, Boolean> flying = new WeakHashMap<>();
    public static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        getCommand("bc").setExecutor(new BcCMD());
        getCommand("gm").setExecutor(new GmCMD());
        getCommand("skull").setExecutor(new SkullCMD());
        getCommand("discord").setExecutor(new DiscordCMD());
        getCommand("fly").setExecutor(new FlyCMD());

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);

    }

    public static Main getInstance() {
        return instance;
    }
}
