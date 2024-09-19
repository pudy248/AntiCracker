package com.kludwisz.anticracker.mixin;

import com.kludwisz.anticracker.SeedHash;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(net.minecraft.structure.Structure.class)
public class StructureMixin {
    @Redirect(method = "place(Lnet/minecraft/world/WorldAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/structure/StructurePlacementData;Ljava/util/Random;I)Z", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextLong()J"))
    private long redirectNextLong(Random randomInstance) {
        return randomInstance.nextLong() ^ SeedHash.getWorldSeedHash();
    }
}
