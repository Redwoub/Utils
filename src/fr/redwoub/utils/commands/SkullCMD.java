package fr.redwoub.utils.commands;

import fr.redwoub.utils.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Only a player can execute this command !");
            return false;
        }

        if(!sender.hasPermission(Main.getInstance().getConfig().getString("permissions.skull"))){
            sender.sendMessage("You don't have the permission !");
            return false;
        }

        Player player = (Player) sender;

        if(args.length != 1){
            player.sendMessage("§cErreur §8: §e/skull <SkullOwner>");
            return false;
        }

        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwner(args[0]);
        itemStack.setItemMeta(skullMeta);
        player.getInventory().addItem(itemStack);
        player.sendMessage("§aYou have been recive the head of : " + args[0]);
        return false;
    }
}
