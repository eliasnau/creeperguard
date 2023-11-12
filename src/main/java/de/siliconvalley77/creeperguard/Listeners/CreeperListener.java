package de.siliconvalley77.creeperguard.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;

public class CreeperListener implements Listener {
    private final Plugin plugin;

    public CreeperListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if (event.getEntityType() == EntityType.CREEPER) {
            boolean destroyBlocks = plugin.getConfig().getBoolean("creeper_destroy_blocks");
            boolean canExplode = plugin.getConfig().getBoolean("creeper_can_explode");
            Creeper creeper = (Creeper) event.getEntity();

            if (!canExplode) {
                // Prevent the Creeper from exploding
                creeper.setPowered(false); // Disable any potential powered state
                creeper.setExplosionRadius(0); // Set the explosion radius to 0
                event.setCancelled(true); // Cancel the explosion event
            } else if (!destroyBlocks) {
                event.blockList().clear();
            }
        }
    }
}
