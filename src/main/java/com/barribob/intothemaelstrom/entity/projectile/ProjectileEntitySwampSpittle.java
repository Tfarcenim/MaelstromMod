package com.barribob.intothemaelstrom.entity.projectile;

import com.barribob.intothemaelstrom.util.ModColors;
import com.barribob.intothemaelstrom.util.ModDamageSource;
import com.barribob.intothemaelstrom.util.ModUtils;
import com.barribob.intothemaelstrom.util.handlers.ParticleManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ProjectileEntitySwampSpittle extends ProjectileEntity {
    public ProjectileEntitySwampSpittle(World worldIn, EntityLivingBase throwerIn, double damage) {
        super(worldIn, throwerIn, damage);
    }

    public ProjectileEntitySwampSpittle(World worldIn) {
        super(worldIn);
    }

    public ProjectileEntitySwampSpittle(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    /**
     * Called every update to spawn particles
     *
     * @param world
     */
    @Override
    protected void spawnParticles() {
        ParticleManager.spawnEffect(world, this.getPositionVector(), ModColors.CLIFF_STONE);
    }

    @Override
    protected void onHit(RayTraceResult result) {
        ModUtils.handleBulletImpact(result.entityHit, this, this.getDamage(), ModDamageSource.causeElementalThrownDamage(this, shootingEntity, getElement()));
        super.onHit(result);
    }
}
