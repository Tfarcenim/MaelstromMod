package com.barribob.MaelstromMod.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.util.math.MathHelper;

public class VexAIMoveControl extends EntityMoveHelper {
    private final EntityLiving mage;

    public VexAIMoveControl(EntityLiving mage) {
        super(mage);
        this.mage = mage;
    }

    public void onUpdateMoveHelper() {
        if (this.action == Action.MOVE_TO) {
            double d0 = this.posX - mage.posX;
            double d1 = this.posY - mage.posY;
            double d2 = this.posZ - mage.posZ;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            d3 = MathHelper.sqrt(d3);

            if (d3 < mage.getEntityBoundingBox().getAverageEdgeLength()) {
                this.action = Action.WAIT;
                mage.motionX *= 0.5D;
                mage.motionY *= 0.5D;
                mage.motionZ *= 0.5D;
            } else {
                mage.motionX += d0 / d3 * 0.05D * this.speed;
                mage.motionY += d1 / d3 * 0.05D * this.speed;
                mage.motionZ += d2 / d3 * 0.05D * this.speed;

                if (mage.getAttackTarget() == null) {
                    mage.rotationYaw = -((float) MathHelper.atan2(mage.motionX, mage.motionZ)) * (180F / (float) Math.PI);
                } else {
                    double d4 = mage.getAttackTarget().posX - mage.posX;
                    double d5 = mage.getAttackTarget().posZ - mage.posZ;
                    mage.rotationYaw = -((float) MathHelper.atan2(d4, d5)) * (180F / (float) Math.PI);
                }
                mage.renderYawOffset = mage.rotationYaw;
            }
        }
    }
}
