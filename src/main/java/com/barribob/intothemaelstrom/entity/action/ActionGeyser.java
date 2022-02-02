package com.barribob.intothemaelstrom.entity.action;

import com.barribob.intothemaelstrom.entity.entities.EntityLeveledMob;
import com.barribob.intothemaelstrom.entity.projectile.EntityGeyser;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;

public class ActionGeyser<T extends ModelBase> implements IAction<T> {
    @Override
    public void performAction(EntityLeveledMob<T> actor, EntityLivingBase target) {
        float zeroish = 0.001f;
        EntityGeyser projectile = new EntityGeyser(actor.world, actor, actor.getAttack());
        projectile.setPosition(target.posX, target.posY, target.posZ);
        projectile.shoot(zeroish, zeroish, zeroish, zeroish, zeroish);
        projectile.setTravelRange(25);
        actor.world.spawnEntity(projectile);
    }
}
