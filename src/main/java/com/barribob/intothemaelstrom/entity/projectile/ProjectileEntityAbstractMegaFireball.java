package com.barribob.intothemaelstrom.entity.projectile;

import com.barribob.intothemaelstrom.util.ModUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class ProjectileEntityAbstractMegaFireball extends ProjectileEntityGun {
    private boolean canBeHit;
    private boolean isExploded;

    public ProjectileEntityAbstractMegaFireball(World worldIn, EntityLivingBase throwerIn, double baseDamage, ItemStack stack, boolean canBeHit) {
        super(worldIn, throwerIn, baseDamage, stack);
        this.setNoGravity(true);
        this.setSize(1, 1);
        this.canBeHit = canBeHit;
    }

    public ProjectileEntityAbstractMegaFireball(World worldIn) {
        super(worldIn);
        this.setNoGravity(true);
        this.setSize(1, 1);
    }

    public ProjectileEntityAbstractMegaFireball(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        this.setNoGravity(true);
        this.setSize(1, 1);
    }

    @Override
    protected final void onHit(RayTraceResult result) {
        boolean isShootingEntity = result != null && result.entityHit != null && result.entityHit == this.shootingEntity;
        boolean isPartOfShootingEntity = result != null && result.entityHit != null && (result.entityHit instanceof MultiPartEntityPart && ((MultiPartEntityPart) result.entityHit).parent == this.shootingEntity);
        if (isShootingEntity || isPartOfShootingEntity || !canExplode()) {
            return;
        }

        super.onHit(result);
    }

    protected abstract void onImpact(@Nullable RayTraceResult result);

    @Override
    public void onUpdate() {

        Vec3d vel = ModUtils.getEntityVelocity(this);
        super.onUpdate();
        // Maintain the velocity the entity has
        ModUtils.setEntityVelocity(this, vel);
    }

    @Override
    public void setDead() {
        if (canExplode()) {
            isExploded = true;
            onImpact(null);
        }
        super.setDead();
    }

    @Override
    public final boolean attackEntityFrom(@Nonnull DamageSource source, float amount) {
        if(canBeHit && canExplode()) {
            this.setDead();
            return super.attackEntityFrom(source, amount);
        }
        return false;
    }

    private boolean canExplode() {
        return shootingEntity != null && !isExploded && !world.isRemote;
    }

    @Override
    public final boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public final boolean canBeAttackedWithItem() {
        return canBeHit;
    }

    @Override
    public int getBrightnessForRender() {
        return 200;
    }
}
