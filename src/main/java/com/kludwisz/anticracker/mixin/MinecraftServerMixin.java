package com.kludwisz.anticracker.mixin;

import com.kludwisz.anticracker.SeedHash;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.SaveProperties;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
	@Shadow @Final protected SaveProperties saveProperties;

	@Inject(at = @At("HEAD"), method = "createWorlds")
	private void precalculateHash(CallbackInfo info) {
		long worldSeed = this.saveProperties.getGeneratorOptions().getSeed();
		SeedHash.precalculateWorldSeedHash(worldSeed);
	}
}