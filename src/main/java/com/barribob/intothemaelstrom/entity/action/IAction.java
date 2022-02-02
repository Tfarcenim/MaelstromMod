package com.barribob.intothemaelstrom.entity.action;

import com.barribob.intothemaelstrom.entity.entities.EntityLeveledMob;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;

/*
 * Base interface for entity actions for example, Shooting, melee attack, and other actions
 */
public interface IAction<T extends ModelBase> {
     void performAction(EntityLeveledMob<T> actor, EntityLivingBase target);

    IAction NONE = (actor, target) -> {
    };
}
