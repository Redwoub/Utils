package fr.redwoub.utils.commands;

import fr.redwoub.utils.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(!(sender instanceof Player)){
            sender.sendMessage("Seul un joueur peux executer cette commande");
            return false;
        }
        Player player = (Player) sender;

        if(args.length == 0){
            if(sender.hasPermission("utils.fly")){
                if(Main.flying.contains(player.getUniqueId())){
                    Main.flying.remove(player.getUniqueId());
                    player.setAllowFlight(false);
                    player.setFlying(false);
                    player.sendMessage("§cFly désactivé");
                }else {
                    Main.flying.add(player.getUniqueId());
                    player.setAllowFlight(true);
                    player.setFlying(true);
                    player.sendMessage("§aFly activé");
                }

                return false;
            }else {
                player.sendMessage("§cVous n'avez pas la permisson d'executer cette commande.");
            }
        }

        if(args.length == 1){
            if(player.hasPermission("utils.fly")){
                if(Bukkit.getPlayer(args[0]) == null){
                    player.sendMessage("§cErreur §8: §cCe joueur n'existe pas !");
                    return false;
                }

                Player target = Bukkit.getPlayer(args[0]);

                if(Main.flying.contains(target.getUniqueId())){
                    Main.flying.remove(target.getUniqueId());
                    target.setFlying(false);
                    target.setAllowFlight(false);
                    player.sendMessage("§cFly désactivé pour : " + target.getName());
                }else {
                    Main.flying.add(target.getUniqueId());
                    target.setAllowFlight(true);
                    target.setFlying(true);
                    player.sendMessage("§aFly activé pour : " + target.getName());
                }
            }
        }

        return false;
    }
}
