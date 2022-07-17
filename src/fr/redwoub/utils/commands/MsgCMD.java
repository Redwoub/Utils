package fr.redwoub.utils.commands;

import fr.redwoub.utils.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
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
        if(args.length < 3){
            player.sendMessage(prefix + " §cError §8: §e/msg <Player> <Message>");
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
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 1; i < args.length; i++){
            stringBuilder.append(args[i]).append(" ");
        }

        target.sendMessage(prefix + " §bFrom " + player.getDisplayName() + " §8: §7" + stringBuilder.toString());
        player.sendMessage(prefix + " §bTo " + target.getDisplayName() + " §8: §7" + stringBuilder.toString());
        return false;
    }
}
