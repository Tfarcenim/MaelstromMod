package com.barribob.intothemaelstrom.entity.projectile;

import com.barribob.intothemaelstrom.entity.entities.EntityShade;
import com.barribob.intothemaelstrom.entity.tileentity.MobSpawnerLogic.MobSpawnData;
import com.barribob.intothemaelstrom.init.ModBlocks;
import com.barribob.intothemaelstrom.init.ModEntities;
import com.barribob.intothemaelstrom.util.Element;
import com.barribob.intothemaelstrom.util.ModRandom;
import com.barribob.intothemaelstrom.util.ModUtils;
import com.barribob.intothemaelstrom.util.handlers.LevelHandler;
import com.barribob.intothemaelstrom.util.handlers.ParticleManager;
import com.barribob.intothemaelstrom.world.gen.WorldGenMaelstrom;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ProjectileEntityMaelstromMeteor extends ProjectileEntity {
    public ProjectileEntityMaelstromMeteor(World worldIn, EntityLivingBase throwerIn, double damage) {
        super(worldIn, throwerIn, damage);
        this.setNoGravity(true);
    }

    public ProjectileEntityMaelstromMeteor(World worldIn) {
        super(worldIn);
        this.setNoGravity(true);
    }

    public ProjectileEntityMaelstromMeteor(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        this.setNoGravity(true);
    }

    @Override
    public void onUpdate() {
        this.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 0.5f, ModRandom.getFloat(0.2f) + 1.0f);
        if (this.ticksExisted > 400) {
            this.setDead();
        }
        super.onUpdate();
    }

    @Override
    protected void spawnParticles() {
        float size = 0.25f;
        ModUtils.performNTimes(10, (i) -> {
            ParticleManager.spawnMaelstromLargeSmoke(world, rand, getPositionVector().add(ModRandom.randVec().scale(size)));
        });
    }

    private static final WorldGenMaelstrom maelStromGenerator = new WorldGenMaelstrom(ModBlocks.DECAYING_MAELSTROM, ModBlocks.MAELSTROM_CORE_BLOCK, (tileEntity) -> tileEntity.getSpawnerBaseLogic().setData(
            new MobSpawnData(ModEntities.getID(EntityShade.class), Element.NONE),
            2,
            LevelHandler.INVASION,
            16));

    @Override
    protected void onHit(RayTraceResult result) {
        // Go through entities
        if (result.entityHit != null) {
            return;
        }

        if (!world.isRemote) {
                    maelStromGenerator.generate(world, rand, this.getPosition());
        }
        super.onHit(result);
    }
}
