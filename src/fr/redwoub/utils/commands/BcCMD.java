package fr.redwoub.utils.commands;

import fr.redwoub.utils.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class BcCMD implements CommandExecutor {
    private final String prefix = ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.prefix"));
    private final FileConfiguration config = Main.getInstance().getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        StringBuilder stringBuilder;

        if(!sender.hasPermission(Main.getInstance().getConfig().getString("permissions.bc"))){
            sender.sendMessage(prefix + " " + ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.dont-have-permission")));
            return false;
        }

        if(args.length == 0){
            sender.sendMessage(prefix + " " + ChatColor.translateAlternateColorCodes('&', config.getString("errors.bc-invalid-arguments")));
            return false;
        }
        stringBuilder = new StringBuilder();
        for(String str : args){
            stringBuilder.append(str).append(" ");
        }
        String value = ChatColor.translateAlternateColorCodes('&', stringBuilder.toString());
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.prefix")) + " " + value);
        return false;
    }
}
