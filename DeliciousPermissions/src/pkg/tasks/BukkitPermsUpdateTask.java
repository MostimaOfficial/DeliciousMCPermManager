package pkg.tasks;

import pkg.main.DeliciousPermissions;

/*
 * 
 * Created by ElgarL
 */

public class BukkitPermsUpdateTask implements Runnable {

	public BukkitPermsUpdateTask() {

		super();
	}

	@Override
	public void run() {

		// Signal loaded and update BukkitPermissions.
		DeliciousPermissions.setLoaded(true);
		DeliciousPermissions.BukkitPermissions.collectPermissions();
		DeliciousPermissions.BukkitPermissions.updateAllPlayers();

		DeliciousPermissions.logger.info("Bukkit Permissions Updated!");

	}

}