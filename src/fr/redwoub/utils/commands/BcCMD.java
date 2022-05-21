package fr.redwoub.utils.commands;

import fr.redwoub.utils.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BcCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        StringBuilder stringBuilder;

        if(!sender.hasPermission(Main.getInstance().getConfig().getString("permissions.bc"))){
            sender.sendMessage("You don't have the permission !");
            return false;
        }

        if(args.length == 0){
            sender.sendMessage(ChatColor.RED + "The commande is : /bc <message>");
            return false;
        }
        stringBuilder = new StringBuilder();
        for(String str : args){
            stringBuilder.append(str + " ");
        }

        String alert = stringBuilder.toString();
        alert = alert.replace("&", "§");
        String value = ChatColor.translateAlternateColorCodes('&', stringBuilder.toString());
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.prefix")) + " " + value);
        return false;
    }
}
