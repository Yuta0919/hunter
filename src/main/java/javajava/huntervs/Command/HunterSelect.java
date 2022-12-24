package javajava.huntervs.Command;

import javajava.huntervs.HunterVS;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class HunterSelect implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,@NotNull String[] args) {
        if(sender.isOp()){
            HunterVS.huntername[HunterVS.count]=args[0];
            sender.sendMessage(ChatColor.RED+
                    HunterVS.huntername[HunterVS.count]+"をハンターに指定しました");
            Bukkit.getPlayer(HunterVS.huntername[HunterVS.count]).setMaxHealth(200);
            Bukkit.getPlayer(HunterVS.huntername[HunterVS.count]).setHealth(200);
            HunterVS.count++;
        }
        return true;
    }
}
