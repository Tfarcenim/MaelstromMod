package com.barribob.intothemaelstrom.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;

public class VexAIMoveRandom extends EntityAIBase {
    private final EntityLiving mage;

    public VexAIMoveRandom(EntityLiving entityLiving) {
        mage = entityLiving;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        return !mage.getMoveHelper().isUpdating() && mage.getRNG().nextInt(7) == 0;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting() {
        return false;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask() {
        BlockPos blockpos = new BlockPos(mage);
        int y = mage.getRNG().nextInt(11) - 5;

        if (/*Modconfig.FlyingHeight_limit != 0*/true)
            y = Math.min(mage.world.getHeight(mage.getPosition()).getY() + /*Modconfig.FlyingHeight_limit*/ 10, y);

        for (int i = 0; i < 3; ++i) {
            BlockPos blockpos1 = blockpos.add(mage.getRNG().nextInt(15) - 7, y, mage.getRNG().nextInt(15) - 7);

            if (mage.world.isAirBlock(blockpos1)) {
                mage.getMoveHelper().setMoveTo((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.5D, (double) blockpos1.getZ() + 0.5D, 0.25D);

                if (mage.getAttackTarget() == null) {
                    mage.getLookHelper().setLookPosition((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.5D, (double) blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
                }

                break;
            }
        }
    }
}
