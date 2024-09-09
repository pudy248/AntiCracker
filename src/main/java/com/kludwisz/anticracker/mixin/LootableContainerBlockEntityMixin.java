package com.kludwisz.anticracker.mixin;

import com.kludwisz.anticracker.SeedHash;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(LootableContainerBlockEntity.class)
public class LootableContainerBlockEntityMixin {
    @Shadow protected Identifier lootTableId;
    @Shadow protected long lootTableSeed;

    /**
     * @author Kludwisz
     * @reason This method is overwritten to scramble the seed of the loot table.
     */
    @Overwrite
    public void setLootTable(Identifier id, long seed) {
        this.lootTableId = id;
        this.lootTableSeed = seed ^ SeedHash.getWorldSeedHash();
    }
}
