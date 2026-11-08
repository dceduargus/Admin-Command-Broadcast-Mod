package com.eduardo.adminbroadcast;

import net.minecraft.server.permissions.Permission;
import net.minecraft.server.permissions.Permissions;

/**
 * Mod configuration.
 */
public final class AdminBroadcastConfig {

	private AdminBroadcastConfig() {
	}

	/** Minimum permission required to broadcast commands (GAMEMASTER = OP level 2). */
	public static final Permission MIN_PERMISSION = Permissions.COMMANDS_GAMEMASTER;

	/** Whether commands run from server console are broadcast. */
	public static final boolean INCLUDE_CONSOLE = false;

	/** Prefix for executed commands. */
	public static final String COMMAND_PREFIX = "§c[ADMIN] §f";

	/** Prefix for command output/feedback. */
	public static final String OUTPUT_PREFIX = "§7[Result] §f";
}
