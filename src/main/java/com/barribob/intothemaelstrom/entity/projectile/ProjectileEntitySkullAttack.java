package com.barribob.intothemaelstrom.entity.projectile;

import com.barribob.intothemaelstrom.entity.entities.EntityMaelstromMob;
import com.barribob.intothemaelstrom.util.ModDamageSource;
import com.barribob.intothemaelstrom.util.ModRandom;
import com.barribob.intothemaelstrom.util.handlers.ParticleManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

/**
 * An attack very similar to the will-o-the-wisp attack
 */
public class ProjectileEntitySkullAttack extends ProjectileEntity {
    private static final int PARTICLE_AMOUNT = 6;
    private static final int AREA_FACTOR = 2;

    public ProjectileEntitySkullAttack(World worldIn, EntityLivingBase throwerIn, double baseDamage) {
        super(worldIn, throwerIn, baseDamage);
        this.setNoGravity(true);
    }

    public ProjectileEntitySkullAttack(World worldIn) {
        super(worldIn);
    }

    public ProjectileEntitySkullAttack(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    /**
     * Called every update to spawn particles
     *
     * @param world
     */
    @Override
    protected void spawnParticles() {
        float f1 = 1.25f;
        float f2 = 0.15f;
        for (int i = 0; i < PARTICLE_AMOUNT; i++) {
            ParticleManager.spawnMaelstromSmoke(world, rand,
                    new Vec3d(this.posX + ModRandom.getFloat(f1), this.posY + ModRandom.getFloat(f1), this.posZ + ModRandom.getFloat(f1)), true);
            world.spawnParticle(EnumParticleTypes.FLAME, this.posX + ModRandom.getFloat(f2), this.posY + ModRandom.getFloat(f2), this.posZ + ModRandom.getFloat(f2), 0, 0, 0);
        }
    }

    @Override
    protected void onHit(RayTraceResult result) {
        /*
         * Find all entities in a certain area and deal damage to them
         */
        List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(AREA_FACTOR));
        for (Entity entity : list) {
            if (entity instanceof EntityLivingBase && this.shootingEntity != null && entity != this.shootingEntity && EntityMaelstromMob.CAN_TARGET.apply(entity)) {
                int burnTime = 5;
                entity.setFire(burnTime);

                entity.attackEntityFrom(ModDamageSource.causeElementalThrownDamage(this, shootingEntity, getElement()), (float) this.getDamage());
                entity.addVelocity(0, 0.1D, 0);
            }
        }

        super.onHit(result);
    }
}
