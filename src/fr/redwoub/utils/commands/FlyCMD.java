package fr.redwoub.utils.commands;

import fr.redwoub.utils.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
            if(player.hasPermission(Main.getInstance().getConfig().getString("permissions.fly"))){
                if(Main.getInstance().flying.get(player.getUniqueId()) == true){
                    player.setAllowFlight(false);
                    player.setFlying(false);
                    Main.getInstance().flying.remove(player.getUniqueId());
                    Main.getInstance().flying.put(player.getUniqueId(), false);
                    player.sendMessage("§cFly disable");
                }else {
                    player.setAllowFlight(true);
                    player.setFlying(true);
                    Main.getInstance().flying.remove(player.getUniqueId());
                    Main.getInstance().flying.put(player.getUniqueId(), true);
                    player.sendMessage("§aFly enable");
                }
            }
        }

        if(args.length == 1){
            if(player.hasPermission(Main.getInstance().getConfig().getString("permissions.flyother"))){
                if(Bukkit.getPlayer(args[0]) == null){
                    player.sendMessage("§cThis player doesn't exist !");
                    return false;
                }

                Player target = Bukkit.getPlayer(args[0]);
                if(Main.getInstance().flying.get(target.getUniqueId()) == true){
                    target.setAllowFlight(false);
                    target.setFlying(false);
                    Main.getInstance().flying.remove(target.getUniqueId());
                    Main.getInstance().flying.put(target.getUniqueId(), false);
                    player.sendMessage("§cFly disable for : §b" + target.getName());
                    target.sendMessage("§cFly disable by : §b" + player.getName());
                }else {
                    target.setAllowFlight(true);
                    target.setFlying(true);
                    Main.getInstance().flying.remove(target.getUniqueId());
                    Main.getInstance().flying.put(target.getUniqueId(), true);
                    player.sendMessage("§aFly enable for : §b" + target.getName());
                    target.sendMessage("§aFly enable by : §b" + player.getName());
                }
            }
        }

        return false;
    }
}
