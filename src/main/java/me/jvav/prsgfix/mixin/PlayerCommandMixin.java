package me.jvav.prsgfix.mixin;

import carpet.commands.PlayerCommand;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = PlayerCommand.class, remap = false)
public class PlayerCommandMixin {
    @Inject(method = "cantSpawn", at = @At("TAIL"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private static void onCantSpawn(CommandContext<CommandSourceStack> context, CallbackInfoReturnable<Boolean> cir, String playerName, MinecraftServer server, PlayerList manager, Player player, GameProfile profile) {
        if (manager.getPlayer(profile.getId()) != null) {
            cir.setReturnValue(true);
        }
    }
}
