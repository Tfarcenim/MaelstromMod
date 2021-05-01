package com.barribob.intothemaelstrom.util;

import com.barribob.intothemaelstrom.entity.animation.Animation;
import net.minecraft.client.model.ModelBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IAnimatedMob<T extends ModelBase> {
    byte animationByte = 13;

    @SideOnly(Side.CLIENT)
    Animation<T> getCurrentAnimation();
}
