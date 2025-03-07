package com.barribob.intothemaelstrom.entity.tileentity;

import com.barribob.intothemaelstrom.init.ModBlocks;
import net.minecraft.util.ITickable;

/**
 * The tile entity for spawning maelstrom mobs
 */
public class TileEntityMalestromSpawner extends TileEntityMobSpawner implements ITickable {
    @Override
    protected MobSpawnerLogic getSpawnerLogic() {
        return new MaelstromMobSpawnerLogic(() -> world, () -> pos, ModBlocks.AZURE_MAELSTROM_CORE);
    }
}