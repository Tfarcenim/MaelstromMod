package com.barribob.MaelstromMod.items;

import com.barribob.MaelstromMod.init.ModCreativeTabs;
import com.barribob.MaelstromMod.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * The base class for basic mod foods
 */
public class ItemBase extends Item {
    public ItemBase(String name, CreativeTabs tab) {
        setUnlocalizedName(name);
        setRegistryName(name);
            setCreativeTab(tab);
        ModItems.ITEMS.add(this);
    }

    public ItemBase(String name) {
        this(name, ModCreativeTabs.ITEMS);
    }

}
