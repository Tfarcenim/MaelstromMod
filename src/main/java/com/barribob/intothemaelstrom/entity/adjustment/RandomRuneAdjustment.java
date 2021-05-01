package com.barribob.intothemaelstrom.entity.adjustment;

import com.barribob.intothemaelstrom.entity.util.IEntityAdjustment;
import com.barribob.intothemaelstrom.util.ModRandom;
import com.barribob.intothemaelstrom.util.ModUtils;
import net.minecraft.entity.Entity;

public class RandomRuneAdjustment implements IEntityAdjustment {
    private final Entity target;

    public RandomRuneAdjustment(Entity target) {
        this.target = target;
    }

    @Override
    public void adjust(Entity entity) {
        ModUtils.setEntityPosition(entity,
                target.getPositionVector()
                        .add(ModRandom.randFlatVec(ModUtils.Y_AXIS).scale(ModRandom.getFloat(2)))
                        .add(ModUtils.yVec(0.1)));
    }
}
