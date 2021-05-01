package com.barribob.intothemaelstrom.entity.projectile;

import com.barribob.intothemaelstrom.util.ModDamageSource;
import com.barribob.intothemaelstrom.util.ModUtils;
import com.barribob.intothemaelstrom.util.handlers.ParticleManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ProjectileEntityMaelstromWisp extends ProjectileEntity {
    private static final int PARTICLE_AMOUNT = 6;
    private static final int AREA_FACTOR = 2;

    public ProjectileEntityMaelstromWisp(World worldIn, EntityLivingBase throwerIn, double baseDamage) {
        super(worldIn, throwerIn, baseDamage);
        this.setNoGravity(true);
    }

    public ProjectileEntityMaelstromWisp(World worldIn) {
        super(worldIn);
    }

    public ProjectileEntityMaelstromWisp(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    protected void spawnParticles() {
        for (int i = 0; i < PARTICLE_AMOUNT; i++) {
            ModUtils.circleCallback(AREA_FACTOR, 30, (pos) -> {
                Vec3d vel = new Vec3d(this.motionX, this.motionY, this.motionZ).normalize();

                // Conversion code taken from projectile shoot method
                float f1 = MathHelper.sqrt(vel.x * vel.x + vel.z * vel.z);
                Vec3d outer = pos.rotatePitch((float) (MathHelper.atan2(vel.y, f1))).rotateYaw((float) (MathHelper.atan2(vel.x, vel.z))).add(getPositionVector());
                Vec3d inner = pos.scale(0.85f).rotatePitch((float) (MathHelper.atan2(vel.y, f1))).rotateYaw((float) (MathHelper.atan2(vel.x, vel.z))).add(getPositionVector());
                ParticleManager.spawnMaelstromSmoke(world, rand, outer, true);
                ParticleManager.spawnMaelstromPotionParticle(world, rand, inner, false);
            });
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        DamageSource source = ModDamageSource.builder()
                .type(ModDamageSource.MAGIC)
                .directEntity(this)
                .indirectEntity(shootingEntity)
                .element(getElement())
                .stoppedByArmorNotShields().build();

        ModUtils.handleAreaImpact(AREA_FACTOR, (e) -> this.getDamage(), this.shootingEntity, getPositionVector(), source);
    }

    @Override
    protected void onHit(RayTraceResult result) {
        // Only destroy if the collision is a block
        if (result.entityHit != null) {
            return;
        }

        super.onHit(result);
    }
}
