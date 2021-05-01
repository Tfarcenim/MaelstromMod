package com.barribob.intothemaelstrom.entity.entities.gauntlet;

import com.barribob.intothemaelstrom.entity.entities.EntityMaelstromMob;
import com.barribob.intothemaelstrom.entity.projectile.ProjectileEntity;
import com.barribob.intothemaelstrom.entity.projectile.ProjectileEntityMegaFireball;
import com.barribob.intothemaelstrom.init.ModSoundEvents;
import com.barribob.intothemaelstrom.util.ModRandom;
import com.barribob.intothemaelstrom.util.ModUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class EntityMaelstromGauntlet extends EntityAbstractMaelstromGauntlet {
    Supplier<Vec3d> position = () -> getAttackTarget() == null ? null : getAttackTarget().getPositionVector();
    private final IGauntletAction punchAttack = new PunchAction("gauntlet.punch", position, () -> {
    }, this, fist);
    private final IGauntletAction laserAttack = new LaserAction(this, stopLazerByte, (vec3d) -> {
    });
    private final IGauntletAction summonAttack = new SummonMobsAction(this::spawnMob, this, fist);
    private final IGauntletAction fireballAttack = new FireballThrowAction<>((target) -> target.getPositionEyes(1), this::generateFireball, this);
    private final double fireballHealth = getMobConfig().getDouble("use_fireball_at_health");
    private final double lazerHealth = getMobConfig().getDouble("use_lazer_at_health");
    private final double spawnHealth = getMobConfig().getDouble("use_spawning_at_health");

    public EntityMaelstromGauntlet(World worldIn) {
        super(worldIn);
    }

    private void spawnMob() {
        ModUtils.spawnMob(world, this.getPosition(), this.getLevel(), getMobConfig().getConfig("summoning_algorithm"));
    }

    private ProjectileEntity generateFireball() {
        ProjectileEntityMegaFireball fireball = new ProjectileEntityMegaFireball(world, this, this.getAttack() * getConfigDouble("fireball_damage"), null, true);
        fireball.setTravelRange(30);
        return fireball;
    }

    @Override
    protected IGauntletAction getNextAttack(EntityLivingBase target, float distanceSq, IGauntletAction previousAction) {
        List<IGauntletAction> attacks = new ArrayList<>(Arrays.asList(punchAttack, laserAttack, summonAttack, fireballAttack));
        int numMinions = (int) ModUtils.getEntitiesInBox(this, getEntityBoundingBox().grow(20, 10, 20)).stream().filter(EntityMaelstromMob::isMaelstromMob).count();

        double defendWeight = previousAction == this.summonAttack || numMinions > 3 || this.getHealth() > spawnHealth ? 0 : 0.8;
        double fireballWeight = distanceSq < Math.pow(25, 2) && this.getHealth() < fireballHealth ? 1 : 0;
        double lazerWeight = distanceSq < Math.pow(35, 2) && this.getHealth() < lazerHealth ? 1 : 0;
        double punchWeight = ModUtils.canEntityBeSeen(this, target) ? Math.sqrt(distanceSq) / 25 : 3;

        double[] weights = {punchWeight, lazerWeight, defendWeight, fireballWeight};
        return ModRandom.choice(attacks, rand, weights).next();
    }

    private static final SoundEvent[] ambient = new SoundEvent[]{ModSoundEvents.ENTITY_MAELSTROM_GAUNTLET_AMBIENT1,ModSoundEvents.ENTITY_MAELSTROM_GAUNTLET_AMBIENT2,ModSoundEvents.ENTITY_MAELSTROM_GAUNTLET_AMBIENT3};
    private static final SoundEvent[] hurt = new SoundEvent[]{ModSoundEvents.ENTITY_MAELSTROM_GAUNTLET_HURT1,ModSoundEvents.ENTITY_MAELSTROM_GAUNTLET_HURT2,ModSoundEvents.ENTITY_MAELSTROM_GAUNTLET_HURT3};
    private static final SoundEvent[] death = new SoundEvent[]{ModSoundEvents.ENTITY_MAELSTROM_GAUNTLET_DEATH1,ModSoundEvents.ENTITY_MAELSTROM_GAUNTLET_DEATH2,ModSoundEvents.ENTITY_MAELSTROM_GAUNTLET_DEATH3};


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
    
}
