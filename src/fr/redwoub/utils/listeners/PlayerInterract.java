package fr.redwoub.utils.listeners;

import fr.redwoub.utils.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

public class PlayerInterract implements Listener {

    @EventHandler
    public void onInterract(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(!(event.getCurrentItem().getType() == Material.COAL)) return;
        if(Main.flying.contains(player.getUniqueId())){
            Main.flying.remove(player.getUniqueId());
            player.setFlying(false);
            player.setAllowFlight(false);
            player.sendMessage("§cLe fly vous a était désactivé de force !");
        }
    }
}
