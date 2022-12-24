package javajava.huntervs;

import javajava.huntervs.Command.HunterSelect;
import net.md_5.bungee.api.chat.*;
import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class HunterVS extends JavaPlugin implements Listener {
public static String[] huntername=new String[1000];
public static JavaPlugin plugin;
public static int count;
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin=this;
        HunterSaturation.setsaturation();
        getCommand("hunterselect").setExecutor(new HunterSelect());
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    @EventHandler
    public static void onHunterDeath(PlayerDeathEvent e){
        for(int i=0;i<count;i++) {
            if (e.getEntity().getName().equals(huntername[i])) {
                Firework f = e.getEntity().getWorld().spawn(e.getEntity().getLocation(), Firework.class);
                FireworkMeta data = f.getFireworkMeta();
                data.addEffects(FireworkEffect.builder().withColor(Color.PURPLE).withColor(Color.GREEN).with(FireworkEffect.Type.BALL_LARGE).withFlicker().build());
                data.setPower(1);
                f.setFireworkMeta(data);
                Bukkit.getOnlinePlayers().forEach((player) -> {
                    Location loc = player.getLocation();
                    loc.getWorld().playSound(loc, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0F, 1.0F);
                    player.spigot().sendMessage(
                            sendHoverText("挑戦", ChatColor.DARK_PURPLE+"[テスト]", "を達成しました","てすと",null));
                });
            }
        }
    }
    public static BaseComponent[] sendHoverText(String beforetext ,String text,String aftertext, String hoverText, String command){
        HoverEvent hoverEvent = null;
        if(hoverText != null){
            BaseComponent[] hover = new ComponentBuilder(hoverText).create();
            hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, hover);
        }
        ClickEvent clickEvent = null;
        if(command != null){
            clickEvent = new ClickEvent(ClickEvent.Action.RUN_COMMAND,command);
        }

        BaseComponent[] message = new ComponentBuilder(beforetext).append(text).event(hoverEvent).event(clickEvent).append(aftertext). create();
        return message;
    }
}
