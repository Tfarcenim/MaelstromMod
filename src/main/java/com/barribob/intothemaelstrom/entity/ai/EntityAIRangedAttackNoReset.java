package com.barribob.intothemaelstrom.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IRangedAttackMob;

/**
 * Makes the task unresettable
 */
public class EntityAIRangedAttackNoReset<T extends EntityCreature  & IRangedAttackMob> extends EntityAIRangedAttack<T> {
    public EntityAIRangedAttackNoReset(T entity, double moveSpeedAmp, int attackCooldown, float armsRaisedTime, float maxAttackDistance, float strafeAmount) {
        super(entity, moveSpeedAmp, attackCooldown, armsRaisedTime, maxAttackDistance, strafeAmount);
    }

    @Override
    public void resetTask() {
    }
}
