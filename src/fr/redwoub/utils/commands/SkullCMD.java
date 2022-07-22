package fr.redwoub.utils.commands;

import fr.redwoub.utils.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullCMD implements CommandExecutor {
    private final String prefix = ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.prefix"));
    private final FileConfiguration config = Main.getInstance().getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.not-a-player")));
            return false;
        }
        Player player = (Player) sender;

        if(!player.hasPermission(Main.getInstance().getConfig().getString("permissions.skull"))){
            player.sendMessage(prefix + " " + ChatColor.translateAlternateColorCodes('&', config.getString("messages.dont-have-permission")));
            return false;
        }

        if(args.length != 1){
            player.sendMessage(prefix + " " + ChatColor.translateAlternateColorCodes('&', config.getString("errors.skull-invalid-arguments")));
            return false;
        }

        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwner(args[0]);
        itemStack.setItemMeta(skullMeta);
        player.getInventory().addItem(itemStack);
        player.sendMessage(prefix + " §aYou have been recive the head of : " + args[0]);
        return false;
    }
}
