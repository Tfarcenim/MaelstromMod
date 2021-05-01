package com.barribob.intothemaelstrom.entity.entities;

import com.barribob.intothemaelstrom.IntoTheMaelstrom;
import com.barribob.intothemaelstrom.entity.ai.AIFuryDive;
import com.barribob.intothemaelstrom.entity.ai.AIPassiveCircle;
import com.barribob.intothemaelstrom.entity.ai.AIRandomFly;
import com.barribob.intothemaelstrom.entity.ai.FlyingMoveHelper;
import com.barribob.intothemaelstrom.entity.util.IAcceleration;
import com.barribob.intothemaelstrom.init.ModBBAnimations;
import com.barribob.intothemaelstrom.packets.MessageModParticles;
import com.barribob.intothemaelstrom.particle.EnumModParticles;
import com.barribob.intothemaelstrom.util.ModColors;
import com.barribob.intothemaelstrom.util.ModDamageSource;
import com.barribob.intothemaelstrom.util.ModUtils;
import com.barribob.intothemaelstrom.init.ModSoundEvents;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityMaelstromFury extends EntityMaelstromMob implements IAcceleration {
    Vec3d acceleration = Vec3d.ZERO;
    public EntityMaelstromFury(World worldIn) {
        super(worldIn);
        this.moveHelper = new FlyingMoveHelper(this);
        this.navigator = new PathNavigateFlying(this, worldIn);
        if(!worldIn.isRemote) {
            ModBBAnimations.animation(this, "fury.fly", false);
        }
        this.setSize(1.2f, 1.2f);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        Vec3d prevAcceleration = acceleration;
        acceleration = ModUtils.getEntityVelocity(this).scale(0.1).add(this.acceleration.scale(0.9));

        if (!world.isRemote) {
            if(prevAcceleration.y > 0 && acceleration.y <= 0) {
                ModBBAnimations.animation(this, "fury.fly", true);
            }
            else if (prevAcceleration.y <= 0 && acceleration.y > 0) {
                ModBBAnimations.animation(this, "fury.fly", false);
            }
        }
    }

    public Vec3d getAcceleration() {
        return acceleration;
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(4, new AIRandomFly(this));
        this.tasks.addTask(3, new AIPassiveCircle<>(this, 30));
        this.tasks.addTask(2, new AIFuryDive(100, 5 * 20, this, this::onDiveStart, this::onDiveEnd, this::whileDiving));
        super.initEntityAI();
    }

    @Override
    public void travel(float strafe, float vertical, float forward) {
        ModUtils.aerialTravel(this, strafe, vertical, forward);
    }

    private void onDiveStart() {
        playSound(SoundEvents.ENTITY_VEX_CHARGE, 7.0f, 1.7f);
        ModBBAnimations.animation(this, "fury.dive", false);
    }

    private void whileDiving() {
        Vec3d entityVelocity = ModUtils.getEntityVelocity(this);
        Vec3d spearPos = ModUtils.getAxisOffset(entityVelocity.normalize(), ModUtils.X_AXIS.scale(1.7)).add(getPositionVector());
        DamageSource damageSource = ModDamageSource.builder()
                .type(ModDamageSource.MOB)
                .disablesShields()
                .directEntity(this)
                .element(getElement())
                .build();
        float velocity = (float) entityVelocity.lengthVector();
        ModUtils.handleAreaImpact(0.7f, e -> getAttack() * velocity * 2, this, spearPos, damageSource, 0.5f, 0);
        IntoTheMaelstrom.network.sendToAllTracking(new MessageModParticles(EnumModParticles.EFFECT, spearPos, entityVelocity, ModColors.PURPLE), this);
    }

    private void onDiveEnd() {
        ModBBAnimations.animation(this, "fury.dive", true);
        ModBBAnimations.animation(this, "fury.undive", false);
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, @Nonnull IBlockState state, @Nonnull BlockPos pos) {
    }

    @Override
    public boolean isOnLadder() {
        return false;
    }

    @Override
    public void attackEntityWithRangedAttack(@Nonnull EntityLivingBase target, float distanceFactor) {
    }

    protected AxisAlignedBB getTargetableArea(double targetDistance) {
        return this.getEntityBoundingBox().grow(targetDistance);
    }

    private static final SoundEvent[] ambient = new SoundEvent[]{ModSoundEvents.ENTITY_MAELSTROM_FURY_AMBIENT1,ModSoundEvents.ENTITY_MAELSTROM_FURY_AMBIENT2,ModSoundEvents.ENTITY_MAELSTROM_FURY_AMBIENT3};
    private static final SoundEvent[] hurt = new SoundEvent[]{ModSoundEvents.ENTITY_MAELSTROM_FURY_HURT1,ModSoundEvents.ENTITY_MAELSTROM_FURY_HURT2,ModSoundEvents.ENTITY_MAELSTROM_FURY_HURT3};
    private static final SoundEvent[] death = new SoundEvent[]{ModSoundEvents.ENTITY_MAELSTROM_FURY_DEATH1,ModSoundEvents.ENTITY_MAELSTROM_FURY_DEATH2,ModSoundEvents.ENTITY_MAELSTROM_FURY_DEATH3};


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
    protected boolean canDespawn() {
         return this.ticksExisted > 20 * getMobConfig().getInt("seconds_existed_to_be_able_to_despawn");
    }
}
