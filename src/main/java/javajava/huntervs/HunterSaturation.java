package javajava.huntervs;

import javajava.huntervs.HunterVS;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class HunterSaturation extends JavaPlugin {
    public static void setsaturation() {
        BukkitRunnable task = new BukkitRunnable() {
            public void run() {
                for (int i = 0; i < HunterVS.count; i++) {
                    Bukkit.getPlayer(HunterVS.huntername[i]).setFoodLevel(10);
                }
            }
        };
        task.runTaskTimer(HunterVS.plugin, 0L, 5L);
    }
}
