package me.kevinnovak.safeitemframes;
import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class SafeItemFrames extends JavaPlugin implements Listener{

    public File frameFile = new File(getDataFolder()+"/frames.yml");
    public FileConfiguration frameData = YamlConfiguration.loadConfiguration(frameFile);
    
    
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
    // Whose frame is this?
    // ======================
//    String whoseFrame(Location itemFrameLocation) {
//        String itemFrameWorld = itemFrameLocation.getWorld().getName();
//        int itemFrameX = itemFrameLocation.getBlockX();
//        int itemFrameY = itemFrameLocation.getBlockY();
//        int itemFrameZ = itemFrameLocation.getBlockZ();
//        
//        String toSearch = itemFrameX + ":" + itemFrameY + ":" + itemFrameZ;
//        
//        List<String> configList = frameData.getStringList(itemFrameWorld);
//        
//        for (String string : configList) {
//            if (string.contains(toSearch)) {
//                String[] frameString = string.split(":");
//                return frameString[0];
//            }
//        }
//        return null;
//    }
    
    
    // ======================
    // Return Item
    // ======================
    @EventHandler
    public void interact(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof ItemFrame) {
            
            if (!(event.getDamager() instanceof Player)) {
                return;
            }
            
            Player player = (Player) event.getDamager();
//            Location itemFrameLocation = entity.getLocation();
            
//            if (whoseFrame(itemFrameLocation) == null) {
//                return;
//            }
//            
//            String owner = whoseFrame(itemFrameLocation);
//            if (!(owner.equalsIgnoreCase(player.getName()))) {
//                player.sendMessage("Owner: " + owner + ".");
//                player.sendMessage("Interacter: " + player.getName() + ".");
//                event.setCancelled(true);
//                player.sendMessage(convertedLang("notYours").replace("{PLAYER}", owner));
//                return;
//            }
            
            ItemFrame itemframe = (ItemFrame) entity;
            ItemStack itemToGive = itemframe.getItem();
            itemframe.setItem(null);
            player.getInventory().addItem(itemToGive); 
        }
    }
    
    // ===========================
    // Protect Item Frame on Place
    // ===========================
//    @EventHandler
//    public void onPlace(HangingPlaceEvent event) {
//        Entity entity = event.getEntity();
//        if (entity instanceof ItemFrame) {
//            
//            Player player = event.getPlayer();
//            String playername = player.getName();
//            player.sendMessage("ItemFrame Placed!");
//            
//            Location itemFrameLocation = entity.getLocation();
//            String itemFrameWorld = itemFrameLocation.getWorld().getName();
//            int itemFrameX = itemFrameLocation.getBlockX();
//            int itemFrameY = itemFrameLocation.getBlockY();
//            int itemFrameZ = itemFrameLocation.getBlockZ();
//       
//            String itemFrameLocationString = playername + ":" + itemFrameX + ":" + itemFrameY + ":" + itemFrameZ;
//            
//            player.sendMessage("String Created: " + itemFrameLocationString);
//            
//            List<String> configList = new ArrayList<String>();
//            if (frameData.getStringList(itemFrameWorld) != null) {
//                configList = frameData.getStringList(itemFrameWorld);
//            }
//            if (!configList.contains(itemFrameLocationString)) {
//                configList.add(itemFrameLocationString);
//                frameData.set(itemFrameWorld, configList);
//                try {
//                    frameData.save(frameFile);
//                } catch (IOException e1) {
//                    // TODO Auto-generated catch block
//                    e1.printStackTrace();
//                }
//            }   
//        }
//    }
    
    // ===========================
    // Stop Breaking if Protected
    // ===========================
//    @EventHandler
//    public void onBreak(HangingBreakByEntityEvent event) {
//        Entity entity = event.getEntity();
//        if (entity instanceof ItemFrame) {
//            
//            if (!(event.getRemover() instanceof Player)) {
//                return;
//            }
//            
//            Player player = (Player) event.getRemover();
//            Location itemFrameLocation = entity.getLocation();
//            
//            if (whoseFrame(itemFrameLocation) == null) {
//                return;
//            }
//            
//            String owner = whoseFrame(itemFrameLocation);
//            if (!(owner.equalsIgnoreCase(player.getName()))) {
//                player.sendMessage("Owner: " + owner + ".");
//                player.sendMessage("Interacter: " + player.getName() + ".");
//                event.setCancelled(true);
//                player.sendMessage(convertedLang("notYours").replace("{PLAYER}", owner));
//                return;
//            }
//        }
//    }
    
    // =========================
    // Convert String in Config
    // =========================
    String convertedLang(String toConvert) {
        return ChatColor.translateAlternateColorCodes('&', getConfig().getString(toConvert));
    }
}
