package fr.redwoub.utils.listeners;

import fr.redwoub.utils.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Main.getInstance().flying.put(event.getPlayer().getUniqueId(), false);
    }
}
