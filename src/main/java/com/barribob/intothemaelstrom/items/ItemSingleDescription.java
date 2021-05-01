package com.barribob.intothemaelstrom.items;

import com.barribob.intothemaelstrom.util.ModUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemSingleDescription extends Item {
    private final String desc;

    public ItemSingleDescription(String desc) {
        this.desc = desc;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.GRAY + ModUtils.translateDesc(desc));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
