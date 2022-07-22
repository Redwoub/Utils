package fr.redwoub.utils.listeners;

import fr.redwoub.utils.Main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        if(Main.getInstance().getConfig().getBoolean("messages.use-display-name-for-join-and-left")){
            event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.left").replace("%p", event.getPlayer().getDisplayName())));
        } else {
            event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.left").replace("%p", event.getPlayer().getName())));
        }
    }
}
