package com.jack.messaging;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//       /message <player> <message>

public class MessageCommand implements CommandExecutor {

    private Main main;
    public MessageCommand(Main main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length >= 2) {
                if (Bukkit.getPlayerExact(args[0]) != null) {
                    Player target = Bukkit.getPlayerExact(args[0]);

                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        builder.append(args[i]).append(" ");
                    }

                    player.sendMessage("§e[§cYou§e -> §c" + target.getName() + "§e]: " + builder);
                    target.sendMessage("§e[§c" + player.getName() + "§e -> §cYou§e]: " + builder);

                    main.getRecentMessages().put(player.getUniqueId(), target.getUniqueId());
                } else {
                    player.sendMessage("§cThis player was not found!");
                }
            } else {
                player.sendMessage("§cInvalid usage! Use /message <player> <message>.");
            }
        }

        return false;
    }
}
