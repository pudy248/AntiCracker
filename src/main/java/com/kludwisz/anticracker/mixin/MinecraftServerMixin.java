package com.kludwisz.anticracker.mixin;

import com.kludwisz.anticracker.SeedHash;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
	@Inject(at = @At("HEAD"), method = "loadWorld")
	private void init(CallbackInfo info) {
		// Pre-calculate the hash of the world seed on each new world load
		long worldSeed = ((MinecraftServer) (Object) this).getOverworld().getSeed();
		SeedHash.precalculateWorldSeedHash(worldSeed);
	}
}