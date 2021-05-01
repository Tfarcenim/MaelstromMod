package com.barribob.intothemaelstrom.entity.projectile;

import com.barribob.intothemaelstrom.util.ModColors;
import com.barribob.intothemaelstrom.util.ModDamageSource;
import com.barribob.intothemaelstrom.util.ModRandom;
import com.barribob.intothemaelstrom.util.ModUtils;
import com.barribob.intothemaelstrom.util.handlers.ParticleManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ProjectileEntityBone extends ProjectileEntity {
    private static final int IMPACT_PARTICLE_AMOUNT = 10;
    private static final int EXPOSION_AREA_FACTOR = 1;

    public ProjectileEntityBone(World worldIn, EntityLivingBase throwerIn, double damage) {
        super(worldIn, throwerIn, damage);
    }

    public ProjectileEntityBone(World worldIn) {
        super(worldIn);
    }

    public ProjectileEntityBone(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    protected void spawnImpactParticles() {
        for (int i = 0; i < IMPACT_PARTICLE_AMOUNT; i++) {
            Vec3d vec1 = ModRandom.randVec()
                    .scale(EXPOSION_AREA_FACTOR * 2)
                    .add(getPositionVector())
                    .add(ModUtils.yVec(0.8f));
            ParticleManager.spawnEffect(world, vec1, ModColors.WHITE);
        }
    }

    @Override
    protected void onHit(RayTraceResult result) {
        ModUtils.handleAreaImpact(EXPOSION_AREA_FACTOR, (e) -> this.getDamage(), this.shootingEntity, this.getPositionVector(),
                ModDamageSource.causeElementalExplosionDamage(this.shootingEntity, getElement()));
        this.playSound(SoundEvents.ENTITY_SKELETON_HURT, 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
        super.onHit(result);
    }
}
