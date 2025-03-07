package com.barribob.intothemaelstrom.entity.particleSpawners;

import com.barribob.intothemaelstrom.entity.util.EntityParticleSpawner;
import com.barribob.intothemaelstrom.util.ModColors;
import com.barribob.intothemaelstrom.util.ModUtils;
import com.barribob.intothemaelstrom.util.handlers.ParticleManager;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ParticleSpawnerRainbow extends EntityParticleSpawner {
    public ParticleSpawnerRainbow(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void spawnParticles() {
        ParticleManager.spawnFluff(world, getPositionVector().add(ModUtils.yVec(2.0f)), ModColors.RED, Vec3d.ZERO);
        ParticleManager.spawnFluff(world, getPositionVector().add(ModUtils.yVec(1.7f)), ModColors.ORANGE, Vec3d.ZERO);
        ParticleManager.spawnFluff(world, getPositionVector().add(ModUtils.yVec(1.4f)), ModColors.YELLOW, Vec3d.ZERO);
        ParticleManager.spawnFluff(world, getPositionVector().add(ModUtils.yVec(1.1f)), ModColors.GREEN, Vec3d.ZERO);
        ParticleManager.spawnFluff(world, getPositionVector().add(ModUtils.yVec(0.8f)), ModColors.BLUE, Vec3d.ZERO);
        ParticleManager.spawnFluff(world, getPositionVector().add(ModUtils.yVec(0.5f)), ModColors.PURPLE, Vec3d.ZERO);
    }
}
