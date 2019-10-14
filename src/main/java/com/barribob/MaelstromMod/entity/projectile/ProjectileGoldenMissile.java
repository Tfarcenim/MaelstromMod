package com.barribob.MaelstromMod.entity.projectile;

import com.barribob.MaelstromMod.util.ModColors;
import com.barribob.MaelstromMod.util.ModDamageSource;
import com.barribob.MaelstromMod.util.ModUtils;
import com.barribob.MaelstromMod.util.handlers.ParticleManager;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ProjectileGoldenMissile extends Projectile
{
    public ProjectileGoldenMissile(World worldIn, EntityLivingBase throwerIn, float damage)
    {
	super(worldIn, throwerIn, damage);
    }

    public ProjectileGoldenMissile(World worldIn)
    {
	super(worldIn);
    }

    public ProjectileGoldenMissile(World worldIn, double x, double y, double z)
    {
	super(worldIn, x, y, z);
    }

    /**
     * Called every update to spawn particles
     * 
     * @param world
     */
    @Override
    protected void spawnParticles()
    {
	ParticleManager.spawnEffect(world, this.getPositionVector(), ModColors.YELLOW);
    }

    @Override
    protected void onHit(RayTraceResult result)
    {
	ModUtils.handleBulletImpact(result.entityHit, this, this.getDamage(), ModDamageSource.causeMaelstromMeleeDamage(this.shootingEntity));
	super.onHit(result);
    }
}
