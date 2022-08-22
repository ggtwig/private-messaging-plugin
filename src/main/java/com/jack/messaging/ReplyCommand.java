package com.jack.messaging;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ReplyCommand implements CommandExecutor {

    //       /reply <message>

    private Main main;
    public ReplyCommand(Main main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length >= 1) {
                if (main.getRecentMessages().containsKey(player.getUniqueId())) {
                    UUID uuid = main.getRecentMessages().get(player.getUniqueId());
                    if (Bukkit.getPlayer(uuid) != null) {
                        Player target = Bukkit.getPlayer(uuid);

                        StringBuilder builder = new StringBuilder();
                        for (int i = 0; i < args.length; i++) {
                            builder.append(args[i]).append(" ");
                        }

                        player.sendMessage(ChatColor.YELLOW + "[" + ChatColor.RED + "You" + ChatColor.YELLOW + " -> " + ChatColor.RED + target.getName() + ChatColor.YELLOW + "]: " + builder);
                        target.sendMessage(ChatColor.YELLOW + "[" + ChatColor.RED + player.getName() + ChatColor.YELLOW + " -> " + ChatColor.RED + "You" + ChatColor.YELLOW + "]: " + builder);
                    } else {
                        player.sendMessage(ChatColor.RED + "The player you messaged is not online!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You haven't messaged anyone yet!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Invalid usage! Use /reply <message>.");
            }

        }

        return false;
    }
}
