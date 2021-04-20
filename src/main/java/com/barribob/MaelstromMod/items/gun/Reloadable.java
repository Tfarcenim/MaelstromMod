package com.barribob.MaelstromMod.items.gun;

import net.minecraft.item.ItemStack;

public interface Reloadable {
    float getCooldownForDisplay(ItemStack stack);
}
