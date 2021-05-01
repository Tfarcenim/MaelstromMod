package com.barribob.intothemaelstrom.items.tools;

import com.barribob.intothemaelstrom.init.ModCreativeTabs;
import com.barribob.intothemaelstrom.init.ModItems;
import net.minecraft.item.ItemPickaxe;

public class ToolPickaxe extends ItemPickaxe {
    public ToolPickaxe(String name, ToolMaterial material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ModCreativeTabs.ITEMS);

        ModItems.ITEMS.add(this);
    }
}
