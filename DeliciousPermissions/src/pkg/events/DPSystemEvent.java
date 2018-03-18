package pkg.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import pkg.main.DeliciousPermissions;

/**
 * @author ElgarL
 * 
 */
public class DPSystemEvent extends Event {

	/**
	 * 
	 */
	private static final HandlerList handlers = new HandlerList();

	@Override
	public HandlerList getHandlers() {

		return handlers;
	}

	public static HandlerList getHandlerList() {

		return handlers;
	}

	//////////////////////////////

	protected Action action;

	public DPSystemEvent(Action action) {

		super();
		
		this.action = action;
	}

	public Action getAction() {

		return this.action;
	}

	public enum Action {
		RELOADED, SAVED, DEFAULT_GROUP_CHANGED, VALIDATE_TOGGLE,
	}

	public void schedule(final DPSystemEvent event) {

		synchronized (DeliciousPermissions.getGMEventHandler().getServer()) {
			if (DeliciousPermissions.getGMEventHandler().getServer().getScheduler().scheduleSyncDelayedTask(DeliciousPermissions.getGMEventHandler().getPlugin(), new Runnable() {
	
				@Override
				public void run() {
	
					DeliciousPermissions.getGMEventHandler().getServer().getPluginManager().callEvent(event);
				}
			}, 1) == -1)
				DeliciousPermissions.logger.warning("Could not schedule GM Event.");
		}
	}
}