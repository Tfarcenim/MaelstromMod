package com.barribob.intothemaelstrom.items.gun;

import net.minecraft.item.ItemStack;

public interface Reloadable {
    float getCooldownForDisplay(ItemStack stack);
}
