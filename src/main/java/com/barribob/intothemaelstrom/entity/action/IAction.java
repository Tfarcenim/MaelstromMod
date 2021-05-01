package com.barribob.intothemaelstrom.entity.action;

import com.barribob.intothemaelstrom.entity.entities.EntityLeveledMob;
import net.minecraft.entity.EntityLivingBase;

/*
 * Base interface for entity actions for example, Shooting, melee attack, and other actions
 */
public interface IAction {
    void performAction(EntityLeveledMob actor, EntityLivingBase target);

    IAction NONE = (actor, target) -> {
    };
}
