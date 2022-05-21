package fr.redwoub.utils.commands;

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

        if(args.length == 0){
            sender.sendMessage(ChatColor.RED + "La commande est : /bc <message>");
            return false;
        }
        stringBuilder = new StringBuilder();
        for(String str : args){
            stringBuilder.append(str + " ");
        }

        String alert = stringBuilder.toString();
        alert = alert.replace("&", "§");
        String value = ChatColor.translateAlternateColorCodes('&', stringBuilder.toString());
        Bukkit.broadcastMessage("§b[§6Mydoria§b]§a " + value);
        return false;
    }
}
