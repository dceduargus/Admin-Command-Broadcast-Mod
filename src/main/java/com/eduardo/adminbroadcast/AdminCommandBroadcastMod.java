package com.eduardo.adminbroadcast;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminCommandBroadcastMod implements ModInitializer {

	public static final String MOD_ID = "adminbroadcast";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("[AdminBroadcast] Mod initialized. Minimum permission: {}",
				AdminBroadcastConfig.MIN_PERMISSION);
	}
}
