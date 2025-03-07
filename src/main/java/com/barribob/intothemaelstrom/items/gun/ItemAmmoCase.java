package com.barribob.intothemaelstrom.items.gun;

import com.barribob.intothemaelstrom.config.ModConfig;
import com.barribob.intothemaelstrom.items.ILeveledItem;
import com.barribob.intothemaelstrom.util.ModUtils;
import com.barribob.intothemaelstrom.util.handlers.LevelHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemAmmoCase extends Item implements ILeveledItem {
    private final float level;

    public ItemAmmoCase(float level) {
        this.level = level;
        this.setMaxDamage(Math.round(LevelHandler.getMultiplierFromLevel(level) * 110));
        this.setMaxStackSize(1);
    }

    @Override
    public float getLevel() {
        return this.level;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(!ModConfig.gui.disableMaelstromArmorItemTooltips) {
            tooltip.add(ModUtils.getDisplayLevel(this.level));
        }
    }
}
