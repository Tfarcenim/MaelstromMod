package com.barribob.intothemaelstrom.entity.action;

import com.barribob.intothemaelstrom.entity.entities.EntityLeveledMob;
import com.barribob.intothemaelstrom.entity.projectile.ProjectileEntity;
import com.barribob.intothemaelstrom.entity.util.IEntityAdjustment;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;

import java.util.function.Supplier;

public class ActionRuneAttack<T extends ModelBase> implements IAction<T> {
    Supplier<ProjectileEntity> projectileSupplier;
    IEntityAdjustment adjustment;
    private static final float zeroish = 0.001f;

    public ActionRuneAttack(Supplier<ProjectileEntity> projectileSupplier, IEntityAdjustment adjustment) {
        this.projectileSupplier = projectileSupplier;
        this.adjustment = adjustment;
    }

    @Override
    public void performAction(EntityLeveledMob<T> actor, EntityLivingBase target) {
        ProjectileEntity projectile = projectileSupplier.get();
        projectile.shoot(zeroish, zeroish, zeroish, zeroish, zeroish);
        adjustment.adjust(projectile);
        projectile.setTravelRange(50);
        actor.world.spawnEntity(projectile);
    }
}
