package com.barribob.intothemaelstrom.entity.entities;

import com.barribob.intothemaelstrom.entity.action.ActionAerialTeleport;
import com.barribob.intothemaelstrom.entity.action.ActionRayAttack;
import com.barribob.intothemaelstrom.entity.action.ActionRingAttack;
import com.barribob.intothemaelstrom.entity.action.ActionRuneAttack;
import com.barribob.intothemaelstrom.entity.adjustment.MovingRuneAdjustment;
import com.barribob.intothemaelstrom.entity.ai.AIAerialTimedAttack;
import com.barribob.intothemaelstrom.entity.ai.EntityAIWanderWithGroup;
import com.barribob.intothemaelstrom.entity.ai.FlyingMoveHelper;
import com.barribob.intothemaelstrom.entity.model.ModelStatueOfNirvana;
import com.barribob.intothemaelstrom.entity.projectile.ProjectileEntity;
import com.barribob.intothemaelstrom.entity.projectile.ProjectileEntityHomingFlame;
import com.barribob.intothemaelstrom.entity.projectile.ProjectileEntityMaelstromRune;
import com.barribob.intothemaelstrom.entity.projectile.ProjectileEntityStatueMaelstromMissile;
import com.barribob.intothemaelstrom.entity.util.IAttack;
import com.barribob.intothemaelstrom.entity.util.TimedAttackInitiator;
import com.barribob.intothemaelstrom.init.ModBBAnimations;
import com.barribob.intothemaelstrom.init.ModSoundEvents;
import com.barribob.intothemaelstrom.util.ModColors;
import com.barribob.intothemaelstrom.util.ModRandom;
import com.barribob.intothemaelstrom.util.ModUtils;
import com.barribob.intothemaelstrom.util.handlers.LootTableHandler;
import com.barribob.intothemaelstrom.util.handlers.ParticleManager;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class EntityMaelstromStatueOfNirvana extends EntityMaelstromMob<ModelBase> implements IAttack {
    private final BossInfoServer bossInfo = (new BossInfoServer(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.NOTCHED_20));
    private static boolean doTeleportNext;
    Consumer<EntityLivingBase> previousAttack;

    public EntityMaelstromStatueOfNirvana(World worldIn) {
        super(worldIn);
        this.moveHelper = new FlyingMoveHelper(this);
        this.navigator = new PathNavigateFlying(this, worldIn);
        this.setSize(1.6f, 3.6f);
        this.healthScaledAttackFactor = 0.2;
        if(!world.isRemote) {
            initNirvanaAI();
        }
    }

    private void initNirvanaAI() {
        float attackDistance = (float) this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).getAttributeValue();
        this.tasks.addTask(4,
                new AIAerialTimedAttack(this, attackDistance, 20, 30,
                        new TimedAttackInitiator<>(this, 80)));
    }

    protected AxisAlignedBB getTargetableArea(double targetDistance) {
        return this.getEntityBoundingBox().grow(targetDistance);
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        ModUtils.removeTaskOfType(this.tasks, EntityAIWanderWithGroup.class);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.bossInfo.setPercent(getHealth() / getMaxHealth());
        if (!world.isRemote) {
            world.setEntityState(this, ModUtils.PARTICLE_BYTE);
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (rand.nextInt(8) == 0 && amount > 1) {
            doTeleportNext = true;
        }

        return super.attackEntityFrom(source, amount);
    }

    @Override
    public int startAttack(EntityLivingBase target, float distanceSq, boolean strafingBackwards) {
        boolean canSee = world.rayTraceBlocks(target.getPositionEyes(1), getPositionEyes(1), false, true, false) == null;

        List<Consumer<EntityLivingBase>> attacks = new ArrayList<>(Arrays.asList(
                rayAttack, runeAttack, ringAttack, teleportAttack));
        int i = previousAttack == ringAttack ? 0 : 1;
        double[] weights = {1, 1, i, canSee ? 0 : 2};

        previousAttack = ModRandom.choice(attacks, rand, weights).next();
        if(doTeleportNext) {
            previousAttack = teleportAttack;
            doTeleportNext = false;
        }

        previousAttack.accept(target);

        return 50;
    }

    public Supplier<ProjectileEntity> maelstromFlame = () -> {
        ProjectileEntityHomingFlame projectile = new ProjectileEntityHomingFlame(world, this, this.getAttack() * getConfigDouble("homing_projectile_damage"));
        projectile.setNoGravity(true);
        projectile.setTravelRange(40);
        return projectile;
    };
    Supplier<ProjectileEntity> maelstromRune = () -> new ProjectileEntityMaelstromRune(this.world, this, this.getAttack() * getConfigDouble("maelstrom_rune_damage"));
    Supplier<ProjectileEntity> maelstromMissile = () -> new ProjectileEntityStatueMaelstromMissile(this.world, this, this.getAttack() * getConfigDouble("maelstrom_missile_damage"));

    private final Consumer<EntityLivingBase> teleportAttack = target -> new ActionAerialTeleport(ModColors.PURPLE).performAction(this, target);

    private final Consumer<EntityLivingBase> rayAttack = target -> {
        ModBBAnimations.animation(this, "statue.fireball", false);

        addEvent(() -> {
            new ActionRayAttack(maelstromMissile, 1.1f).performAction(this, target);
            new ActionRayAttack(maelstromMissile, 1.1f).performAction(this, target);
            this.playSound(ModSoundEvents.ENTITY_MAELSTROM_STATUE_OF_NIRVANA_MISSILE_FIRE, 1.0F, ModRandom.getFloat(0.2f) + 1.3f);
        }, 22);
    };

    private final Consumer<EntityLivingBase> runeAttack = target -> {
        ModBBAnimations.animation(this, "statue.runes", false);
        addEvent(() -> new ActionRuneAttack<>(maelstromRune, new MovingRuneAdjustment(target)).performAction(this, target), 12);
    };

    private final Consumer<EntityLivingBase> ringAttack = target -> {
        ModBBAnimations.animation(this, "statue.summon", false);
        addEvent(() -> new ActionRingAttack(maelstromFlame).performAction(this, target), 15);
        playSound(ModSoundEvents.ENTITY_MAELSTROM_STATUE_OF_NIRVANA_SUMMON, 2.5f, 1.0f + ModRandom.getFloat(0.2f));
    };

    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == ModUtils.PARTICLE_BYTE) {
            ParticleManager.spawnEffect(world, ModRandom.randVec().add(this.getPositionVector()), ModColors.PURPLE);
        } else if(id == ModUtils.SECOND_PARTICLE_BYTE) {
            ModUtils.performNTimes(3, i -> ModUtils.circleCallback(i * 0.5f + 1, 30, pos -> {
                ParticleManager.spawnSwirl(world, getPositionVector().add(pos), ModColors.PURPLE, Vec3d.ZERO, ModRandom.range(10, 15));
                ParticleManager.spawnSwirl(world,
                        getPositionVector().add(ModUtils.rotateVector2(pos, ModUtils.yVec(1), 90)),
                        ModColors.PURPLE, Vec3d.ZERO, ModRandom.range(10, 15));
            }));
        }

        super.handleStatusUpdate(id);
    }

    @Override
    public void setCustomNameTag(@Nonnull String name) {
        super.setCustomNameTag(name);
        this.bossInfo.setName(this.getDisplayName());
    }

    @Override
    public void addTrackingPlayer(@Nonnull EntityPlayerMP player) {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void removeTrackingPlayer(@Nonnull EntityPlayerMP player) {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    private static final SoundEvent[] ambient = new SoundEvent[]{ModSoundEvents.ENTITY_MAELSTROM_STATUE_OF_NIRVANA_AMBIENT1,ModSoundEvents.ENTITY_MAELSTROM_STATUE_OF_NIRVANA_AMBIENT2,ModSoundEvents.ENTITY_MAELSTROM_STATUE_OF_NIRVANA_AMBIENT3};
    private static final SoundEvent[] hurt = new SoundEvent[]{ModSoundEvents.ENTITY_MAELSTROM_STATUE_OF_NIRVANA_HURT1,ModSoundEvents.ENTITY_MAELSTROM_STATUE_OF_NIRVANA_HURT2,ModSoundEvents.ENTITY_MAELSTROM_STATUE_OF_NIRVANA_HURT3};
    private static final SoundEvent[] death = new SoundEvent[]{ModSoundEvents.ENTITY_MAELSTROM_STATUE_OF_NIRVANA_DEATH1,ModSoundEvents.ENTITY_MAELSTROM_STATUE_OF_NIRVANA_DEATH2,ModSoundEvents.ENTITY_MAELSTROM_STATUE_OF_NIRVANA_DEATH3};


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
    protected ResourceLocation getLootTable() {
        return LootTableHandler.GOLDEN_BOSS;
    }

    @Override
    public void attackEntityWithRangedAttack(@Nonnull EntityLivingBase target, float distanceFactor) {
    }

    @Override
    public void travel(float strafe, float vertical, float forward) {
        ModUtils.aerialTravel(this, strafe, vertical, forward);
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
}
