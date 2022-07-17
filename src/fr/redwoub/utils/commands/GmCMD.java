package fr.redwoub.utils.commands;

import fr.redwoub.utils.Main;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class GmCMD implements CommandExecutor {
    private final String prefix = ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.prefix"));
    private final FileConfiguration config = Main.getInstance().getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.not-a-player")));
            return false;
        }
        Player player = (Player) sender;

        if(!player.hasPermission(Main.getInstance().getConfig().getString("permissions.gm"))){
            player.sendMessage(prefix + " " + ChatColor.translateAlternateColorCodes('&', config.getString("messages.dont-have-permission")));
            return false;
        }

        if(args.length != 1){
            player.sendMessage(prefix + " §cError §8: §e/gm 0 | 1 | 2 | 3");
            return false;
        }

        if(args[0].equalsIgnoreCase("0")){
            player.setGameMode(GameMode.SURVIVAL);
            player.sendMessage(prefix + " §aYour gamemode has change into gamemode survival");
            return false;
        }
        if(args[0].equalsIgnoreCase("1")){
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage(prefix + " §aYour gamemode has change into gamemode creative");
            return false;
        }
        if(args[0].equalsIgnoreCase("2")){
            player.setGameMode(GameMode.ADVENTURE);
            player.sendMessage(prefix + " §aYour gamemode has change into gamemode adventure");
            return false;
        }
        if(args[0].equalsIgnoreCase("3")){
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage(prefix + " §aYour gamemode has change into gamemode spectator");
            return false;
        }
        player.sendMessage(prefix + " §cThis gamemode doesn't exist");
        return false;
    }
}
