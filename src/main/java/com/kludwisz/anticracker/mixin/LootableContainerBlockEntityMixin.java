package com.kludwisz.anticracker.mixin;

import com.kludwisz.anticracker.SeedHash;

import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.util.Identifier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(LootableContainerBlockEntity.class)
public class LootableContainerBlockEntityMixin {
    @Shadow protected long lootTableSeed;

    @Inject(method = "setLootTable(Lnet/minecraft/util/Identifier;J)V", at = @At(value = "TAIL"))
    private void setScrambledLootSeed(Identifier id, long seed, CallbackInfo ci) {
        // System.out.println("Previous loot seed: " + this.lootTableSeed + ", new seed: " + (seed ^ SeedHash.getWorldSeedHash()));
        this.lootTableSeed = seed ^ SeedHash.getWorldSeedHash();
    }
}
