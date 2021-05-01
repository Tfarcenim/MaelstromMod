package com.barribob.intothemaelstrom.entity;

import com.barribob.intothemaelstrom.entity.util.EntityPortalSpawn;
import com.barribob.intothemaelstrom.init.ModBlocks;
import com.barribob.intothemaelstrom.util.ModColors;
import com.barribob.intothemaelstrom.util.handlers.ParticleManager;
import net.minecraft.block.Block;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityCrimsonPortalSpawn extends EntityPortalSpawn {
    public EntityCrimsonPortalSpawn(World worldIn) {
        super(worldIn);
    }

    public EntityCrimsonPortalSpawn(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    protected void spawnParticles() {
        int offset = 0;
        int sectors = 90;
        int degreesPerSector = 360 / sectors;
        double size = 3;
        for (int i = 0; i < sectors; i++) {
            double x = this.posX + Math.cos(i * degreesPerSector) * Math.sin(this.ticksExisted) * size + offset;
            double y = this.posY + 3.5 + Math.sin(i * degreesPerSector) * Math.cos(this.ticksExisted) * size + offset;
            double z = this.posZ + Math.cos(i * degreesPerSector) * Math.sin(this.ticksExisted) * size + offset;
            ParticleManager.spawnEffect(world, new Vec3d(x, y, this.posZ), ModColors.RED);
            ParticleManager.spawnEffect(world, new Vec3d(this.posX, y, z), ModColors.RED);
        }
    }

    @Override
    protected Block getRimBlock() {
        return ModBlocks.FURNACE_BRICKS;
    }

    @Override
    protected Block getPortalBlock() {
        return ModBlocks.CRIMSON_PORTAL;
    }
}
