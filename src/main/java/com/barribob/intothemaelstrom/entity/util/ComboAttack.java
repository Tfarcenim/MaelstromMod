package com.barribob.intothemaelstrom.entity.util;

import com.barribob.intothemaelstrom.entity.action.IAction;
import com.barribob.intothemaelstrom.entity.animation.Animation;
import com.barribob.intothemaelstrom.entity.animation.AnimationNone;
import net.minecraft.client.model.ModelBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Designed to organize multiple attacks for a single entity Uses bytes to
 * handle the animations on the server side smoothly
 */
public class ComboAttack<T extends ModelBase> {
    private HashMap<Byte, IAction<T>> actions = new HashMap<>();

    @SideOnly(Side.CLIENT)
    private HashMap<Byte, Supplier<Animation<T>>> animations;
    private byte currentAttack;

    public void setCurrentAttack(byte b) {
        currentAttack = b;
    }

    public byte getCurrentAttack() {
        return currentAttack;
    }

    public IAction<T> getCurrentAttackAction() {
        return getAction(currentAttack);
    }

    @SideOnly(Side.CLIENT)
    public void setAnimation(byte b, IAction<T> action, Supplier<Animation<T>> anim) {
        if (animations == null) {
            animations = new HashMap<>();
        }
        setAttack(b, action);
        animations.put(b, anim);
    }

    /*public void setAttack(byte b, BiConsumer<EntityLeveledMob<T>, EntityLivingBase> action) {
        actions.put(b, action::accept);
    }*/

    public void setAttack(byte b, IAction<T> action) {
        actions.put(b, action);
    }

    @SideOnly(Side.CLIENT)
    public Animation<T> getAnimation(byte b) {
        if (animations == null) {
            return new AnimationNone<>();
        }
        if (!animations.containsKey(b)) {
            throw new IllegalArgumentException("The byte " + b + " does not correspond to an attack");
        }
        return animations.get(b).get();
    }

    private IAction<T> getAction(byte b) {
        if (!actions.containsKey(b)) {
            throw new IllegalArgumentException("The byte " + b + " does not correspond to an attack");
        }
        return actions.get(b);
    }
}
