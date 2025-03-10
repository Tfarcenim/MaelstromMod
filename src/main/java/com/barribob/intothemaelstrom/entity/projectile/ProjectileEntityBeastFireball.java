package com.barribob.intothemaelstrom.entity.projectile;

import com.barribob.intothemaelstrom.util.ModDamageSource;
import com.barribob.intothemaelstrom.util.ModRandom;
import com.barribob.intothemaelstrom.util.ModUtils;
import com.barribob.intothemaelstrom.util.handlers.ParticleManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ProjectileEntityBeastFireball extends ProjectileEntity {
    private static final int PARTICLE_AMOUNT = 15;
    private static final int IMPACT_PARTICLE_AMOUNT = 150;
    private static final int EXPOSION_AREA_FACTOR = 3;

    public ProjectileEntityBeastFireball(World worldIn, EntityLivingBase throwerIn, double baseDamage) {
        super(worldIn, throwerIn, baseDamage);
        this.setNoGravity(true);
    }

    public ProjectileEntityBeastFireball(World worldIn) {
        super(worldIn);
        this.setNoGravity(true);
    }

    public ProjectileEntityBeastFireball(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        this.setNoGravity(true);
    }

    @Override
    protected void spawnParticles() {
        float size = 0.5f;
        for (int i = 0; i < PARTICLE_AMOUNT; i++) {
            ParticleManager.spawnDarkFlames(this.world, rand, ModUtils.entityPos(this).add(ModRandom.randVec().scale(size)));
        }
    }

    @Override
    protected void spawnImpactParticles() {
        float size = (float) (EXPOSION_AREA_FACTOR * this.getEntityBoundingBox().grow(EXPOSION_AREA_FACTOR).getAverageEdgeLength() * 0.5f);
        for (int i = 0; i < IMPACT_PARTICLE_AMOUNT; i++) {
            Vec3d pos = ModUtils.entityPos(this).add(ModRandom.randVec().scale(size));
            if (rand.nextInt(2) == 0) {
                ParticleManager.spawnDarkFlames(this.world, rand, pos, ModRandom.randVec().scale(0.5f));
            } else {
                this.world.spawnParticle(EnumParticleTypes.FLAME, pos.x, pos.y, pos.z, ModRandom.getFloat(0.25f), ModRandom.getFloat(0.25f), ModRandom.getFloat(0.25f));
            }
        }
        for (int i = 0; i < 10; i++) {
            Vec3d pos = ModUtils.entityPos(this).add(ModRandom.randVec().scale(size));
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, pos.x, pos.y, pos.z, ModRandom.getFloat(0.25f), ModRandom.getFloat(0.25f), ModRandom.getFloat(0.25f));
        }
    }

    @Override
    protected void onHit(RayTraceResult result) {
        if(!world.isRemote && shootingEntity != null) {
            DamageSource source = ModDamageSource.builder()
                    .type(ModDamageSource.EXPLOSION)
                    .directEntity(this)
                    .indirectEntity(shootingEntity)
                    .element(getElement())
                    .stoppedByArmorNotShields().build();

            ModUtils.handleAreaImpact(EXPOSION_AREA_FACTOR, (e) -> {
                if (e instanceof EntityLivingBase) {
                    ((EntityLivingBase) e).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 80, 0));
                }
                return this.getDamage();
            }, this.shootingEntity, this.getPositionVector(), source, 1, 0);

            world.createExplosion(shootingEntity, posX, posY, posZ, 2.0f, ModUtils.mobGriefing(world, this.shootingEntity));
        }
        super.onHit(result);
    }
}
