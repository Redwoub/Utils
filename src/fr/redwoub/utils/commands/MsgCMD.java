package fr.redwoub.utils.commands;

import fr.redwoub.utils.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class MsgCMD implements CommandExecutor {
    private final String prefix = ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.prefix"));
    private final FileConfiguration config = Main.getInstance().getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.not-a-player")));
            return false;
        }

        Player player = (Player) sender;
        if(args.length < 2){
            player.sendMessage(prefix + " " + ChatColor.translateAlternateColorCodes('&', config.getString("errors.msg-invalid-arguments")));
            return false;
        }

        if(!player.hasPermission(config.getString("permissions.msg"))){
            player.sendMessage(prefix + " " +ChatColor.translateAlternateColorCodes('&', config.getString("messages.dont-have-permission")));
            return false;
        }

        if(Bukkit.getPlayer(args[0]) == null){
            player.sendMessage(prefix + " " + ChatColor.translateAlternateColorCodes('&', config.getString("messages.player-doesnt-exist")));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);
        YamlConfiguration data = YamlConfiguration.loadConfiguration(Main.instance.getFile());
        if(data.getString(target.getName()).equalsIgnoreCase("false")){
            player.sendMessage(prefix + " " + ChatColor.translateAlternateColorCodes('&', config.getString("messages.player-disable-msg")));
            return false;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 1; i < args.length; i++){
            stringBuilder.append(args[i]).append(" ");
        }

        if(Main.getInstance().getConfig().getBoolean("msg.use-display-name")){
            target.sendMessage(prefix + " " + ChatColor.translateAlternateColorCodes('&', config.getString("msg.target-recive-message-format").replace("%p", player.getDisplayName()).replace("%m", stringBuilder.toString())));
            player.sendMessage(prefix + " " + ChatColor.translateAlternateColorCodes('&', config.getString("msg.player-recive-message-format").replace("%p", target.getDisplayName()).replace("%m", stringBuilder.toString())));
        } else {
            target.sendMessage(prefix + " " + ChatColor.translateAlternateColorCodes('&', config.getString("msg.target-recive-message-format").replace("%p", player.getName()).replace("%m", stringBuilder.toString())));
            player.sendMessage(prefix + " " + ChatColor.translateAlternateColorCodes('&', config.getString("msg.player-recive-message-format").replace("%p", target.getName()).replace("%m", stringBuilder.toString())));
        }
        return false;
    }
}
