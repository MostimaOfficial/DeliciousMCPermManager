package pkg.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import pkg.data.User;
import pkg.main.DeliciousPermissions;

/**
 * @author ElgarL
 * 
 */
public class DPUserEvent extends Event {

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

	protected User user;

	protected String userName;

	protected Action action;

	public DPUserEvent(User user, Action action) {

		super();

		this.user = user;
		this.action = action;
		this.userName = user.getLastName();
	}

	public DPUserEvent(String userName, Action action) {

		super();

		this.userName = userName;
		this.action = action;
	}

	public Action getAction() {

		return this.action;
	}

	public User getUser() {

		return user;
	}

	public String getUserName() {

		return userName;
	}

	public enum Action {
		USER_PERMISSIONS_CHANGED, USER_INHERITANCE_CHANGED, USER_INFO_CHANGED, USER_GROUP_CHANGED, USER_SUBGROUP_CHANGED, USER_ADDED, USER_REMOVED,
	}

	public void schedule(final DPUserEvent event) {

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