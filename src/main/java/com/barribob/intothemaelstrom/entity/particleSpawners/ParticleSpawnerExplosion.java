package com.barribob.intothemaelstrom.entity.particleSpawners;

import com.barribob.intothemaelstrom.entity.util.EntityParticleSpawner;
import com.barribob.intothemaelstrom.util.ModRandom;
import com.barribob.intothemaelstrom.util.handlers.ParticleManager;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ParticleSpawnerExplosion extends EntityParticleSpawner {
    public ParticleSpawnerExplosion(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void spawnParticles() {
        for (int i = 0; i < 4; i++) {
            Vec3d pos2 = getPositionVector().add(ModRandom.randVec().scale(1));
            Vec3d pos3 = getPositionVector().add(ModRandom.randVec().scale(1));
            ParticleManager.spawnMaelstromExplosion(world, rand, pos2);
        }
    }
}
