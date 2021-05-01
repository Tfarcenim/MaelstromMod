package com.barribob.intothemaelstrom.entity.entities;

import com.barribob.intothemaelstrom.entity.ai.AIJumpAtTarget;
import com.barribob.intothemaelstrom.entity.ai.EntityAITimedAttack;
import com.barribob.intothemaelstrom.entity.util.IAttack;
import com.barribob.intothemaelstrom.init.ModBBAnimations;
import com.barribob.intothemaelstrom.util.Element;
import com.barribob.intothemaelstrom.util.ModDamageSource;
import com.barribob.intothemaelstrom.util.ModRandom;
import com.barribob.intothemaelstrom.util.ModUtils;
import com.barribob.intothemaelstrom.util.handlers.ParticleManager;
import com.barribob.intothemaelstrom.init.ModSoundEvents;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Represent the attibutes and logic of the shade monster
 */
//aka scout
public class EntityShade extends EntityMaelstromMob implements IAttack {

    public EntityShade(World worldIn) {
        super(worldIn);
        this.setSize(0.9f, 1.8f);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(4, new EntityAITimedAttack<>(this, 1.0f, 5, 3f, 0.5f));
        this.tasks.addTask(0, new AIJumpAtTarget(this, 0.4f, 0.5f));
    }

    private static final SoundEvent[] ambient = new SoundEvent[]{ModSoundEvents.ENTITY_SHADE_AMBIENT1,ModSoundEvents.ENTITY_SHADE_AMBIENT2,ModSoundEvents.ENTITY_SHADE_AMBIENT3};
    private static final SoundEvent[] hurt = new SoundEvent[]{ModSoundEvents.ENTITY_SHADE_HURT1,ModSoundEvents.ENTITY_SHADE_HURT2,ModSoundEvents.ENTITY_SHADE_HURT3};
    private static final SoundEvent[] death = new SoundEvent[]{ModSoundEvents.ENTITY_SHADE_DEATH1,ModSoundEvents.ENTITY_SHADE_DEATH2,ModSoundEvents.ENTITY_SHADE_DEATH3};


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
    public void onEntityUpdate() {
        super.onEntityUpdate();

        if (rand.nextInt(20) == 0) {
            world.setEntityState(this, ModUtils.PARTICLE_BYTE);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == ModUtils.PARTICLE_BYTE) {
            if (this.getElement().equals(Element.NONE)) {
                ParticleManager.spawnMaelstromPotionParticle(world, rand, this.getPositionVector().add(ModRandom.randVec()).add(ModUtils.yVec(1)), false);
            }

            ParticleManager.spawnEffect(world, this.getPositionVector().add(ModRandom.randVec()).add(ModUtils.yVec(1)), getElement().particleColor);
        } else {
            super.handleStatusUpdate(id);
        }
    }

    @Override
    public int startAttack(EntityLivingBase target, float distanceFactor, boolean strafingBackwards) {
        ModBBAnimations.animation(this, "scout.attack", false);
        ModUtils.leapTowards(this, this.getAttackTarget().getPositionVector(), 0.4f, 0.3f);

        addEvent(() -> {
            Vec3d pos = this.getPositionVector().add(ModUtils.yVec(1)).add(this.getLookVec());
            this.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, 1.0F, 0.8F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
            ModUtils.handleAreaImpact(0.6f, (e) -> this.getAttack(), this, pos, ModDamageSource.causeElementalMeleeDamage(this, getElement()), 0.20f, 0, false);
        }, 10);

        return 20;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
    }
}