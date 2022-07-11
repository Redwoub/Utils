package fr.redwoub.utils.listeners;

import fr.redwoub.utils.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Main.getInstance().flying.put(event.getPlayer().getUniqueId(), false);
    }
}
