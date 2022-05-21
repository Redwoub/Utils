package fr.redwoub.utils.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(!(sender instanceof Player)){
            sender.sendMessage("Seul un joueur peux executer cette commande !");
            return false;
        }

        Player player = (Player) sender;

        if(args.length != 1){
            player.sendMessage("§cErreur §8: §e/gm 0 | 1 | 2 | 3");
            return false;
        }

        if(args[0].equalsIgnoreCase("0")){
            player.setGameMode(GameMode.SURVIVAL);
            return false;
        }
        if(args[0].equalsIgnoreCase("1")){
            player.setGameMode(GameMode.CREATIVE);
            return false;
        }
        if(args[0].equalsIgnoreCase("2")){
            player.setGameMode(GameMode.ADVENTURE);
            return false;
        }
        if(args[0].equalsIgnoreCase("3")){
            player.setGameMode(GameMode.SPECTATOR);
            return false;
        }
        player.sendMessage("Ce mode de jeu n'existe pas !");
        return false;
    }
}
