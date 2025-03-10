package com.barribob.intothemaelstrom.entity.projectile;

import com.barribob.intothemaelstrom.util.Element;
import com.barribob.intothemaelstrom.util.ModDamageSource;
import com.barribob.intothemaelstrom.util.ModRandom;
import com.barribob.intothemaelstrom.util.ModUtils;
import com.barribob.intothemaelstrom.util.handlers.ParticleManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * The simple attacks that the beast outputs during its ranged mode
 */
public class ProjectileEntityBeastAttack extends ProjectileEntity {
    private static final int PARTICLE_AMOUNT = 3;
    private static final int IMPACT_PARTICLE_AMOUNT = 20;

    public ProjectileEntityBeastAttack(World worldIn, EntityLivingBase throwerIn, double damage) {
        super(worldIn, throwerIn, damage);
    }

    public ProjectileEntityBeastAttack(World worldIn) {
        super(worldIn);
    }

    public ProjectileEntityBeastAttack(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    /**
     * Called every update to spawn particles
     *
     * @param world
     */
    @Override
    protected void spawnParticles() {
        for (int i = 0; i < PARTICLE_AMOUNT; i++) {
            ParticleManager.spawnSmoke2(world, this.getPositionVector().add(ModRandom.randVec().scale(0.5f)), this.getElement().particleColor, ModUtils.yVec(0.1f));
        }
    }

    @Override
    protected void spawnImpactParticles() {
        for (int i = 0; i < IMPACT_PARTICLE_AMOUNT; i++) {
            ParticleManager.spawnSmoke2(world, this.getPositionVector().add(ModRandom.randVec()), this.getElement().particleColor, ModUtils.yVec(0.1f));
        }
    }

    @Override
    protected void onHit(RayTraceResult result) {
        ModUtils.handleBulletImpact(result.entityHit, this, this.getDamage(), ModDamageSource.causeElementalThrownDamage(this, shootingEntity, getElement()), 1, (proj, entity) -> {
            if (this.getElement().equals(Element.CRIMSON) && entity instanceof EntityLivingBase) {
                ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WITHER, 100));
            }
        }, (proj, entity) -> {
        });
        super.onHit(result);
    }
}
