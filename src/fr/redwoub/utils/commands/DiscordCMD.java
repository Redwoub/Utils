package fr.redwoub.utils.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("Seul un joueur peux executer cette commande");
            return false;
        }

        sender.sendMessage("§9───────────────────");
        sender.sendMessage(" ");
        sender.sendMessage("§eDiscord : §7discord.gg/XEHzSNG9Ka");
        sender.sendMessage(" ");
        sender.sendMessage("§9───────────────────");
        return false;
    }
}
