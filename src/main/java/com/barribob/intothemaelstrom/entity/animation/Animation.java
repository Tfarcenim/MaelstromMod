package com.barribob.intothemaelstrom.entity.animation;

import net.minecraft.client.model.ModelBase;

public interface Animation<T extends ModelBase> {
    /*
     * Called by the entity's model to get the rotations it needs
     */
    void setModelRotations(T model, float limbSwing, float limbSwingAmount, float partialTicks);

    /*
     * Called by the entity to move the animation forward
     */
    void update();

    /*
     * Reset the animation back to the beginning of its frames
     */
    void startAnimation();
}
