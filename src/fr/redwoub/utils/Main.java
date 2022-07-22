package fr.redwoub.utils;


import fr.redwoub.utils.commands.*;
import fr.redwoub.utils.listeners.PlayerJoin;
import fr.redwoub.utils.listeners.PlayerQuit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main extends JavaPlugin {

    public WeakHashMap<UUID, Boolean> flying = new WeakHashMap<>();
    public File file = new File(getDataFolder(), "msg.yml");
    public static Main instance;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        if(!getDataFolder().exists()) getDataFolder().mkdir();

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        getCommand("bc").setExecutor(new BcCMD());
        getCommand("gm").setExecutor(new GmCMD());
        getCommand("skull").setExecutor(new SkullCMD());
        getCommand("discord").setExecutor(new DiscordCMD());
        getCommand("fly").setExecutor(new FlyCMD());
        getCommand("msg").setExecutor(new MsgCMD());
        getCommand("togglemsg").setExecutor(new ToggleMsgCMD());

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);

    }

    public static Main getInstance() {
        return instance;
    }

    public File getFile() {
        return file;
    }
}
