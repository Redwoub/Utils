package fr.redwoub.utils.commands;

import fr.redwoub.utils.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class ToggleMsgCMD implements CommandExecutor {
    private final String prefix = ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.prefix"));
    private final FileConfiguration config = Main.getInstance().getConfig();
    private final YamlConfiguration data = YamlConfiguration.loadConfiguration(Main.instance.getFile());

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.not-a-player")));
            return false;
        }
        Player player = (Player) sender;

        if(args.length > 0){
            player.sendMessage(prefix + " " + ChatColor.translateAlternateColorCodes('&', config.getString("errors.togglemsg-invalid-arguments")));
            return false;
        }

        if(data.getString(player.getName()).equalsIgnoreCase("true")){
            data.set(player.getName(), "false");
            try {
                data.save(Main.getInstance().getFile());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            player.sendMessage(prefix + " " + ChatColor.translateAlternateColorCodes('&', config.getString("messages.disable-msg")));
        } else {
            data.set(player.getName(), "true");
            try {
                data.save(Main.getInstance().getFile());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            player.sendMessage(prefix + " " + ChatColor.translateAlternateColorCodes('&', config.getString("messages.enable-msg")));
        }

        return false;
    }
}
