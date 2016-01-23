package me.kevinnovak.safeitemframes;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SafeItemFrames extends JavaPlugin implements Listener{
    // ======================
    // Enable
    // ======================
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        if (getConfig().getBoolean("metrics")) {
            try {
                MetricsLite metrics = new MetricsLite(this);
                metrics.start();
                Bukkit.getServer().getLogger().info("[SafeItemFrames] Metrics Enabled!");
            } catch (IOException e) {
                Bukkit.getServer().getLogger().info("[SafeItemFrames] Failed to Start Metrics.");
            }
        } else {
            Bukkit.getServer().getLogger().info("[SafeItemFrames] Metrics Disabled.");
        }
        Bukkit.getServer().getLogger().info("[SafeItemFrames] Plugin Enabled!");
    }
    
    // ======================
    // Disable
    // ======================
    public void onDisable() {
        Bukkit.getServer().getLogger().info("[EasyBoats] Plugin Disabled!");
    }
    
    // ======================
    // Clicking with Item
    // ======================
    @EventHandler
    public void interact(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        if(entity instanceof ItemFrame) {
            ItemFrame itemframe = (ItemFrame) entity;
            player.getInventory().addItem(itemframe.getItem());
        }
    }
}
