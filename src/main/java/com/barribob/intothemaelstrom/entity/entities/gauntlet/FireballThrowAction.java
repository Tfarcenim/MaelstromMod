package com.barribob.intothemaelstrom.entity.entities.gauntlet;

import com.barribob.intothemaelstrom.entity.entities.EntityLeveledMob;
import com.barribob.intothemaelstrom.entity.projectile.ProjectileEntity;
import com.barribob.intothemaelstrom.entity.util.IPitch;
import com.barribob.intothemaelstrom.init.ModBBAnimations;
import com.barribob.intothemaelstrom.util.ModUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;

import java.util.function.Function;
import java.util.function.Supplier;

public class FireballThrowAction<T extends EntityLeveledMob & IPitch> implements IGauntletAction {

    private final Function<EntityLivingBase, Vec3d> target;
    private final Supplier<ProjectileEntity> projectileSupplier;
    private final T entity;

    public FireballThrowAction(Function<EntityLivingBase, Vec3d> target, Supplier<ProjectileEntity> projectileSupplier, T entity) {
        this.target = target;
        this.projectileSupplier = projectileSupplier;
        this.entity = entity;
    }

    @Override
    public void doAction() {
        EntityLivingBase attackTarget = entity.getAttackTarget();
        if(attackTarget == null) return;

        ModBBAnimations.animation(entity, "gauntlet.fireball", false);
        ProjectileEntity proj = projectileSupplier.get();

        entity.addEvent(() -> entity.world.spawnEntity(proj), 10);

        // Hold the fireball in place
        for (int i = 10; i < 27; i++) {
            entity.addEvent(() -> {
                Vec3d fireballPos = entity.getPositionEyes(1).add(ModUtils.getAxisOffset(ModUtils.getLookVec(entity.getPitch(), entity.renderYawOffset), new Vec3d(1, 0, 0)));
                ModUtils.setEntityPosition(proj, fireballPos);
            }, i);
        }

        // Throw the fireball
        entity.addEvent(() -> {
            Vec3d vel = target.apply(attackTarget).subtract(ModUtils.yVec(1)).subtract(proj.getPositionVector());
            proj.shoot(vel.x, vel.y, vel.z, 0.8f, 0.3f);
            ModUtils.addEntityVelocity(entity, vel.normalize().scale(-0.8));
        }, 27);
    }

    @Override
    public int attackLength() {
        return 52;
    }
}
