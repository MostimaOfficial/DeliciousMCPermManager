package pkg.events;

import org.bukkit.Server;

import pkg.data.Group;
import pkg.data.User;
import pkg.main.DeliciousPermissions;

/**
 * @author ElgarL
 * 
 *         Handles all Event generation.
 * 
 */
public class DeliciousPermissionsEventHandler {
	
	private final Server server;
	private final DeliciousPermissions plugin;
	

	public DeliciousPermissionsEventHandler(DeliciousPermissions plugin) {
		
		this.plugin = plugin;
		this.server = plugin.getServer();
		
	}

	protected void callEvent(DPGroupEvent event) {

		event.schedule(event);
	}

	protected void callEvent(DPUserEvent event) {

		event.schedule(event);
	}

	protected void callEvent(DPSystemEvent event) {

		event.schedule(event);
	}

	public void callEvent(Group group, DPGroupEvent.Action action) {

		callEvent(new DPGroupEvent(group, action));
	}

	public void callEvent(String groupName, DPGroupEvent.Action action) {

		callEvent(new DPGroupEvent(groupName, action));
	}

	public void callEvent(User user, DPUserEvent.Action action) {

		callEvent(new DPUserEvent(user, action));
	}

	public void callEvent(String userName, DPUserEvent.Action action) {

		callEvent(new DPUserEvent(userName, action));
	}

	public void callEvent(DPSystemEvent.Action action) {

		callEvent(new DPSystemEvent(action));
	}
	
	/**
	 * @return the plugin
	 */
	public DeliciousPermissions getPlugin() {
	
		return plugin;
	}
	
	/**
	 * @return the server
	 */
	public Server getServer() {
	
		return server;
	}

	
}