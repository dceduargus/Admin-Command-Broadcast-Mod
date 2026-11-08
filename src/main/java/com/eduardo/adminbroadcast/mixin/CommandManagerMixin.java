package com.eduardo.adminbroadcast.mixin;

import com.eduardo.adminbroadcast.AdminBroadcastConfig;
import com.mojang.brigadier.ParseResults;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Intercepts command execution to broadcast admin commands to server chat.
 */
@Mixin(Commands.class)
public class CommandManagerMixin {

	@Inject(
			method = "performCommand(Lcom/mojang/brigadier/ParseResults;Ljava/lang/String;)V",
			at = @At("HEAD")
	)
	private void adminbroadcast$onExecute(
			ParseResults<CommandSourceStack> parseResults,
			String command,
			CallbackInfo ci
	) {
		CommandSourceStack source = parseResults.getContext().getSource();

		boolean isAdminPlayer = source.getEntity() instanceof ServerPlayer
				&& source.permissions().hasPermission(AdminBroadcastConfig.MIN_PERMISSION);
		boolean isConsoleIncluded = AdminBroadcastConfig.INCLUDE_CONSOLE
				&& !(source.getEntity() instanceof ServerPlayer);

		if (!isAdminPlayer && !isConsoleIncluded) {
			return;
		}

		String executorName = source.getEntity() instanceof ServerPlayer player
				? player.getName().getString()
				: "Console";

		String displayedCommand = command.startsWith("/") ? command : "/" + command;

		Component message = Component.literal(
				AdminBroadcastConfig.COMMAND_PREFIX + executorName + ": " + displayedCommand);

		MinecraftServer server = source.getServer();
		server.getPlayerList().broadcastSystemMessage(message, false);
	}
}
