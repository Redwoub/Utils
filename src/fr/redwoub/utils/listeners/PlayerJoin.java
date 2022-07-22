package fr.redwoub.utils.listeners;

import fr.redwoub.utils.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        FileConfiguration config = Main.getInstance().getConfig();
        Main.getInstance().flying.put(event.getPlayer().getUniqueId(), false);
        YamlConfiguration data = YamlConfiguration.loadConfiguration(Main.instance.getFile());
        if(data.get(event.getPlayer().getName()) == null){
            data.set(event.getPlayer().getName(), "true");
            try {
                data.save(Main.getInstance().getFile());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(Main.getInstance().getConfig().getBoolean("messages.use-display-name-for-join-and-left")){
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.join").replace("%p", event.getPlayer().getDisplayName())));
        } else {
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.join").replace("%p", event.getPlayer().getName())));
        }
        if(Main.getInstance().getConfig().getBoolean("teleportation.enable")){
            Location location = new Location(event.getPlayer().getWorld(), config.getDouble("teleportation.x"), config.getDouble("teleportation.y"), config.getDouble("teleportation.z"), (float) config.getDouble("teleportation.yaw"), (float) config.getDouble("teleportation.pich"));
            event.getPlayer().teleport(location);
        }
    }
}
