package com.barribob.intothemaelstrom.entity.entities;

import com.barribob.intothemaelstrom.entity.ai.AIFlyingRangedAttack;
import com.barribob.intothemaelstrom.entity.ai.AILookAround;
import com.barribob.intothemaelstrom.entity.ai.AIRandomFly;
import com.barribob.intothemaelstrom.entity.ai.FlyingMoveHelper;
import com.barribob.intothemaelstrom.entity.animation.AnimationClip;
import com.barribob.intothemaelstrom.entity.animation.StreamAnimation;
import com.barribob.intothemaelstrom.entity.model.ModelCliffFly;
import com.barribob.intothemaelstrom.entity.projectile.ProjectileEntitySwampSpittle;
import com.barribob.intothemaelstrom.init.ModItems;
import com.barribob.intothemaelstrom.util.ModUtils;
import com.barribob.intothemaelstrom.util.handlers.LevelHandler;
import com.barribob.intothemaelstrom.init.ModSoundEvents;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

//aka swamp fly
public class EntityCliffFly extends EntityLeveledFlyingMob<ModelCliffFly> {
    public EntityCliffFly(World worldIn) {
        super(worldIn);
        this.moveHelper = new FlyingMoveHelper(this);
        this.setSize(1.0f, 1.8f);
        this.setLevel(LevelHandler.CLIFF_OVERWORLD);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(5, new AIRandomFly(this));
        this.tasks.addTask(7, new AILookAround(this));
        this.tasks.addTask(7, new AIFlyingRangedAttack<>(this, 40, 20, 30, 1.0f));
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.5);
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        for (int i = 0; i < 5; i++) {
            ModUtils.throwProjectile(this, target, new ProjectileEntitySwampSpittle(world, this, this.getAttack()));
            this.playSound(ModSoundEvents.ENTITY_CLIFF_FLY_FIRE, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        }
    }

    @Override
    public void setSwingingArms(boolean swingingArms) {

    }

    private static final SoundEvent[] ambient = new SoundEvent[]{ModSoundEvents.ENTITY_CLIFF_FLY_AMBIENT1,ModSoundEvents.ENTITY_CLIFF_FLY_AMBIENT2,ModSoundEvents.ENTITY_CLIFF_FLY_AMBIENT3};
    private static final SoundEvent[] hurt = new SoundEvent[]{ModSoundEvents.ENTITY_CLIFF_FLY_HURT1,ModSoundEvents.ENTITY_CLIFF_FLY_HURT2,ModSoundEvents.ENTITY_CLIFF_FLY_HURT3};
    private static final SoundEvent[] death = new SoundEvent[]{ModSoundEvents.ENTITY_CLIFF_FLY_DEATH1,ModSoundEvents.ENTITY_CLIFF_FLY_DEATH2,ModSoundEvents.ENTITY_CLIFF_FLY_DEATH3};


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
    protected Item getDropItem() {
        return ModItems.FLY_WINGS;
    }

    @Override
    protected float getSoundVolume() {
        return 0.3f;
    }

    @Override
    protected float getSoundPitch() {
        return super.getSoundPitch() * 1.5f;
    }

    @Override
    protected void initAnimation() {
        List<List<AnimationClip<ModelCliffFly>>> animationWings = new ArrayList<>();
        List<AnimationClip<ModelCliffFly>> wings = new ArrayList<>();
        BiConsumer<ModelCliffFly, Float> wingsY = (model, f) -> {
            model.leftFrontWing.rotateAngleY = -f;
            model.leftFrontWing1.rotateAngleY = -f;
            model.rightFrontWing.rotateAngleY = f;
            model.rightFrontWing2.rotateAngleY = f;

            model.rightBackWing.rotateAngleY = -f;
            model.rightBackWing2.rotateAngleY = -f;
            model.leftBackWing.rotateAngleY = f;
            model.leftBackWing2.rotateAngleY = f;
        };

        wings.add(new AnimationClip<>(2, 0, 30, wingsY));
        wings.add(new AnimationClip<>(4, 30, -30, wingsY));
        wings.add(new AnimationClip<>(2, -30, 0, wingsY));

        animationWings.add(wings);

        currentAnimation = new StreamAnimation<>(animationWings).loop(true);
        this.currentAnimation.startAnimation();
    }
}
