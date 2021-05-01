package com.barribob.intothemaelstrom.items;

import com.barribob.intothemaelstrom.init.ModCreativeTabs;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;

public class ItemModRecord extends ItemRecord {
    public ItemModRecord(String name, SoundEvent event) {
        super(name, event);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ModCreativeTabs.ITEMS);
    }
}
