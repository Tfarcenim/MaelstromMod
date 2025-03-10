package com.barribob.intothemaelstrom.entity.entities;

import com.barribob.intothemaelstrom.IntoTheMaelstrom;
import com.barribob.intothemaelstrom.config.ModConfig;
import com.barribob.intothemaelstrom.entity.ai.EntityAIAvoidCrowding;
import com.barribob.intothemaelstrom.entity.ai.EntityAIFollowAttackers;
import com.barribob.intothemaelstrom.entity.ai.EntityAIWanderWithGroup;
import com.barribob.intothemaelstrom.init.ModDimensions;
import com.barribob.intothemaelstrom.mana.IMana;
import com.barribob.intothemaelstrom.mana.ManaProvider;
import com.barribob.intothemaelstrom.packets.MessageMana;
import com.barribob.intothemaelstrom.util.Element;
import com.barribob.intothemaelstrom.util.ModRandom;
import com.barribob.intothemaelstrom.util.ModUtils;
import com.barribob.intothemaelstrom.util.handlers.LootTableHandler;
import com.barribob.intothemaelstrom.util.handlers.ParticleManager;
import com.google.common.base.Predicate;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

/**
 * The base mob that most mobs in this mod will extend A lot of these methods are from the EntityMob class to make it behave similarly
 */
public abstract class EntityMaelstromMob<T extends ModelBase>  extends EntityLeveledMob<T> implements IRangedAttackMob {
    // Swinging arms is the animation for the attack
    private static final DataParameter<Boolean> SWINGING_ARMS = EntityDataManager.<Boolean>createKey(EntityLeveledMob.class, DataSerializers.BOOLEAN);
    public static final Predicate<Entity> CAN_TARGET = entity -> {
        boolean isConfigFriend = false;
        if (entity != null) {
            EntityEntry entry = EntityRegistry.getEntry(entity.getClass());
            if(entry != null) {
                ResourceLocation registryName = entry.getRegistryName();
                if(registryName != null) {
                    isConfigFriend = IntoTheMaelstrom.maelstromFriendsConfig.getStringList("maelstrom_friends")
                            .contains(registryName.toString());
                }
            }
        }

        return !(entity instanceof EntityMaelstromMob) && !isConfigFriend;
    };

    public static boolean isMaelstromMob(Entity entity) {
        return !CAN_TARGET.apply(entity);
    }

    public EntityMaelstromMob(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIFollowAttackers(this, 1.0D));
        this.tasks.addTask(6, new EntityAIAvoidCrowding(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderWithGroup(this, 1.0D));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, 1, true, false, null){
            @Override
            @Nonnull
            protected AxisAlignedBB getTargetableArea(double targetDistance) {
                return EntityMaelstromMob.this.getTargetableArea(targetDistance);
            }
        });

        if (ModConfig.entities.attackAll) {
            // This makes it so that the entity attack every entity except others of its kind
            this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityLiving>(this, EntityLiving.class, 10, true, false, CAN_TARGET){
                @Override
                @Nonnull
                protected AxisAlignedBB getTargetableArea(double targetDistance) {
                    return EntityMaelstromMob.this.getTargetableArea(targetDistance);
                }
            });
        }
    }

    protected AxisAlignedBB getTargetableArea(double targetDistance) {
        return this.getEntityBoundingBox().grow(targetDistance, 4.0D, targetDistance);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
    }

    @Override
    public SoundCategory getSoundCategory() {
        return SoundCategory.HOSTILE;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_HOSTILE_SWIM;
    }

    @Override
    protected SoundEvent getSplashSound() {
        return SoundEvents.ENTITY_HOSTILE_SPLASH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_HOSTILE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_HOSTILE_DEATH;
    }

    @Override
    protected SoundEvent getFallSound(int heightIn) {
        return heightIn > 4 ? SoundEvents.ENTITY_HOSTILE_BIG_FALL : SoundEvents.ENTITY_HOSTILE_SMALL_FALL;
    }

    @Override
    protected ResourceLocation getLootTable() {
        if (this.getElement().equals(Element.AZURE)) {
            return LootTableHandler.AZURE_MAELSTROM;
        } else if (this.getElement().equals(Element.GOLDEN)) {
            return LootTableHandler.GOLDEN_MAELSTROM;
        } else if (this.getElement().equals(Element.CRIMSON)) {
            return LootTableHandler.CRIMSON_MAELSTROM;
        }

        return LootTableHandler.MAELSTROM;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    @Override
    public boolean getCanSpawnHere() {
        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && super.getCanSpawnHere();
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL) {
            this.setDead();
        }
    }

    /**
     * Entity won't drop items or experience points if this returns false
     */
    @Override
    protected boolean canDropLoot() {
        return true;
    }

    /**
     * Changes the default "white smoke" spawning from a mob spawner to a purple smoke
     */
    @Override
    public void spawnExplosionParticle() {
        if (this.world.isRemote) {
            for (int i = 0; i < 20; ++i) {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                ParticleManager.spawnMaelstromLargeSmoke(world, rand, new Vec3d(this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width - d0 * 10.0D,
                        this.posY + this.rand.nextFloat() * this.height - d1 * 10.0D, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width - d2 * 10.0D));
            }
        } else {
            this.world.setEntityState(this, (byte) 20);
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (!CAN_TARGET.apply(source.getTrueSource())) {
            return false;
        }
        return super.attackEntityFrom(source, amount);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(SWINGING_ARMS, false);
    }

    public boolean isSwingingArms() {
        return this.dataManager.get(SWINGING_ARMS);
    }

    @Override
    public void setSwingingArms(boolean swingingArms) {
        this.dataManager.set(SWINGING_ARMS, swingingArms);
    }

    @Override
    public void onDeath(DamageSource cause) {
        if (!world.isRemote && cause.getTrueSource() instanceof EntityPlayerMP) {
            IMana mana = cause.getTrueSource().getCapability(ManaProvider.MANA, null);
            if (!mana.isLocked()) {
                mana.replenish(getManaExp());
                IntoTheMaelstrom.network.sendTo(new MessageMana(mana.getMana()), (EntityPlayerMP) cause.getTrueSource());
            }
        }
        super.onDeath(cause);
    }

    protected float getManaExp() {
        return Math.round(this.getMaxHealth() * 0.05f);
    }

    @Override
    protected void onDeathUpdate() {
        ++this.deathTime;

        if (this.deathTime == 20) {
            if (!this.world.isRemote && (this.isPlayer() || this.recentlyHit > 0 && this.canDropLoot() && this.world.getGameRules().getBoolean("doMobLoot"))) {
                int i = this.getExperiencePoints(this.attackingPlayer);
                i = net.minecraftforge.event.ForgeEventFactory.getExperienceDrop(this, this.attackingPlayer, i);
                while (i > 0) {
                    int j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, j));
                }
            }

            this.setDead();

            world.setEntityState(this, ModUtils.MAELSTROM_PARTICLE_BYTE);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == ModUtils.MAELSTROM_PARTICLE_BYTE) {
            for (int i = 0; i < 20; i++) {
                ParticleManager.spawnMaelstromLargeSmoke(world, rand, this.getPositionVector().add(ModRandom.gaussVec().scale(0.5f).add(ModUtils.yVec(1))));
            }
        }
        super.handleStatusUpdate(id);
    }

    @Override
    protected boolean canDespawn() {
        if (this.dimension == ModDimensions.CRIMSON_KINGDOM.getId() || this.dimension == ModDimensions.NEXUS.getId()) {
            // Allow despawn after about twenty minutes of being idle
            return this.ticksExisted > 20 * 60 * 20;
        }
        return true;
    }
}
