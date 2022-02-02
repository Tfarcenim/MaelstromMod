package com.barribob.intothemaelstrom.entity.entities;

import com.barribob.intothemaelstrom.IntoTheMaelstrom;
import com.barribob.intothemaelstrom.config.ModConfig;
import com.barribob.intothemaelstrom.entity.ai.ModGroundNavigator;
import com.barribob.intothemaelstrom.entity.animation.Animation;
import com.barribob.intothemaelstrom.entity.animation.AnimationNone;
import com.barribob.intothemaelstrom.entity.util.LeapingEntity;
import com.barribob.intothemaelstrom.util.*;
import com.barribob.intothemaelstrom.util.handlers.LevelHandler;
import com.barribob.intothemaelstrom.init.ModSoundEvents;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.PriorityQueue;

/**
 * A base class for the mod's mobs. It includes a hodgepodge of attributes and abilities. One is to scale nicely with the leveling system.
 */
public abstract class EntityLeveledMob<T extends ModelBase> extends EntityCreature implements IAnimatedMob<T>, IElement, LeapingEntity {
    protected static final DataParameter<Float> LEVEL = EntityDataManager.createKey(EntityLeveledMob.class, DataSerializers.FLOAT);
    private float regenStartTimer;
    private static final float regenStartTime = 60;
    protected static final DataParameter<Integer> ELEMENT = EntityDataManager.createKey(EntityLeveledMob.class, DataSerializers.VARINT);

    @SideOnly(Side.CLIENT)
    protected Animation<T> currentAnimation;

    protected static final DataParameter<Boolean> IMMOVABLE = EntityDataManager.createKey(EntityLeveledMob.class, DataSerializers.BOOLEAN);
    private Vec3d initialPosition = null;
    protected double healthScaledAttackFactor = 0.0; // Factor that determines how much attack is affected by health
    private final PriorityQueue<TimedEvent> events = new PriorityQueue<>();
    private boolean leaping = false;

    public EntityLeveledMob(World worldIn) {
        super(worldIn);
        this.setLevel(LevelHandler.INVASION);
        this.experienceValue = 5;
        //todo find another way
       /* if(getMobConfig().hasPath("nbt_spawn_data")) {
            this.readFromNBT(ModUtils.parseNBTFromConfig(getMobConfig().getConfig("nbt_spawn_data")));
        }*/
    }

    public Config getMobConfig() {
        EntityEntry entry = EntityRegistry.getEntry(this.getClass());
        if(entry != null) {
            String entityName = entry.getName();
            if (IntoTheMaelstrom.mobsConfig.hasPath(entityName)) {
                return IntoTheMaelstrom.mobsConfig.getConfig(entityName);
            }
        }
        return ConfigFactory.empty();
    }

    public double getConfigDouble(String path) {
        double aDouble = getMobConfig().getDouble(path);
        return aDouble;
    }

