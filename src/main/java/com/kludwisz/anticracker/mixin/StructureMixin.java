package com.kludwisz.anticracker.mixin;

import com.kludwisz.anticracker.SeedHash;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(net.minecraft.structure.Structure.class)
public class StructureMixin {
    @Redirect(method = "place(Lnet/minecraft/world/WorldAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/structure/StructurePlacementData;Ljava/util/Random;I)Z", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextLong()J"))
    private long redirectNextLong(Random randomInstance) {
        return randomInstance.nextLong() ^ SeedHash.getWorldSeedHash();
    }
}
