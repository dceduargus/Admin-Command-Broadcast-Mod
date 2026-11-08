package com.eduardo.adminbroadcast.mixin;

import com.eduardo.adminbroadcast.AdminBroadcastConfig;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

/**
 * Intercepts command feedback and broadcasts it to server chat for admins.
 */
@Mixin(CommandSourceStack.class)
public class ServerCommandSourceMixin {

	@Inject(
			method = "sendSuccess(Ljava/util/function/Supplier;Z)V",
			at = @At("HEAD")
	)
	private void adminbroadcast$onSendSuccess(
			Supplier<Component> messageSupplier,
			boolean allowLogging,
			CallbackInfo ci
	) {
		CommandSourceStack self = (CommandSourceStack) (Object) this;

		boolean isAdminPlayer = self.getEntity() instanceof ServerPlayer
				&& self.permissions().hasPermission(AdminBroadcastConfig.MIN_PERMISSION);
		boolean isConsoleIncluded = AdminBroadcastConfig.INCLUDE_CONSOLE
				&& !(self.getEntity() instanceof ServerPlayer);

		if (!isAdminPlayer && !isConsoleIncluded) {
			return;
		}

		Component feedback = messageSupplier.get();
		Component message = Component.literal(AdminBroadcastConfig.OUTPUT_PREFIX).append(feedback);

		self.getServer().getPlayerList().broadcastSystemMessage(message, false);
	}
}
