package com.barribob.intothemaelstrom.entity.entities;

import com.barribob.intothemaelstrom.entity.action.ActionGeyser;
import com.barribob.intothemaelstrom.entity.action.ActionGolemSlam;
import com.barribob.intothemaelstrom.entity.ai.EntityAIRangedAttack;
import com.barribob.intothemaelstrom.entity.animation.AnimationAzureGolem;
import com.barribob.intothemaelstrom.entity.animation.AnimationGroundFistBump;
import com.barribob.intothemaelstrom.entity.model.ModelAzureGolem;
import com.barribob.intothemaelstrom.entity.render.RenderAzureGolem;
import com.barribob.intothemaelstrom.entity.util.ComboAttack;
import com.barribob.intothemaelstrom.init.ModSoundEvents;
import com.barribob.intothemaelstrom.util.ModRandom;
import com.barribob.intothemaelstrom.util.handlers.LootTableHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityCliffGolem extends EntityLeveledMob<ModelAzureGolem> implements IRangedAttackMob {
    private final ComboAttack<ModelAzureGolem> attackHandler = new ComboAttack<>();
    private final byte groundPoundByte = 4;
    private final byte geyserByte = 5;

    public EntityCliffGolem(World worldIn) {
        super(worldIn);
        this.setSize(1.4F * RenderAzureGolem.AZURE_GOLEM_SIZE, 2.7F * RenderAzureGolem.AZURE_GOLEM_SIZE);
        if (!worldIn.isRemote) {
            attackHandler.setAttack(groundPoundByte, new ActionGolemSlam<>());
            attackHandler.setAttack(this.geyserByte, new ActionGeyser<>());
        }
        this.healthScaledAttackFactor = 0.2;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected void initAnimation() {
        this.currentAnimation = new AnimationAzureGolem();
        attackHandler.setAnimation(groundPoundByte, new ActionGolemSlam<>(), AnimationAzureGolem::new);
        attackHandler.setAnimation(this.geyserByte, new ActionGeyser<>(), AnimationGroundFistBump::new);
    }

    @Override
    public float getRenderSizeModifier() {
        return RenderAzureGolem.AZURE_GOLEM_SIZE;
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(4, new EntityAIRangedAttack<>(this, 1f, 60, 15, 20.0f, 0.1f));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
    }

    private static final SoundEvent[] ambient = new SoundEvent[]{ModSoundEvents.ENTITY_CLIFF_GOLEM_AMBIENT1,ModSoundEvents.ENTITY_CLIFF_GOLEM_AMBIENT2,ModSoundEvents.ENTITY_CLIFF_GOLEM_AMBIENT3};
    private static final SoundEvent[] hurt = new SoundEvent[]{ModSoundEvents.ENTITY_CLIFF_GOLEM_HURT1,ModSoundEvents.ENTITY_CLIFF_GOLEM_HURT2,ModSoundEvents.ENTITY_CLIFF_GOLEM_HURT3};
    private static final SoundEvent[] death = new SoundEvent[]{ModSoundEvents.ENTITY_CLIFF_GOLEM_DEATH1,ModSoundEvents.ENTITY_CLIFF_GOLEM_DEATH2,ModSoundEvents.ENTITY_CLIFF_GOLEM_DEATH3};


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
        return 0.9f + ModRandom.getFloat(0.1f);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LootTableHandler.SWAMP_BOSS;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        this.attackHandler.getCurrentAttackAction().performAction(this, target);
    }

    @Override
    public void swingArm(EnumHand hand) {
    }

    @Override
    public void setSwingingArms(boolean swingingArms) {
        if (swingingArms) {
            double distance = this.getDistanceSq(this.getAttackTarget().posX, getAttackTarget().getEntityBoundingBox().minY, getAttackTarget().posZ);
            double meleeDistance = 7;
            if (distance < Math.pow(meleeDistance, 2)) {
                attackHandler.setCurrentAttack(this.groundPoundByte);
            } else {
                attackHandler.setCurrentAttack(this.geyserByte);
            }

            this.world.setEntityState(this, attackHandler.getCurrentAttack());
            this.motionY = 0.63f;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == this.groundPoundByte || id == this.geyserByte) {
            this.currentAnimation = attackHandler.getAnimation(id);
            getCurrentAnimation().startAnimation();
            this.playSound(SoundEvents.BLOCK_ANVIL_BREAK, 1.0F, 1.0F);
        } else {
            super.handleStatusUpdate(id);
        }
    }
}
