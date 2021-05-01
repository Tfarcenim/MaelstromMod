package com.barribob.intothemaelstrom.entity.projectile;

import com.barribob.intothemaelstrom.entity.entities.EntityMaelstromMob;
import com.barribob.intothemaelstrom.util.ModDamageSource;
import com.barribob.intothemaelstrom.util.ModRandom;
import com.barribob.intothemaelstrom.util.handlers.ParticleManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ProjectileEntityMaelstromMissile extends ProjectileEntity {
    public ProjectileEntityMaelstromMissile(World worldIn, EntityLivingBase throwerIn, double damage) {
        super(worldIn, throwerIn, damage);
        this.setNoGravity(true);
    }

    public ProjectileEntityMaelstromMissile(World worldIn) {
        super(worldIn);
    }

    public ProjectileEntityMaelstromMissile(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    protected void spawnParticles() {
        ParticleManager.spawnDarkFlames(world, this.rand, this.getPositionVector());
    }

    @Override
    protected void onHit(RayTraceResult result) {
        if (result.entityHit != null && !EntityMaelstromMob.isMaelstromMob(result.entityHit) && this.shootingEntity != null) {
            DamageSource source = ModDamageSource.builder()
                    .type(ModDamageSource.MAGIC)
                    .indirectEntity(this)
                    .directEntity(shootingEntity)
                    .element(getElement())
                    .stoppedByArmorNotShields().build();

            result.entityHit.attackEntityFrom(source, (float) this.getDamage());
        }
        this.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 1.0f + ModRandom.getFloat(0.2f), 1.0f + ModRandom.getFloat(0.2f));
        super.onHit(result);
    }
}
