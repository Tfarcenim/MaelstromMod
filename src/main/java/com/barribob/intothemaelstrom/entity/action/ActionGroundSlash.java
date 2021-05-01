package com.barribob.intothemaelstrom.entity.action;

import com.barribob.intothemaelstrom.entity.entities.EntityLeveledMob;
import com.barribob.intothemaelstrom.entity.projectile.ProjectileEntity;
import net.minecraft.entity.EntityLivingBase;

import java.util.function.Supplier;

public class ActionGroundSlash implements IAction {
    public final Supplier<ProjectileEntity> supplier;

    public ActionGroundSlash(Supplier<ProjectileEntity> p) {
        supplier = p;
    }

    @Override
    public void performAction(EntityLeveledMob actor, EntityLivingBase target) {
        float inaccuracy = 0.0f;
        float speed = 0.8f;
        float pitch = 0; // Projectiles aim straight ahead always
        ProjectileEntity projectile = supplier.get();
        projectile.setPosition(actor.posX, actor.posY, actor.posZ);
        projectile.shoot(actor, pitch, actor.rotationYaw, 0.0F, speed, inaccuracy);
        projectile.setTravelRange(20f);
        actor.world.spawnEntity(projectile);
    }
}
