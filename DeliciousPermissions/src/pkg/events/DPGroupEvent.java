package pkg.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import pkg.data.Group;
import pkg.main.DeliciousPermissions;

/**
 * @author ElgarL
 * 
 */
public class DPGroupEvent extends Event {

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

	protected Group group;

	protected String groupName;

	protected Action action;

	public DPGroupEvent(Group group, Action action) {

		super();

		this.group = group;
		this.action = action;
		this.groupName = group.getName();
	}

	public DPGroupEvent(String groupName, Action action) {

		super();

		this.groupName = groupName;
		this.action = action;
	}

	public Action getAction() {

		return this.action;
	}

	public Group getGroup() {

		return group;
	}

	public String getGroupName() {

		return groupName;
	}

	public enum Action {
		GROUP_PERMISSIONS_CHANGED, GROUP_INHERITANCE_CHANGED, GROUP_INFO_CHANGED, GROUP_ADDED, GROUP_REMOVED,
	}

	public void schedule(final DPGroupEvent event) {

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