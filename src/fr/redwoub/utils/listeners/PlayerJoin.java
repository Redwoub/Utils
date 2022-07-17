package fr.redwoub.utils.listeners;

import fr.redwoub.utils.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        FileConfiguration config = Main.getInstance().getConfig();
        Main.getInstance().flying.put(event.getPlayer().getUniqueId(), false);
        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.join").replace("%p", event.getPlayer().getDisplayName())));

        if(Main.getInstance().getConfig().getBoolean("teleportation.enable")){
            Location location = new Location(event.getPlayer().getWorld(), config.getDouble("teleportation.x"), config.getDouble("teleportation.y"), config.getDouble("teleportation.z"), (float) config.getDouble("teleportation.yaw"), (float) config.getDouble("teleportation.pich"));
            event.getPlayer().teleport(location);
        }
    }
}
