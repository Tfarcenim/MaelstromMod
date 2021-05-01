package com.barribob.intothemaelstrom.entity.animation;

import net.minecraft.client.model.ModelBase;

public class AnimationNone<T extends ModelBase> extends ArrayAnimation<T> {
    public AnimationNone() {
        super(0);
    }

    @Override
    public void setModelRotations(T model, float limbSwing, float limbSwingAmount, float partialTicks) {
    }
}