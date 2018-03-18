package pkg.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;

import pkg.main.DeliciousPermissions;

/**
 * @author ElgarL
 * 
 *         Handle new world creation from other plugins
 * 
 */
public class DPWorldListener implements Listener {

	private final DeliciousPermissions plugin;

	public DPWorldListener(DeliciousPermissions instance) {

		plugin = instance;
		registerEvents();
	}

	private void registerEvents() {

		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onWorldInit(WorldInitEvent event) {

		String worldName = event.getWorld().getName();

		if (DeliciousPermissions.isLoaded() && !plugin.getWorldsHolder().isInList(worldName)) {
			DeliciousPermissions.logger.info("New world detected...");
			DeliciousPermissions.logger.info("Creating data for: " + worldName);
			
			if (plugin.getWorldsHolder().isWorldKnown("all_unnamed_worlds")) {
				
				String usersMirror = plugin.getWorldsHolder().getMirrorsUser().get("all_unnamed_worlds");
				String groupsMirror = plugin.getWorldsHolder().getMirrorsGroup().get("all_unnamed_worlds");
				
				if (usersMirror != null)
					plugin.getWorldsHolder().getMirrorsUser().put(worldName.toLowerCase(), usersMirror);
				
				if (groupsMirror != null)
					plugin.getWorldsHolder().getMirrorsGroup().put(worldName.toLowerCase(), groupsMirror);
				
			}
			
			plugin.getWorldsHolder().setupWorldFolder(worldName);
			plugin.getWorldsHolder().loadWorld(worldName);
			
			
			if (plugin.getWorldsHolder().isInList(worldName)) {
				DeliciousPermissions.logger.info("Don't forget to configure/mirror this world in config.yml.");
			} else
				DeliciousPermissions.logger.severe("Failed to configure this world.");
		}
	}
}