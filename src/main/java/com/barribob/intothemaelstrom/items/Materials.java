package com.barribob.intothemaelstrom.items;

import com.barribob.intothemaelstrom.IntoTheMaelstrom;
import com.barribob.intothemaelstrom.init.ModItems;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class Materials {
    public static final Item.ToolMaterial DAGGER = EnumHelper.addToolMaterial("rare_dagger", 2, 600, 8.0f, ModItems.BASE_MELEE_DAMAGE, 20);
    public static final Item.ToolMaterial SWORD = EnumHelper.addToolMaterial("rare_sword", 2, 500, 8.0f, ModItems.BASE_MELEE_DAMAGE, 20);
    public static final Item.ToolMaterial BATTLEAXE = EnumHelper.addToolMaterial("rare_battleaxe", 2, 400, 8.0f, ModItems.BASE_MELEE_DAMAGE, 20);
    public static final ItemArmor.ArmorMaterial ARMOR = EnumHelper.addArmorMaterial("maelstrom", IntoTheMaelstrom.MOD_ID + ":maelstrom", 32, new int[]{3, 6, 8, 3}, 16, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0);
    public static final Item.ToolMaterial ENERGETIC_PICKAXE = EnumHelper.addToolMaterial("energetic_pickaxe", 5, 8000, 100, 6, 15);
}
