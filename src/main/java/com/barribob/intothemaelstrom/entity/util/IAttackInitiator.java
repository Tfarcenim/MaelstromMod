package com.barribob.intothemaelstrom.entity.util;

import net.minecraft.entity.EntityLivingBase;

public interface IAttackInitiator {
    void update(EntityLivingBase target);
    void resetTask();
}
