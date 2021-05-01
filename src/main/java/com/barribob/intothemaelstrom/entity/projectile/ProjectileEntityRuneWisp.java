package com.barribob.intothemaelstrom.entity.projectile;

import com.barribob.intothemaelstrom.util.ModDamageSource;
import com.barribob.intothemaelstrom.util.ModUtils;
import com.barribob.intothemaelstrom.util.handlers.ParticleManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ProjectileEntityRuneWisp extends ProjectileEntityGun {
    private static final int PARTICLE_AMOUNT = 6;
    private static final int RADIUS = 2;

    public ProjectileEntityRuneWisp(World worldIn, EntityLivingBase throwerIn, float baseDamage, ItemStack stack) {
        super(worldIn, throwerIn, baseDamage, stack);
        this.setNoGravity(true);
    }

    public ProjectileEntityRuneWisp(World worldIn) {
        super(worldIn);
    }

    public ProjectileEntityRuneWisp(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    protected void spawnParticles() {
        for (int i = 0; i < PARTICLE_AMOUNT; i++) {
            ModUtils.circleCallback(RADIUS, 30, (pos) -> {
                Vec3d vel = new Vec3d(this.motionX, this.motionY, this.motionZ).normalize();

                // Conversion code taken from projectile shoot method
                float f1 = MathHelper.sqrt(vel.x * vel.x + vel.z * vel.z);
                Vec3d outer = pos.rotatePitch((float) (MathHelper.atan2(vel.y, f1))).rotateYaw((float) (MathHelper.atan2(vel.x, vel.z))).add(getPositionVector());
                Vec3d inner = pos.scale(0.85f).rotatePitch((float) (MathHelper.atan2(vel.y, f1))).rotateYaw((float) (MathHelper.atan2(vel.x, vel.z))).add(getPositionVector());
                ParticleManager.spawnWisp(world, outer, this.getElement().particleColor, Vec3d.ZERO);
                ParticleManager.spawnEffect(world, inner, getElement().particleColor);
            });
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.shootingEntity != null && this.ticksExisted % 4 == 1) {
            float knockbackFactor = 1.0f + this.getKnockback() * 0.3f;
            int fireFactor = this.isBurning() ? 5 : 0;
            ModUtils.handleAreaImpact(
                    RADIUS,
                    (e) -> this.getDamage(),
                    this.shootingEntity,
                    this.getPositionVector(),
                    ModDamageSource.causeElementalThrownDamage(this, this.shootingEntity, this.getElement()),
                    knockbackFactor,
                    fireFactor);
        }
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
