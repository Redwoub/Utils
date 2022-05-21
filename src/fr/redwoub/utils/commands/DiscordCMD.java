package fr.redwoub.utils.commands;

import fr.redwoub.utils.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        sender.sendMessage("§9───────────────────");
        sender.sendMessage(" ");
        sender.sendMessage("§eDiscord : " + ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.discord")));
        sender.sendMessage(" ");
        sender.sendMessage("§9───────────────────");
        return false;
    }
}
