package com.barribob.intothemaelstrom.init;

import com.barribob.intothemaelstrom.gui.MaelstromCreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModCreativeTabs {
    public static CreativeTabs ITEMS = new MaelstromCreativeTab(CreativeTabs.getNextID(), "items", () -> ModItems.MAELSTROM_KEY);
    public static CreativeTabs BLOCKS = new MaelstromCreativeTab(CreativeTabs.getNextID(), "blocks", () -> Item.getItemFromBlock(ModBlocks.MAELSTROM_BRICKS));
}
