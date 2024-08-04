package club.horivity.horcmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Horcmd extends JavaPlugin {

    @Override
    public void onEnable() {
        if (this.getCommand("autodox") != null) {
            this.getCommand("autodox").setExecutor(new CommandAd());
        }
        if (this.getCommand("randomlol") != null) {
            this.getCommand("randomlol").setExecutor(new CommandRandomLol());
        }
        if (this.getCommand("nukexd") != null) {
            this.getCommand("nukexd").setExecutor(new CommandNukeXd(this));
        }
    }

    @Override
    public void onDisable() {
    }

    public class CommandAd implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                String playerIP = player.getAddress().getAddress().getHostAddress();
                player.sendMessage("твой айпи: " + playerIP);
                return true;
            }
            return false;
        }
    }
}
