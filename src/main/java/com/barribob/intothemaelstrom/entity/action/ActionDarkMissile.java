package com.barribob.intothemaelstrom.entity.action;

import com.barribob.intothemaelstrom.entity.entities.EntityLeveledMob;
import com.barribob.intothemaelstrom.entity.projectile.ProjectileEntityMaelstromMissile;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;

public class ActionDarkMissile implements IAction {
    @Override
    public void performAction(EntityLeveledMob actor, EntityLivingBase target) {
        float inaccuracy = 4.0f;
        float velocity = 0.5f;

        ProjectileEntityMaelstromMissile projectile = new ProjectileEntityMaelstromMissile(actor.world, actor, actor.getAttack());
        double d0 = target.posY + (double) target.getEyeHeight() - 1.0f;
        double d1 = target.posX - actor.posX;
        double d2 = d0 - actor.posY;
        double d3 = target.posZ - actor.posZ;
        projectile.shoot(d1, d2, d3, velocity, inaccuracy);
        projectile.setTravelRange(20f);
        actor.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 1.0F, 1.0F / (actor.getRNG().nextFloat() * 0.4F + 0.8F));
        actor.world.spawnEntity(projectile);
    }
}
