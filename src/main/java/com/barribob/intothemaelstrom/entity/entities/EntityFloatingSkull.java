package com.barribob.intothemaelstrom.entity.entities;

import com.barribob.intothemaelstrom.entity.ai.EntityAIRangedAttack;
import com.barribob.intothemaelstrom.entity.animation.AnimationFloatingSkull;
import com.barribob.intothemaelstrom.entity.projectile.ProjectileEntitySkullAttack;
import com.barribob.intothemaelstrom.init.ModSoundEvents;
import com.barribob.intothemaelstrom.util.ModRandom;
import com.barribob.intothemaelstrom.util.handlers.ParticleManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFloatingSkull extends EntityMaelstromMob {
    public EntityFloatingSkull(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(4, new EntityAIRangedAttack<EntityMaelstromMob>(this, 1.0f, 60, 5, 7.5f, 0.5f));
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (world.isRemote) {
            ParticleManager.spawnDarkFlames(world, rand,
                    new Vec3d(this.posX + ModRandom.getFloat(0.5f), this.posY + 0.1f + ModRandom.getFloat(0.1f), this.posZ + ModRandom.getFloat(0.5f)));
        }
    }

    private static final SoundEvent[] ambient = new SoundEvent[]{ModSoundEvents.ENTITY_FLOATING_SKULL_AMBIENT1,ModSoundEvents.ENTITY_FLOATING_SKULL_AMBIENT2,ModSoundEvents.ENTITY_FLOATING_SKULL_AMBIENT3};
    private static final SoundEvent[] hurt = new SoundEvent[]{ModSoundEvents.ENTITY_FLOATING_SKULL_HURT1,ModSoundEvents.ENTITY_FLOATING_SKULL_HURT2,ModSoundEvents.ENTITY_FLOATING_SKULL_HURT3};
    private static final SoundEvent[] death = new SoundEvent[]{ModSoundEvents.ENTITY_FLOATING_SKULL_DEATH1,ModSoundEvents.ENTITY_FLOATING_SKULL_DEATH2,ModSoundEvents.ENTITY_FLOATING_SKULL_DEATH3};


    @Override
    protected SoundEvent getAmbientSound() {
        return ambient[rand.nextInt(3)];
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return hurt[rand.nextInt(3)];
    }

    @Override
    protected SoundEvent getDeathSound() {
        return death[rand.nextInt(3)];
    }

    @Override
    protected float getSoundPitch() {
        return 0.8f + ModRandom.getFloat(0.1f);
    }

    @Override
    public void setSwingingArms(boolean swingingArms) {
        if (swingingArms) {
            this.world.setEntityState(this, (byte) 4);
        }
    }

    /**
     * Handler for {@link World#setEntityState}
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 4) {
            this.currentAnimation = new AnimationFloatingSkull();
            getCurrentAnimation().startAnimation();
        } else {
            super.handleStatusUpdate(id);
        }
    }

    /**
     * Shoots a projectile in a similar fashion to the snow golem (see
     * EntitySnowman)
     */
    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        if (!world.isRemote) {
            world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_BLAZE_AMBIENT, SoundCategory.NEUTRAL, 0.5F,
                    0.4F / (world.rand.nextFloat() * 0.4F + 0.8F));

            float inaccuracy = 0.0f;
            float speed = 0.5f;

            ProjectileEntitySkullAttack projectile = new ProjectileEntitySkullAttack(world, this, this.getAttack());
            projectile.shoot(this, this.rotationPitch, this.rotationYaw, 0.0F, speed, inaccuracy);
            projectile.setTravelRange(9f);

            world.spawnEntity(projectile);
        }
    }
}