package club.horivity.horcmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import java.util.Random;

public class CommandRandomLol implements CommandExecutor {
    private final Random random = new Random();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.hasPermission("horcmd.randomlol")) {
                player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                return true;
            }

            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "Usage: /randomlol <numbers|min|max> | /randomlol player");
                return true;
            }

            if (args[0].equalsIgnoreCase("numbers")) {
                if (args.length != 3) {
                    player.sendMessage(ChatColor.RED + "Usage: /randomlol numbers <min> <max>");
                    return true;
                }

                try {
                    int min = Integer.parseInt(args[1]);
                    int max = Integer.parseInt(args[2]);

                    if (min >= max) {
                        player.sendMessage(ChatColor.RED + "The min value must be less than the max value.");
                        return true;
                    }

                    int randomNumber = random.nextInt((max - min) + 1) + min;
                    String message = String.format("{\"text\":\"Рандомное число: (от %d до %d): %d\"}", min, max, randomNumber);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw @a " + message);
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "Min and max must be valid integers.");
                }
                return true;
            } else if (args[0].equalsIgnoreCase("player")) {
                Player[] onlinePlayers = Bukkit.getOnlinePlayers().toArray(new Player[0]);

                if (onlinePlayers.length == 0) {
                    player.sendMessage(ChatColor.RED + "No players are currently online.");
                    return true;
                }

                Player randomPlayer = onlinePlayers[random.nextInt(onlinePlayers.length)];
                String message = String.format("{\"text\":\"Случайный игрок: %s\"}", randomPlayer.getName());
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw @a " + message);
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "Usage: /randomlol <numbers|min|max> | /randomlol player");
                return true;
            }
        }
        return false;
    }
}