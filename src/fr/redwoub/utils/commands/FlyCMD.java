package fr.redwoub.utils.commands;

import fr.redwoub.utils.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class FlyCMD implements CommandExecutor {
    private final String prefix = ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.prefix"));
    private final FileConfiguration config = Main.getInstance().getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.not-a-player")));
            return false;
        }

        Player player = (Player) sender;

        if(args.length == 0){
            if(player.hasPermission(Main.getInstance().getConfig().getString("permissions.fly"))){
                if(Main.getInstance().flying.get(player.getUniqueId())){
                    player.setAllowFlight(false);
                    player.setFlying(false);
                    Main.getInstance().flying.remove(player.getUniqueId());
                    Main.getInstance().flying.put(player.getUniqueId(), false);
                    player.sendMessage(prefix + " §cFly disable");
                }else {
                    player.setAllowFlight(true);
                    player.setFlying(true);
                    Main.getInstance().flying.remove(player.getUniqueId());
                    Main.getInstance().flying.put(player.getUniqueId(), true);
                    player.sendMessage(prefix + " §aFly enable");
                }
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.dont-have-permission")));
            }
        }

        if(args.length == 1){
            if(player.hasPermission(Main.getInstance().getConfig().getString("permissions.flyother"))){
                if(Bukkit.getPlayer(args[0]) == null){
                    player.sendMessage(prefix + " " + ChatColor.translateAlternateColorCodes('&', config.getString("messages.player-doesnt-exist")));
                    return false;
                }

                Player target = Bukkit.getPlayer(args[0]);
                if(Main.getInstance().flying.get(target.getUniqueId())){
                    target.setAllowFlight(false);
                    target.setFlying(false);
                    Main.getInstance().flying.remove(target.getUniqueId());
                    Main.getInstance().flying.put(target.getUniqueId(), false);
                    player.sendMessage(prefix + " §cFly disable for : §b" + target.getName());
                    target.sendMessage(prefix + " §cFly disable by : §b" + player.getName());
                }else {
                    target.setAllowFlight(true);
                    target.setFlying(true);
                    Main.getInstance().flying.remove(target.getUniqueId());
                    Main.getInstance().flying.put(target.getUniqueId(), true);
                    player.sendMessage(prefix + " §aFly enable for : §b" + target.getName());
                    target.sendMessage(prefix + " §aFly enable by : §b" + player.getName());
                }
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.dont-have-permission")));
            }
        }

        return false;
    }
}
