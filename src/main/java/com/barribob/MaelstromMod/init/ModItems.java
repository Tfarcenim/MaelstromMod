package com.barribob.MaelstromMod.init;

import java.util.ArrayList;
import java.util.List;

import com.barribob.MaelstromMod.items.ItemAzureKey;
import com.barribob.MaelstromMod.items.ItemBase;
import com.barribob.MaelstromMod.items.ItemFoodBase;
import com.barribob.MaelstromMod.items.armor.ModArmorBase;
import com.barribob.MaelstromMod.items.gun.ItemBoomstick;
import com.barribob.MaelstromMod.items.gun.ItemMaelstromCannon;
import com.barribob.MaelstromMod.items.gun.ItemMusket;
import com.barribob.MaelstromMod.items.gun.ItemPumpkin;
import com.barribob.MaelstromMod.items.gun.ItemQuakeStaff;
import com.barribob.MaelstromMod.items.gun.ItemWispStaff;
import com.barribob.MaelstromMod.items.tools.ToolAxe;
import com.barribob.MaelstromMod.items.tools.ToolBattleaxe;
import com.barribob.MaelstromMod.items.tools.ToolDagger;
import com.barribob.MaelstromMod.items.tools.ToolLongsword;
import com.barribob.MaelstromMod.items.tools.ToolPickaxe;
import com.barribob.MaelstromMod.items.tools.ToolSpade;
import com.barribob.MaelstromMod.items.tools.ToolSword;
import com.barribob.MaelstromMod.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

/**
 * 
 * Holds all of our new items
 *
 */
public class ModItems
{
    private static final float BASE_MELEE_DAMAGE = 6.0f;
    
    // Tool materials
    private static final ToolMaterial COMMON_DAGGER = EnumHelper.addToolMaterial("common_dagger", 2, 300, 8.0f, BASE_MELEE_DAMAGE, 14);
    private static final ToolMaterial COMMON_BATTLEAXE = EnumHelper.addToolMaterial("common_battleaxe", 2, 200, 8.0f, BASE_MELEE_DAMAGE, 14);
    private static final ToolMaterial COMMON_SWORD = EnumHelper.addToolMaterial("common_sword", 2, 250, 8.0f, BASE_MELEE_DAMAGE, 14);
    
    private static final ToolMaterial RARE_SWORD = EnumHelper.addToolMaterial("rare_sword", 2, 500, 8.0f, BASE_MELEE_DAMAGE, 20);
    
    // Armor materials
    private static final ArmorMaterial COMMON_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("maelstrom", Reference.MOD_ID + ":maelstrom", 16, new int[] { 3, 6, 8, 3 }, 12,
	    SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0f);
    
    private static final ArmorMaterial RARE_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("maelstrom", Reference.MOD_ID + ":maelstrom", 32, new int[] { 3, 6, 8, 3 }, 16,
	    SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0f);

    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item INVISIBLE = new ItemBase("invisible", null);

    // The azure dimension's items
    public static final Item ELK_HIDE = new ItemBase("elk_hide", ModCreativeTabs.ALL);
    public static final Item ELK_STRIPS = new ItemFoodBase("elk_strips", ModCreativeTabs.ALL, 3, 0.3F, true);
    public static final Item ELK_JERKY = new ItemFoodBase("elk_jerky", ModCreativeTabs.ALL, 8, 1.0F, true);
    public static final Item PLUM = new ItemFoodBase("plum", ModCreativeTabs.ALL, 4, 0.3F, true);
    public static final Item IRON_PELLET = new ItemBase("iron_pellet", ModCreativeTabs.ALL);

    public static final Item AZURE_MAELSTROM_CORE_CRYSTAL = new ItemBase("azure_maelstrom_core_crystal", ModCreativeTabs.ALL);
    public static final Item AZURE_KEY = new ItemAzureKey("azure_key", ModCreativeTabs.ALL);
    public static final Item BEASTS_KEY = new ItemAzureKey("beast_key", ModCreativeTabs.ALL);

    private static final int COMMON_USE_TIME = 6000;
    private static final int RARE_USE_TIME = 12000;
    public static final Item BOOMSTICK = new ItemBoomstick("boomstick", 60, RARE_USE_TIME, IRON_PELLET, 1f, ModCreativeTabs.ALL);
    public static final Item MUSKET = new ItemMusket("musket", 40, RARE_USE_TIME, 5.0f, IRON_PELLET, 1f, ModCreativeTabs.ALL);
    public static final Item MAELSTROM_CANNON = new ItemMaelstromCannon("maelstrom_cannon", 25, COMMON_USE_TIME, 1f, ModCreativeTabs.ALL);
    public static final Item WILLOTHEWISP_STAFF = new ItemWispStaff("will-o-the-wisp_staff", 60, COMMON_USE_TIME, 1f, ModCreativeTabs.ALL);
    public static final Item QUAKE_STAFF = new ItemQuakeStaff("quake_staff", 40, COMMON_USE_TIME, 1f, ModCreativeTabs.ALL);
    
    public static final Item SWORD_OF_SHADES = new ToolLongsword("sword_of_shades", 4.0f, COMMON_SWORD, 1f);
    public static final Item SHADOW_DAGGER = new ToolDagger("shadow_dagger", COMMON_DAGGER, 1f);
    public static final Item MAELSTROM_BATTLEAXE = new ToolBattleaxe("maelstrom_battleaxe", COMMON_BATTLEAXE, 1f);
    public static final Item BEAST_BLADE = new ToolSword("beast_blade", RARE_SWORD, 1.5f);

    public static final Item MAELSTROM_HELMET = new ModArmorBase("maelstrom_helmet", COMMON_ARMOR_MATERIAL, 1, EntityEquipmentSlot.HEAD, 1.5f);
    public static final Item MAELSTROM_CHESTPLATE = new ModArmorBase("maelstrom_chestplate", COMMON_ARMOR_MATERIAL, 1, EntityEquipmentSlot.CHEST, 1.5f);
    public static final Item MAELSTROM_LEGGINGS = new ModArmorBase("maelstrom_leggings", COMMON_ARMOR_MATERIAL, 2, EntityEquipmentSlot.LEGS, 1.5f);
    public static final Item MAELSTROM_BOOTS = new ModArmorBase("maelstrom_boots", COMMON_ARMOR_MATERIAL, 1, EntityEquipmentSlot.FEET, 1.5f);
    
    /*
     * Nexus Items
     */
    public static final Item PUMPKIN = new ItemPumpkin("pumpkin", 180, RARE_USE_TIME, null, 1f, ModCreativeTabs.ALL);
    public static final Item ELUCIDATOR = new ToolLongsword("elucidator", 4.0f, RARE_SWORD, 1.5f);
}
