package com.barribob.intothemaelstrom.entity.projectile;

import com.barribob.intothemaelstrom.util.ModRandom;
import com.barribob.intothemaelstrom.util.ModUtils;
import com.barribob.intothemaelstrom.util.handlers.ParticleManager;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ProjectileEntityMaelstromQuake extends ProjectileEntityQuake {
    public static final int PARTICLE_AMOUNT = 4;

    public ProjectileEntityMaelstromQuake(World worldIn, EntityLivingBase throwerIn, float baseDamage) {
        super(worldIn, throwerIn, baseDamage, null);
        this.setSize(0.25f, 1);
    }

    public ProjectileEntityMaelstromQuake(World worldIn) {
        super(worldIn);
    }

    public ProjectileEntityMaelstromQuake(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    protected void spawnParticles() {
        IBlockState block = world.getBlockState(new BlockPos(this.posX, this.posY, this.posZ));
        if (block.isFullCube()) {
            Vec3d vel = new Vec3d(0, -0.1, 0);
            for (int i = 0; i < this.PARTICLE_AMOUNT; i++) {
                Vec3d pos = ModUtils.entityPos(this).add(new Vec3d(ModRandom.getFloat(AREA_FACTOR), 0.75 + ModRandom.getFloat(0.25f), ModRandom.getFloat(AREA_FACTOR)));
                ParticleManager.spawnDarkFlames(world, rand, pos, vel);
            }
        }
    }

    @Override
    protected void playQuakeSound() {
        if (rand.nextInt(10) == 0) {
            super.playQuakeSound();
        }
    }
}