    // Because for some reason the default entity ai for 1.12 sends entities
    // off cliffs and holes instead of going around them
    @Override
    protected PathNavigate createNavigator(World worldIn) {
        if (ModConfig.entities.useVanillaPathfinding) {
            return super.createNavigator(worldIn);
        }
        return new ModGroundNavigator(this, worldIn);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == animationByte && currentAnimation == null) {
            initAnimation();
        } else {
            super.handleStatusUpdate(id);
        }
    }

    @SideOnly(Side.CLIENT)
    protected void initAnimation() {
    }

    @Override
    public void move(MoverType type, double x, double y, double z) {
        if(!this.isImmovable()) {
            super.move(type, x, y, z);
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!isDead && this.getHealth() > 0) {
            boolean foundEvent = true;
            while (foundEvent) {
                TimedEvent event = events.peek();
                if (event != null && event.ticks <= this.ticksExisted) {
                    events.remove();
                    event.callback.run();
                } else {
                    foundEvent = false;
                }
            }
        }

        if (world.isRemote && currentAnimation != null && this.getHealth() > 0) {
            currentAnimation.update();
        }

        if (!world.isRemote) {
            if (this.getAttackTarget() == null) {
                if (this.regenStartTimer > regenStartTime) {
                    if (this.ticksExisted % 20 == 0) {
                        this.heal(this.getMaxHealth() * 0.015f);
                    }
                } else {
                    this.regenStartTimer++;
                }
            } else {
                this.regenStartTimer = 0;
            }
        }

        /**
         * Periodically check if the animations need to be reinitialized
         */
        if (this.ticksExisted % 20 == 1) {
            world.setEntityState(this, animationByte);
        }

        if (this.isImmovable() && this.initialPosition != null) {
            this.setPosition(initialPosition.x, initialPosition.y, initialPosition.z);
        }
    }

    protected boolean isImmovable() {
        return this.dataManager == null ? false : this.dataManager.get(IMMOVABLE);
    }

    protected void setImmovable(boolean immovable) {
        this.dataManager.set(IMMOVABLE, immovable);
    }

    public void setImmovablePosition(Vec3d pos) {
        this.initialPosition = pos;
        this.setPosition(0, 0, 0);
    }

    // Hold the entity in the same position
    @Override
    public void setPosition(double x, double y, double z) {
        super.setPosition(x, y, z);
        if (this.isImmovable()) {
            if (this.initialPosition == null) {
                this.initialPosition = ModUtils.entityPos(this);
            } else {
                super.setPosition(initialPosition.x, initialPosition.y, initialPosition.z);
            }
        }
    }

    @Override
    public Animation<T> getCurrentAnimation() {
        return this.currentAnimation == null ? new AnimationNone() : this.currentAnimation;
    }

    public float getLevel() {
        return this.dataManager == null ? 0 : this.dataManager.get(LEVEL);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
        this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0);
    }

    /**
     * Sets the level, updates attributes, and set health to the updated max health
     */
    public EntityLeveledMob setLevel(float level) {
        this.dataManager.set(LEVEL, level);
        return this;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setFloat("level", getLevel());
        compound.setBoolean("isImmovable", this.isImmovable());
        compound.setInteger("element", getElement().id);
        compound.setInteger("experienceValue", this.experienceValue);
        if (initialPosition != null) {
            compound.setDouble("initialX", initialPosition.x);
            compound.setDouble("initialY", initialPosition.y);
            compound.setDouble("initialZ", initialPosition.z);
        }
        super.writeEntityToNBT(compound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        if (compound.hasKey("level")) {
            this.setLevel(compound.getFloat("level"));
        }
        if (compound.hasKey("element")) {
            this.setElement(Element.getElementFromId(compound.getInteger("element")));
        }
        world.setEntityState(this, animationByte);

        if (compound.hasKey("isImmovable")) {
            this.setImmovable(compound.getBoolean("isImmovable"));
        }
    }

    /**
     * Return the shared monster attribute attack
     */
    public double getAttack() {

        double baseDamage = this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();

        float mobDamage = ModUtils.getMobDamage(baseDamage, healthScaledAttackFactor, this.getMaxHealth(),
                this.getHealth(), this.getLevel(), this.getElement());
        return mobDamage;
    }

    @Override
    protected float applyArmorCalculations(DamageSource source, float damage) {
        return super.applyArmorCalculations(source, ModUtils.getArmoredDamage(source, damage, getLevel(), getElement()));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(LEVEL, 0.0f);
        this.dataManager.register(IMMOVABLE, false);
        this.dataManager.register(ELEMENT, Element.NONE.id);
    }

    @Override
    public Element getElement() {
        return this.dataManager == null ? Element.getElementFromId(Element.NONE.id) : Element.getElementFromId(this.dataManager.get(ELEMENT));
    }

    public EntityLeveledMob setElement(Element element) {
        this.dataManager.set(ELEMENT, element.id);
        return this;
    }

    public void doRender(RenderManager renderManager, double x, double y, double z, float entityYaw, float partialTicks) {
    }

    /**
     * Adds an event to be executed at a later time. Negative ticks are executed immediately.
     *
     * @param runnable
     * @param ticksFromNow
     */
    public void addEvent(Runnable runnable, int ticksFromNow) {
        events.add(new TimedEvent(runnable, this.ticksExisted + ticksFromNow));
    }

    private static class TimedEvent implements Comparable<TimedEvent> {
        Runnable callback;
        int ticks;

        public TimedEvent(Runnable callback, int ticks) {
            this.callback = callback;
            this.ticks = ticks;
        }

        @Override
        public int compareTo(TimedEvent event) {
            return event.ticks < ticks ? 1 : -1;
        }
    }

    @Override
    public boolean isLeaping() {
        return leaping;
    }

    @Override
    public void setLeaping(boolean leaping) {
        this.leaping = leaping;
    }

    protected Vec3d getInitialPosition() {
        return initialPosition;
    }

    @Override
    public void onStopLeaping() {
    }

    public void playSoundWithFallback(SoundEvent sound, SoundEvent fallback, float volume, float pitch) {
        playSound(ModUtils.getConfiguredSound(sound, fallback), volume, pitch);
    }

    public void playSoundWithFallback(SoundEvent sound, SoundEvent fallback, float volume) {
        playSound(ModUtils.getConfiguredSound(sound, fallback), volume, 1.0f + ModRandom.getFloat(0.2f));
    }

    public void playSoundWithFallback(SoundEvent sound, SoundEvent fallback) {
        playSoundWithFallback(sound, fallback, 1.0f);
    }

    public void playSoundWithFallback(SoundEvent sound) {
        playSoundWithFallback(sound, ModSoundEvents.NONE);
    }

    @Override
    public boolean isBeingRidden() {
        return super.isBeingRidden() || (getMobConfig().hasPath("can_be_pushed") && !getMobConfig().getBoolean("can_be_pushed"));
    }
}
