package fr.redwoub.utils.listeners;

import fr.redwoub.utils.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(Main.flying.contains(player)){
            Main.flying.remove(player);
        }
    }
}
