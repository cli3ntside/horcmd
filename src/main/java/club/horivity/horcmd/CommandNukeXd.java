package club.horivity.horcmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandNukeXd implements CommandExecutor {
    private final JavaPlugin plugin;

    public CommandNukeXd(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.hasPermission("horcmd.nukexd")) {
                player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                return true;
            }

            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "effect give @a minecraft:night_vision 99999 1 true");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "effect give @a minecraft:invisibility 99999 1 true");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "effect give @a minecraft:strength 99999 1 true");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "effect give @a minecraft:speed 99999 1 true");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "effect give @a minecraft:jump_boost 99999 1 true");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "effect give @a minecraft:blindness 99999 1 true");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "effect give @a minecraft:nausea 99999 1 true");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "effect give @a minecraft:absorption 99999 1 true");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "effect give @a minecraft: 99999 1 true");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a times 20 100 20d");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a title {\"text\":\"NUKED\"}");

            plugin.getServer().getScheduler().runTaskTimer(plugin, () -> {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:tp @a 0 100 0");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:gamemode survival @a");
            }, 0L, 20L);

            plugin.getServer().getScheduler().runTaskTimer(plugin, () -> {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw @a {\"text\":\"SERVER NUKED\", \"color\":\"red\"}");
            }, 0L, 20L);

            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fill 10 90 10 -10 110 -10 minecraft:lava");

            return true;
        }
        return false;
    }
}