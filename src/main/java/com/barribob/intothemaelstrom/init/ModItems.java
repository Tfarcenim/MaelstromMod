package com.barribob.intothemaelstrom.init;

import com.barribob.intothemaelstrom.IntoTheMaelstrom;
import com.barribob.intothemaelstrom.items.*;
import com.barribob.intothemaelstrom.items.armor.ArmorNyanHelmet;
import com.barribob.intothemaelstrom.items.armor.ArmorStrawHat;
import com.barribob.intothemaelstrom.items.armor.ItemSpeedBoots;
import com.barribob.intothemaelstrom.items.armor.ModArmorBase;
import com.barribob.intothemaelstrom.items.gun.*;
import com.barribob.intothemaelstrom.items.gun.bullet.BrownstoneCannon;
import com.barribob.intothemaelstrom.items.gun.bullet.GoldenFireball;
import com.barribob.intothemaelstrom.items.staff.*;
import com.barribob.intothemaelstrom.items.tools.*;
import com.barribob.intothemaelstrom.util.Element;
import com.barribob.intothemaelstrom.util.ModUtils;
import com.barribob.intothemaelstrom.util.handlers.LevelHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Holds all of our new items
 */
public class ModItems {
    
    //blockitems

    public static final ItemBlock MEGA_STRUCTURE_BLOCK = new ItemBlock(ModBlocks.MEGA_STRUCTURE_BLOCK);
    public static final ItemBlock LIGHTING_UPDATER = new ItemBlock(ModBlocks.LIGHTING_UPDATER);
    public static final ItemBlock DISAPPEARING_SPAWNER = new ItemBlock(ModBlocks.DISAPPEARING_SPAWNER);
    public static final ItemBlock BOSS_SPAWNER = new ItemBlock(ModBlocks.BOSS_SPAWNER);

    public static final ItemBlock MAELSTROM_CORE_BLOCK = new ItemBlock(ModBlocks.MAELSTROM_CORE_BLOCK);
    public static final ItemBlock AZURE_MAELSTROM_CORE = new ItemBlock(ModBlocks.AZURE_MAELSTROM_CORE);
    public static final ItemBlock CLIFF_MAELSTROM_CORE = new ItemBlock(ModBlocks.CLIFF_MAELSTROM_CORE);
    public static final ItemBlock AZURE_MAELSTROM = new ItemBlock(ModBlocks.AZURE_MAELSTROM);
    public static final ItemBlock DECAYING_MAELSTROM = new ItemBlock(ModBlocks.DECAYING_MAELSTROM);
    public static final ItemBlock MAELSTROM_HEART = new ItemBlock(ModBlocks.MAELSTROM_HEART);

    public static final ItemBlock NEXUS_TELEPORTER = new ItemBlock(ModBlocks.NEXUS_TELEPORTER);
    public static final ItemBlock NEXUS_PORTAL = new ItemBlock(ModBlocks.NEXUS_PORTAL);
    public static final ItemBlock DARK_NEXUS_PORTAL = new ItemBlock(ModBlocks.DARK_NEXUS_PORTAL);
    public static final ItemBlock AZURE_PORTAL = new ItemBlock(ModBlocks.AZURE_PORTAL);
    public static final ItemBlock CLIFF_PORTAL = new ItemBlock(ModBlocks.CLIFF_PORTAL);
    public static final ItemBlock CRIMSON_PORTAL = new ItemBlock(ModBlocks.CRIMSON_PORTAL);

    public static final ItemBlock BLACK_SKY = new ItemBlock(ModBlocks.BLACK_SKY);

    /**
     * Stone and ore
     */

    public static final ItemBlock DARK_AZURE_STONE = new ItemBlock(ModBlocks.DARK_AZURE_STONE);
    public static final ItemBlock AZURE_STONE_1 = new ItemBlock(ModBlocks.AZURE_STONE_1);
    public static final ItemBlock AZURE_STONE_2 = new ItemBlock(ModBlocks.AZURE_STONE_2);
    public static final ItemBlock AZURE_STONE_3 = new ItemBlock(ModBlocks.AZURE_STONE_3);
    public static final ItemBlock AZURE_STONE_4 = new ItemBlock(ModBlocks.AZURE_STONE_4);
    public static final ItemBlock AZURE_STONE_5 = new ItemBlock(ModBlocks.AZURE_STONE_5);
    public static final ItemBlock LIGHT_AZURE_STONE = new ItemBlock(ModBlocks.LIGHT_AZURE_STONE);
    public static final ItemBlock AZURE_COAL_ORE = new ItemBlock(ModBlocks.AZURE_COAL_ORE);
    public static final ItemBlock AZURE_DIAMOND_ORE = new ItemBlock(ModBlocks.AZURE_DIAMOND_ORE);
    public static final ItemBlock AZURE_EMERALD_ORE = new ItemBlock(ModBlocks.AZURE_EMERALD_ORE);
    public static final ItemBlock AZURE_LAPIS_ORE = new ItemBlock(ModBlocks.AZURE_LAPIS_ORE);
    public static final ItemBlock AZURE_IRON_ORE = new ItemBlock(ModBlocks.AZURE_IRON_ORE);
    public static final ItemBlock AZURE_GOLD_ORE = new ItemBlock(ModBlocks.AZURE_GOLD_ORE);
    public static final ItemBlock AZURE_REDSTONE_ORE = new ItemBlock(ModBlocks.AZURE_REDSTONE_ORE);
    public static final ItemBlock AZURE_LIT_REDSTONE_ORE = new ItemBlock(ModBlocks.AZURE_LIT_REDSTONE_ORE);
    public static final ItemBlock CHASMIUM_ORE = new ItemBlock(ModBlocks.CHASMIUM_ORE);
    public static final ItemBlock CLIFF_STONE = new ItemBlock(ModBlocks.CLIFF_STONE);
    public static final ItemBlock RED_CLIFF_STONE = new ItemBlock(ModBlocks.RED_CLIFF_STONE);

    /**
     * Organic blocks
     */

    public static final ItemBlock AZURE_GRASS = new ItemBlock(ModBlocks.AZURE_GRASS);
    public static final ItemBlock AZURE_VINES_BLOCK = new ItemBlock(ModBlocks.AZURE_VINES_BLOCK);
    public static final ItemBlock AZURE_VINES = new ItemBlock(ModBlocks.AZURE_VINES);
    public static final ItemBlock BLUE_DAISY = new ItemBlock(ModBlocks.BLUE_DAISY);
    public static final ItemBlock RUBY_ORCHID = new ItemBlock(ModBlocks.RUBY_ORCHID);
    public static final ItemBlock BROWNED_GRASS = new ItemBlock(ModBlocks.BROWNED_GRASS);
    public static final ItemBlock DOUBLE_BROWNED_GRASS = new ItemBlock(ModBlocks.DOUBLE_BROWNED_GRASS);
    public static final ItemBlock FIRE_GRASS = new ItemBlock(ModBlocks.FIRE_GRASS);

    public static final ItemBlock AZURE_LOG = new ItemBlock(ModBlocks.AZURE_LOG);
    public static final ItemBlock PLUM_LOG = new ItemBlock(ModBlocks.PLUM_LOG);
    public static final ItemBlock SWAMP_LOG = new ItemBlock(ModBlocks.SWAMP_LOG);
    public static final ItemBlock FULL_SWAMP_LOG = new ItemBlock(ModBlocks.FULL_SWAMP_LOG);

    public static final ItemBlock AZURE_LEAVES = new ItemBlock(ModBlocks.AZURE_LEAVES);
    public static final ItemBlock PLUM_LEAVES = new ItemBlock(ModBlocks.PLUM_LEAVES);
    public static final ItemBlock PLUM_FILLED_LEAVES = new ItemBlock(ModBlocks.PLUM_FILLED_LEAVES);
    public static final ItemBlock SWAMP_LEAVES = new ItemBlock(ModBlocks.SWAMP_LEAVES);

    public static final ItemBlock AZURE_SAPLING = new ItemBlock(ModBlocks.AZURE_SAPLING);
    public static final ItemBlock PLUM_SAPLING = new ItemBlock(ModBlocks.PLUM_SAPLING);
    public static final ItemBlock LARGE_PLUM_SAPLING = new ItemBlock(ModBlocks.LARGE_PLUM_SAPLING);
    public static final ItemBlock SWAMP_SAPLING = new ItemBlock(ModBlocks.SWAMP_SAPLING);

    public static final ItemBlock AZURE_PLANKS = new ItemBlock(ModBlocks.AZURE_PLANKS);
    public static final ItemBlock AZURE_FENCE = new ItemBlock(ModBlocks.AZURE_FENCE);
    public static final ItemBlock AZURE_PLANK_STAIRS = new ItemBlock(ModBlocks.AZURE_PLANK_STAIRS);
    public static final ItemBlock SWAMP_PLANKS = new ItemBlock(ModBlocks.SWAMP_PLANKS);
    public static final ItemBlock SWAMP_PLANK_STAIRS = new ItemBlock(ModBlocks.SWAMP_PLANK_STAIRS);
    public static final ItemBlock SWAMP_FENCE = new ItemBlock(ModBlocks.SWAMP_FENCE);

    /**
     * Blocks for structures
     */

    public static final ItemBlock MAELSTROM_BRICKS = new ItemBlock(ModBlocks.MAELSTROM_BRICKS);
    public static final ItemBlock MAELSTROM_BRICK_FENCE = new ItemBlock(ModBlocks.MAELSTROM_BRICK_FENCE);
    public static final ItemBlock MAELSTROM_BRICK_STAIRS = new ItemBlock(ModBlocks.MAELSTROM_BRICK_STAIRS);

    public static final ItemBlock MAELSTROM_STONEBRICK = new ItemBlock(ModBlocks.MAELSTROM_STONEBRICK);
    public static final ItemBlock MAELSTROM_STONEBRICK_STAIRS = new ItemBlock(ModBlocks.MAELSTROM_STONEBRICK_STAIRS);
    public static final ItemBlock MAELSTROM_STONEBRICK_FENCE = new ItemBlock(ModBlocks.MAELSTROM_STONEBRICK_FENCE);

    public static final ItemBlock AZURE_STONEBRICK = new ItemBlock(ModBlocks.AZURE_STONEBRICK);
    public static final ItemBlock AZURE_STONEBRICK_STAIRS = new ItemBlock(ModBlocks.AZURE_STONEBRICK_STAIRS);
    public static final ItemBlock AZURE_STONEBRICK_CRACKED = new ItemBlock(ModBlocks.AZURE_STONEBRICK_CRACKED);
    public static final ItemBlock AZURE_STONEBRICK_CARVED = new ItemBlock(ModBlocks.AZURE_STONEBRICK_CARVED);
    public static final ItemBlock AZURE_STONEBRICK_CARVED_2 = new ItemBlock(ModBlocks.AZURE_STONEBRICK_CARVED_2);
    public static final ItemBlock AZURE_STONEBRICK_CARVED_3 = new ItemBlock(ModBlocks.AZURE_STONEBRICK_CARVED_3);
    public static final ItemBlock AZURE_STONEBRICK_LIT = new ItemBlock(ModBlocks.AZURE_STONEBRICK_LIT);

    public static final ItemBlock GOLD_STONE = new ItemBlock(ModBlocks.GOLD_STONE);
    public static final ItemBlock CRACKED_GOLD_STONE = new ItemBlock(ModBlocks.CRACKED_GOLD_STONE);
    public static final ItemBlock GOLD_STONE_FENCE = new ItemBlock(ModBlocks.GOLD_STONE_FENCE);
    public static final ItemBlock GOLD_STONE_STAIRS = new ItemBlock(ModBlocks.GOLD_STONE_STAIRS);
    public static final ItemBlock BROWNED_PILLAR = new ItemBlock(ModBlocks.BROWNED_PILLAR);
    public static final ItemBlock BROWNED_BLOCK = new ItemBlock(ModBlocks.BROWNED_BLOCK);
    public static final ItemBlock GOLD_STONE_LAMP = new ItemBlock(ModBlocks.GOLD_STONE_LAMP);
    public static final ItemBlock CHISELED_CLIFF_STONE = new ItemBlock(ModBlocks.CHISELED_CLIFF_STONE);
    public static final ItemBlock SWAMP_BRICK = new ItemBlock(ModBlocks.SWAMP_BRICK);
    public static final ItemBlock CRACKED_SWAMP_BRICK = new ItemBlock(ModBlocks.CRACKED_SWAMP_BRICK);

    public static final ItemBlock CRIMSON_MAELSTROM_BRICKS = new ItemBlock(ModBlocks.CRIMSON_MAELSTROM_BRICKS);
    public static final ItemBlock CRIMSON_MAELSTROM_BRICK_FENCE = new ItemBlock(ModBlocks.CRIMSON_MAELSTROM_BRICK_FENCE);
    public static final ItemBlock CRIMSON_MAELSTROM_BRICK_STAIRS =new ItemBlock(ModBlocks.CRIMSON_MAELSTROM_BRICK_STAIRS);
    public static final ItemBlock CRIMSON_LAMP = new ItemBlock(ModBlocks.CRIMSON_LAMP);
    public static final ItemBlock CHAIN = new ItemBlock(ModBlocks.CHAIN);
    public static final ItemBlock MULTI_CHAIN = new ItemBlock(ModBlocks.MULTI_CHAIN);
    public static final ItemBlock LARGE_CHAIN = new ItemBlock(ModBlocks.LARGE_CHAIN);
    public static final ItemBlock METAL_LAMP = new ItemBlock(ModBlocks.METAL_LAMP);
    public static final ItemBlock FURNACE_BRICKS = new ItemBlock(ModBlocks.FURNACE_BRICKS);
    public static final ItemBlock FURNACE_STAIRS = new ItemBlock(ModBlocks.FURNACE_STAIRS);
    public static final ItemBlock CRACKED_FURNACE_BRICKS = new ItemBlock(ModBlocks.CRACKED_FURNACE_BRICKS);
    public static final ItemBlock FURNACE_BRICKS_LIT = new ItemBlock(ModBlocks.FURNACE_BRICKS_LIT);

    /*
     * Key blocks and nexus stuff
     */

    public static final ItemBlock CRACKED_QUARTZ = new ItemBlock(ModBlocks.CRACKED_QUARTZ);
    public static final ItemBlock AZURE_KEY_BLOCK = new ItemBlock(ModBlocks.AZURE_KEY_BLOCK);
    public static final ItemBlock MAELSTROM_DUNGEON_KEY_BLOCK = new ItemBlock(ModBlocks.MAELSTROM_DUNGEON_KEY_BLOCK);
    public static final ItemBlock BROWN_KEY_BLOCK = new ItemBlock(ModBlocks.BROWN_KEY_BLOCK);
    public static final ItemBlock RED_DUNGEON_KEY_BLOCK = new ItemBlock(ModBlocks.RED_DUNGEON_KEY_BLOCK);
    public static final ItemBlock ICE_KEY_BLOCK = new ItemBlock(ModBlocks.ICE_KEY_BLOCK);
    public static final ItemBlock ICE_DUNGEON_KEY_BLOCK = new ItemBlock(ModBlocks.ICE_DUNGEON_KEY_BLOCK);
    public static final ItemBlock BLACK_DUNGEON_KEY_BLOCK = new ItemBlock(ModBlocks.BLACK_DUNGEON_KEY_BLOCK);

    /*
     * Crimson dimension
     */

    public static final ItemBlock FURNACE_PILLAR = new ItemBlock(ModBlocks.FURNACE_PILLAR);
    public static final ItemBlock REDSTONE_BRICK = new ItemBlock(ModBlocks.REDSTONE_BRICK);
    public static final ItemBlock CRACKED_REDSTONE_BRICK = new ItemBlock(ModBlocks.CRACKED_REDSTONE_BRICK);
    public static final ItemBlock IRON_GRATE = new ItemBlock(ModBlocks.IRON_GRATE);
    public static final ItemBlock FAN = new ItemBlock(ModBlocks.FAN);
    
    public static final float BASE_MELEE_DAMAGE = 6;
    
    // There are technically not blocks, but are in here because they depend on two of the block defined above
    public static final Item STONEBRICK_BLOCKVOID = new ItemBlockvoid(Blocks.STONEBRICK, 30);
    public static final Item OBSIDIAN_BLOCKVOID = new ItemBlockvoid(Blocks.OBSIDIAN, 1000);
    public static final Item FURNACE_BRICKS_BLOCKVOID = new ItemBlockvoid(ModBlocks.FURNACE_BRICKS, 30);
    public static final Item REDSTONE_BRICK_BLOCKVOID = new ItemBlockvoid(ModBlocks.REDSTONE_BRICK, 30);

    public static final int GUN_USE_TIME = 12000;
    public static final int STAFF_USE_TIME = 9000;

    public static final Set<Item> ITEMS = new HashSet<>();

    static Consumer<List<String>> kanshouBakuya = (tooltip) -> {
        if (IntoTheMaelstrom.itemsConfig.getBoolean("full_set_bonuses.kanshou_bakuya")) {
            tooltip.add(TextFormatting.GRAY + ModUtils.translateDesc("kanshou_bakuya"));
        }
    };

    /*
     * Dimensional Items
     */
    public static final Item AZURE_KEY = new ItemKey("dimensional_key");
    public static final Item BROWN_KEY = new ItemKey("dimensional_key");
    public static final Item MAELSTROM_KEY = new Item();
    public static final Item RED_KEY = new ItemKey("dimensional_key");

    public static final Item CLIFF_KEY_FRAGMENT = new ItemSingleDescription("cliff_key_fragment").setCreativeTab(ModCreativeTabs.ITEMS);
    public static final Item RED_KEY_FRAGMENT = new ItemSingleDescription("red_key_fragment").setCreativeTab(ModCreativeTabs.ITEMS);
    public static final Item MAELSTROM_KEY_FRAGMENTS = new ItemSingleDescription("maelstrom_key_fragments").setCreativeTab(ModCreativeTabs.ITEMS);

    public static final Item MAELSTROM_CORE = new ItemSingleDescription("Used for Trading").setCreativeTab(ModCreativeTabs.ITEMS);
    public static final Item AZURE_MAELSTROM_CORE_CRYSTAL = new ItemSingleDescription("azure_maelstrom_core_crystal").setCreativeTab(ModCreativeTabs.ITEMS);
    public static final Item GOLDEN_MAELSTROM_CORE = new ItemSingleDescription("golden_maelstrom_core").setCreativeTab(ModCreativeTabs.ITEMS);
    public static final Item CRIMSON_MAELSTROM_CORE = new ItemSingleDescription("crimson_maelstrom_core").setCreativeTab(ModCreativeTabs.ITEMS);
    public static final Item MAELSTROM_FRAGMENT = new Item().setCreativeTab(ModCreativeTabs.ITEMS);
    public static final Item AZURE_MAELSTROM_FRAGMENT = new Item().setCreativeTab(ModCreativeTabs.ITEMS);
    public static final Item GOLDEN_MAELSTROM_FRAGMENT = new ItemSingleDescription("golden_maelstrom_fragment").setCreativeTab(ModCreativeTabs.ITEMS);
    public static final Item CRIMSON_MAELSTROM_FRAGMENT = new ItemSingleDescription("crimson_maelstrom_fragment").setCreativeTab(ModCreativeTabs.ITEMS);

    // The azure dimension's items
    public static final Item ELK_HIDE = new ItemSingleDescription("elk_hide").setCreativeTab(ModCreativeTabs.ITEMS);
    public static final Item ELK_STRIPS = new ItemFood(3, 0.3F, true).setCreativeTab(ModCreativeTabs.ITEMS);
    public static final Item ELK_JERKY = new ItemFood(8, 1.0F, true).setCreativeTab(ModCreativeTabs.ITEMS);
    public static final Item PLUM = new ItemFood(4, 0.3F, true).setCreativeTab(ModCreativeTabs.ITEMS);
    public static final Item IRON_PELLET = new Item();
    public static final Item MAELSTROM_PELLET = new Item();
    public static final Item CHASMIUM_INGOT = new ItemSingleDescription("chasmium_ingot").setCreativeTab(ModCreativeTabs.ITEMS);
    public static final Item CATALYST = new ItemCatalyst().setCreativeTab(ModCreativeTabs.ITEMS);
    public static final Item MINOTAUR_HORN = new ItemSingleDescription("minotaur_horn").setCreativeTab(ModCreativeTabs.ITEMS);

    /**
     * Guns
     */

    public static final Item FLINTLOCK = new ItemFlintlock(LevelHandler.INVASION);
    public static final Item BOOMSTICK = new ItemBoomstick(LevelHandler.AZURE_OVERWORLD);
    public static final Item MUSKET = new ItemMusket(LevelHandler.AZURE_OVERWORLD);
    public static final Item REPEATER = new ItemRepeater(LevelHandler.AZURE_OVERWORLD);
    public static final Item RIFLE = new ItemRifle(LevelHandler.AZURE_OVERWORLD).setInformation((tooltip) -> {
        tooltip.add(TextFormatting.GRAY + ModUtils.translateDesc("rifle"));
    });
    public static final Item ELK_BLASTER = new ItemPiercer(LevelHandler.AZURE_ENDGAME).setElement(Element.AZURE);
    public static final Item PUMPKIN = new ItemPumpkinGun(80, LevelHandler.AZURE_ENDGAME);
    public static final Item GOLDEN_FLINTLOCK = new ItemFlintlock(LevelHandler.CLIFF_ENDGAME).setElement(Element.GOLDEN);
    public static final Item GOLDEN_REPEATER = new ItemRepeater(LevelHandler.CLIFF_ENDGAME).setElement(Element.GOLDEN);
    public static final Item GOLDEN_SHOTGUN = new ItemBoomstick(LevelHandler.CLIFF_ENDGAME).setElement(Element.GOLDEN);
    public static final Item GOLDEN_RIFLE = new ItemRifle(LevelHandler.CLIFF_ENDGAME).setElement(Element.GOLDEN);

    public static final Item ENERGIZED_PISTOL = new ItemFlintlock(LevelHandler.CRIMSON_START).setElement(Element.CRIMSON);
    public static final Item ENERGIZED_REPEATER = new ItemRepeater(LevelHandler.CRIMSON_START).setElement(Element.CRIMSON);
    public static final Item ENERGIZED_SHOTGUN = new ItemBoomstick(LevelHandler.CRIMSON_START).setElement(Element.CRIMSON);
    public static final Item ENERGIZED_PIERCER = new ItemPiercer(LevelHandler.CRIMSON_START).setElement(Element.CRIMSON);
    public static final Item ENERGIZED_MUSKET = new ItemMusket(LevelHandler.CRIMSON_START).setElement(Element.CRIMSON);

    /**
     * Staves
     */

    public static final Item MAELSTROM_CANNON = new ItemMaelstromCannon(STAFF_USE_TIME, LevelHandler.AZURE_ENDGAME);
    public static final Item WILL_O_THE_WISP_STAFF = new ItemWispStaff(STAFF_USE_TIME, LevelHandler.AZURE_ENDGAME);
    public static final Item QUAKE_STAFF = new ItemQuakeStaff(STAFF_USE_TIME, LevelHandler.AZURE_OVERWORLD);
    public static final Item LEAP_STAFF = new ItemLeapStaff(STAFF_USE_TIME, LevelHandler.AZURE_ENDGAME);
    public static final Item SPEED_STAFF = new ItemSpeedStaff(STAFF_USE_TIME, LevelHandler.AZURE_ENDGAME);
    public static final Item FIREBALL_STAFF = new ItemFireballStaff(STAFF_USE_TIME, LevelHandler.AZURE_ENDGAME);
    public static final Item CROSS_OF_AQUA = (new Item() {
        @Override
        public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
            tooltip.add(TextFormatting.BLUE + "When held, allows the player to walk on water");
            tooltip.add(TextFormatting.GRAY + "Mana cost: " + TextFormatting.DARK_PURPLE + "0.5" + TextFormatting.GRAY + " per second");
        }
    }).setMaxStackSize(1);
    public static final Item BROWNSTONE_CANNON = new ItemMaelstromCannon(STAFF_USE_TIME, LevelHandler.CLIFF_OVERWORLD).setFactory(new BrownstoneCannon());
    public static final Item METEOR_STAFF = new ItemMeteorStaff(STAFF_USE_TIME, LevelHandler.CLIFF_OVERWORLD);
    public static final Item GOLDEN_QUAKE_STAFF = new ItemQuakeStaff(STAFF_USE_TIME, LevelHandler.CLIFF_OVERWORLD).setElement(Element.GOLDEN);
    public static final Item EXPLOSIVE_STAFF = new ItemExplosiveStaff(STAFF_USE_TIME, LevelHandler.CLIFF_ENDGAME);
    public static final Item GOLDEN_FIREBALL_STAFF = new ItemFireballStaff(STAFF_USE_TIME, LevelHandler.CLIFF_ENDGAME).setFactory(new GoldenFireball()).setElement(Element.GOLDEN);
    public static final Item CRIMSON_RUNE_STAFF = new ItemRuneStaff(LevelHandler.CRIMSON_START).setElement(Element.CRIMSON);
    public static final Item ENERGIZED_CADUCEUS = new ItemPotionEffectStaff(LevelHandler.CRIMSON_END,
            () -> new PotionEffect[]{new PotionEffect(MobEffects.REGENERATION, 150, 3), new PotionEffect(MobEffects.ABSORPTION, 2400, 4)}, "energized_caduceus");
    public static final Item TUNING_FORK = new ItemTuningFork(LevelHandler.CRIMSON_END).setElement(Element.CRIMSON);

    /**
     * Melee
     */

    public static final Item VENOM_DAGGER = new ToolVenomDagger("venom_dagger", Materials.DAGGER, LevelHandler.INVASION);
    public static final Item NEXUS_BATTLEAXE = new ToolBattleaxe(Materials.BATTLEAXE, LevelHandler.AZURE_OVERWORLD);
    public static final Item CHASMIUM_SWORD = new ToolSword(Materials.SWORD, LevelHandler.AZURE_OVERWORLD);
    public static final Item SWORD_OF_SHADES = new ToolLongsword(Materials.SWORD, LevelHandler.AZURE_ENDGAME);
    public static final Item SHADOW_DAGGER = new ToolDagger(Materials.DAGGER, LevelHandler.AZURE_ENDGAME);
    public static final Item MAELSTROM_BATTLEAXE = new ToolBattleaxe(Materials.BATTLEAXE, LevelHandler.AZURE_ENDGAME);
    public static final Item FROST_SWORD = new ToolFrostSword("frost_sword", Materials.SWORD, LevelHandler.AZURE_ENDGAME);
    public static final Item BEAST_BLADE = new ToolSword(Materials.SWORD, LevelHandler.AZURE_ENDGAME);
    public static final Item ELUCIDATOR = new ToolLongsword(Materials.SWORD, LevelHandler.AZURE_ENDGAME);
    public static final Item DRAGON_SLAYER = new ToolDragonslayer(Materials.BATTLEAXE, LevelHandler.AZURE_ENDGAME);
    public static final Item ANCIENT_BATTLEAXE = new ToolDragonslayer(Materials.BATTLEAXE, LevelHandler.CLIFF_OVERWORLD);
    public static final Item BROWNSTONE_SWORD = new ToolSword(Materials.SWORD, LevelHandler.CLIFF_OVERWORLD);
    public static final Item CRUSADE_SWORD = new ToolCrusadeSword(Materials.SWORD, LevelHandler.CLIFF_OVERWORLD);
    public static final Item MAGISTEEL_SWORD = new ItemMagisteelSword(Materials.SWORD, LevelHandler.CLIFF_OVERWORLD, Element.NONE);
    public static final Item GOLD_STONE_LONGSWORD = new ToolLongsword(Materials.SWORD, LevelHandler.CLIFF_ENDGAME);
    public static final Item BLACK_GOLD_SWORD = new ToolSword(Materials.SWORD, LevelHandler.CLIFF_ENDGAME, Element.GOLDEN);
    public static final Item KANSHOU = new ToolDagger(Materials.DAGGER, LevelHandler.CLIFF_ENDGAME).setInformation(kanshouBakuya);
    public static final Item BAKUYA = new ToolDagger(Materials.DAGGER, LevelHandler.CLIFF_ENDGAME).setInformation(kanshouBakuya);
    public static final Item EXPLOSIVE_DAGGER = new ToolExplosiveDagger("explosive_dagger", Materials.DAGGER, LevelHandler.CLIFF_ENDGAME);
    public static final Item ENERGETIC_STEEL_SWORD = new ItemMagisteelSword(Materials.SWORD, LevelHandler.CRIMSON_START, Element.CRIMSON);
    public static final Item ENERGETIC_STEEL_CLEAVER = new ToolDragonslayer(Materials.BATTLEAXE, LevelHandler.CRIMSON_START).setElement(Element.CRIMSON);
    public static final Item FADESTEEL_SWORD = new ToolSword(Materials.SWORD, LevelHandler.CRIMSON_START);
    public static final Item HOMURAMARU = new ToolSword(Materials.SWORD, LevelHandler.CRIMSON_START);
    public static final Item BLACK_MARCH = new ToolDagger(Materials.DAGGER, LevelHandler.CRIMSON_END);

    /*
     * Armors
     */

    public static final Item STRAW_HAT = new ArmorStrawHat("straw_hat", Materials.ARMOR, 1, EntityEquipmentSlot.HEAD, LevelHandler.INVASION, "straw_hat.png");
    public static final Item SPEED_BOOTS = new ItemSpeedBoots("speed_boots", Materials.ARMOR, 1, EntityEquipmentSlot.FEET, LevelHandler.AZURE_OVERWORLD, "speed");

    public static final Item NEXUS_HELMET = new ModArmorBase("nexus_helmet", Materials.ARMOR, 1, EntityEquipmentSlot.HEAD, LevelHandler.INVASION, "nexus");
    public static final Item NEXUS_CHESTPLATE = new ModArmorBase("nexus_chestplate", Materials.ARMOR, 1, EntityEquipmentSlot.CHEST, LevelHandler.INVASION, "nexus");
    public static final Item NEXUS_LEGGINGS = new ModArmorBase("nexus_leggings", Materials.ARMOR, 2, EntityEquipmentSlot.LEGS, LevelHandler.INVASION, "nexus");
    public static final Item NEXUS_BOOTS = new ModArmorBase("nexus_boots", Materials.ARMOR, 1, EntityEquipmentSlot.FEET, LevelHandler.INVASION, "nexus");

    public static final Item ELK_HIDE_HELMET = new ModArmorBase("elk_hide_helmet", Materials.ARMOR, 1, EntityEquipmentSlot.HEAD, LevelHandler.AZURE_OVERWORLD, "elk_hide").setElement(Element.AZURE);
    public static final Item ELK_HIDE_CHESTPLATE = new ModArmorBase("elk_hide_chestplate", Materials.ARMOR, 1, EntityEquipmentSlot.CHEST, LevelHandler.AZURE_OVERWORLD, "elk_hide").setElement(Element.AZURE);
    public static final Item ELK_HIDE_LEGGINGS = new ModArmorBase("elk_hide_leggings", Materials.ARMOR, 2, EntityEquipmentSlot.LEGS, LevelHandler.AZURE_OVERWORLD, "elk_hide").setElement(Element.AZURE);
    public static final Item ELK_HIDE_BOOTS = new ModArmorBase("elk_hide_boots", Materials.ARMOR, 1, EntityEquipmentSlot.FEET, LevelHandler.AZURE_OVERWORLD, "elk_hide").setElement(Element.AZURE);

    public static final Item CHASMIUM_HELMET = new ModArmorBase("chasmium_helmet", Materials.ARMOR, 1, EntityEquipmentSlot.HEAD, LevelHandler.AZURE_OVERWORLD, "chasmium");
    public static final Item CHASMIUM_CHESTPLATE = new ModArmorBase("chasmium_chestplate", Materials.ARMOR, 1, EntityEquipmentSlot.CHEST, LevelHandler.AZURE_OVERWORLD, "chasmium");
    public static final Item CHASMIUM_LEGGINGS = new ModArmorBase("chasmium_leggings", Materials.ARMOR, 2, EntityEquipmentSlot.LEGS, LevelHandler.AZURE_OVERWORLD, "chasmium");
    public static final Item CHASMIUM_BOOTS = new ModArmorBase("chasmium_boots", Materials.ARMOR, 1, EntityEquipmentSlot.FEET, LevelHandler.AZURE_OVERWORLD, "chasmium");

    public static final Item MAELSTROM_HELMET = new ModArmorBase("maelstrom_helmet", Materials.ARMOR, 1, EntityEquipmentSlot.HEAD, LevelHandler.AZURE_ENDGAME, "maelstrom").setArmorBonusDesc("maelstrom_full_set");
    public static final Item MAELSTROM_CHESTPLATE = new ModArmorBase("maelstrom_chestplate", Materials.ARMOR, 1, EntityEquipmentSlot.CHEST, LevelHandler.AZURE_ENDGAME, "maelstrom").setArmorBonusDesc("maelstrom_full_set");
    public static final Item MAELSTROM_LEGGINGS = new ModArmorBase("maelstrom_leggings", Materials.ARMOR, 2, EntityEquipmentSlot.LEGS, LevelHandler.AZURE_ENDGAME, "maelstrom").setArmorBonusDesc("maelstrom_full_set");
    public static final Item MAELSTROM_BOOTS = new ModArmorBase("maelstrom_boots", Materials.ARMOR, 1, EntityEquipmentSlot.FEET, LevelHandler.AZURE_ENDGAME, "maelstrom").setArmorBonusDesc("maelstrom_full_set");

    public static final Item SWAMP_HELMET = new ModArmorBase("swamp_helmet", Materials.ARMOR, 1, EntityEquipmentSlot.HEAD, LevelHandler.CLIFF_OVERWORLD, "swamp").setArmorBonusDesc("swamp_full_set");
    public static final Item SWAMP_CHESTPLATE = new ModArmorBase("swamp_chestplate", Materials.ARMOR, 1, EntityEquipmentSlot.CHEST, LevelHandler.CLIFF_OVERWORLD, "swamp").setArmorBonusDesc("swamp_full_set");
    public static final Item SWAMP_LEGGINGS = new ModArmorBase("swamp_leggings", Materials.ARMOR, 2, EntityEquipmentSlot.LEGS, LevelHandler.CLIFF_OVERWORLD, "swamp").setArmorBonusDesc("swamp_full_set");
    public static final Item SWAMP_BOOTS = new ModArmorBase("swamp_boots", Materials.ARMOR, 1, EntityEquipmentSlot.FEET, LevelHandler.CLIFF_OVERWORLD, "swamp").setArmorBonusDesc("swamp_full_set");

    public static final Item GOLTOX_HELMET = new ModArmorBase("goltox_helmet", Materials.ARMOR, 1, EntityEquipmentSlot.HEAD, LevelHandler.CLIFF_OVERWORLD, "goltox").setElement(Element.GOLDEN).setArmorBonusDesc("goltox_full_set");
    public static final Item GOLTOX_CHESTPLATE = new ModArmorBase("goltox_chestplate", Materials.ARMOR, 1, EntityEquipmentSlot.CHEST, LevelHandler.CLIFF_OVERWORLD, "goltox").setElement(Element.GOLDEN).setArmorBonusDesc("goltox_full_set");
    public static final Item GOLTOX_LEGGINGS = new ModArmorBase("goltox_leggings", Materials.ARMOR, 2, EntityEquipmentSlot.LEGS, LevelHandler.CLIFF_OVERWORLD, "goltox").setElement(Element.GOLDEN).setArmorBonusDesc("goltox_full_set");
    public static final Item GOLTOX_BOOTS = new ModArmorBase("goltox_boots", Materials.ARMOR, 1, EntityEquipmentSlot.FEET, LevelHandler.CLIFF_OVERWORLD, "goltox").setElement(Element.GOLDEN).setArmorBonusDesc("goltox_full_set");

    public static final Item NYAN_HELMET = new ArmorNyanHelmet("nyan_helmet", Materials.ARMOR, 1, EntityEquipmentSlot.HEAD, LevelHandler.CLIFF_ENDGAME, "nyan_helmet.png").setArmorBonusDesc("nyan_full_set");
    public static final Item NYAN_CHESTPLATE = new ModArmorBase("nyan_chestplate", Materials.ARMOR, 1, EntityEquipmentSlot.CHEST, LevelHandler.CLIFF_ENDGAME, "nyan").setArmorBonusDesc("nyan_full_set");
    public static final Item NYAN_LEGGINGS = new ModArmorBase("nyan_leggings", Materials.ARMOR, 2, EntityEquipmentSlot.LEGS, LevelHandler.CLIFF_ENDGAME, "nyan").setArmorBonusDesc("nyan_full_set");
    public static final Item NYAN_BOOTS = new ModArmorBase("nyan_boots", Materials.ARMOR, 1, EntityEquipmentSlot.FEET, LevelHandler.CLIFF_ENDGAME, "nyan").setArmorBonusDesc("nyan_full_set");

    public static final Item BLACK_GOLD_HELMET = new ModArmorBase("black_gold_helmet", Materials.ARMOR, 1, EntityEquipmentSlot.HEAD, LevelHandler.CLIFF_ENDGAME, "black_gold").setElement(Element.GOLDEN).setArmorBonusDesc("black_gold_full_set");
    public static final Item BLACK_GOLD_CHESTPLATE = new ModArmorBase("black_gold_chestplate", Materials.ARMOR, 1, EntityEquipmentSlot.CHEST, LevelHandler.CLIFF_ENDGAME, "black_gold").setElement(Element.GOLDEN).setArmorBonusDesc("black_gold_full_set");
    public static final Item BLACK_GOLD_LEGGINGS = new ModArmorBase("black_gold_leggings", Materials.ARMOR, 2, EntityEquipmentSlot.LEGS, LevelHandler.CLIFF_ENDGAME, "black_gold").setElement(Element.GOLDEN).setArmorBonusDesc("black_gold_full_set");
    public static final Item BLACK_GOLD_BOOTS = new ModArmorBase("black_gold_boots", Materials.ARMOR, 1, EntityEquipmentSlot.FEET, LevelHandler.CLIFF_ENDGAME, "black_gold").setElement(Element.GOLDEN).setArmorBonusDesc("black_gold_full_set");

    public static final Item ENERGETIC_STEEL_HELMET =
            new ModArmorBase("energetic_steel_helmet", Materials.ARMOR, 1, EntityEquipmentSlot.HEAD, LevelHandler.CRIMSON_START, "energetic_steel").setElement(Element.CRIMSON).setArmorBonusDesc("energetic_steel_full_set");
    public static final Item ENERGETIC_STEEL_CHESTPLATE =
            new ModArmorBase("energetic_steel_chestplate", Materials.ARMOR, 1, EntityEquipmentSlot.CHEST, LevelHandler.CRIMSON_START, "energetic_steel").setElement(Element.CRIMSON).setArmorBonusDesc("energetic_steel_full_set");
    public static final Item ENERGETIC_STEEL_LEGGINGS =
            new ModArmorBase("energetic_steel_leggings", Materials.ARMOR, 2, EntityEquipmentSlot.LEGS, LevelHandler.CRIMSON_START, "energetic_steel").setElement(Element.CRIMSON).setArmorBonusDesc("energetic_steel_full_set");
    public static final Item ENERGETIC_STEEL_BOOTS =
            new ModArmorBase("energetic_steel_boots", Materials.ARMOR, 1, EntityEquipmentSlot.FEET, LevelHandler.CRIMSON_START, "energetic_steel").setElement(Element.CRIMSON).setArmorBonusDesc("energetic_steel_full_set");

    public static final Item FADESTEEL_HELMET = new ModArmorBase("fadesteel_helmet", Materials.ARMOR, 1, EntityEquipmentSlot.HEAD, LevelHandler.CRIMSON_START, "fadesteel").setArmorBonusDesc("fadesteel_full_set");
    public static final Item FADESTEEL_CHESTPLATE = new ModArmorBase("fadesteel_chestplate", Materials.ARMOR, 1, EntityEquipmentSlot.CHEST, LevelHandler.CRIMSON_START, "fadesteel").setArmorBonusDesc("fadesteel_full_set");
    public static final Item FADESTEEL_LEGGINGS = new ModArmorBase("fadesteel_leggings", Materials.ARMOR, 2, EntityEquipmentSlot.LEGS, LevelHandler.CRIMSON_START, "fadesteel").setArmorBonusDesc("fadesteel_full_set");
    public static final Item FADESTEEL_BOOTS = new ModArmorBase("fadesteel_boots", Materials.ARMOR, 1, EntityEquipmentSlot.FEET, LevelHandler.CRIMSON_START, "fadesteel").setArmorBonusDesc("fadesteel_full_set");

    public static final Item ELYSIUM_HELMET = new ModArmorBase("elysium_helmet", Materials.ARMOR, 1, EntityEquipmentSlot.HEAD, LevelHandler.CRIMSON_END, "elysium").setArmorBonusDesc("elysium_full_set");
    public static final Item ELYSIUM_CHESTPLATE = new ModArmorBase("elysium_chestplate", Materials.ARMOR, 1, EntityEquipmentSlot.CHEST, LevelHandler.CRIMSON_END, "elysium").setArmorBonusDesc("elysium_full_set");
    public static final Item ELYSIUM_LEGGINGS = new ModArmorBase("elysium_leggings", Materials.ARMOR, 2, EntityEquipmentSlot.LEGS, LevelHandler.CRIMSON_END, "elysium").setArmorBonusDesc("elysium_full_set");
    public static final Item ELYSIUM_BOOTS = new ModArmorBase("elysium_boots", Materials.ARMOR, 1, EntityEquipmentSlot.FEET, LevelHandler.CRIMSON_END, "elysium").setArmorBonusDesc("elysium_full_set");

    public static final Item AMMO_CASE = new ItemAmmoCase(LevelHandler.INVASION);
    public static final Item CHASMIUM_AMMO_CASE = new ItemAmmoCase(LevelHandler.AZURE_OVERWORLD);
    public static final Item BLACK_GOLD_AMMO_CASE = new ItemAmmoCase(LevelHandler.CLIFF_ENDGAME);
    public static final Item CRIMSON_AMMO_CASE = new ItemAmmoCase(LevelHandler.CRIMSON_START);

    /*
     * Cliff Dimension Items
     */
    public static final Item GOLD_PELLET = new Item();
    public static final Item SWAMP_SLIME = new ItemSingleDescription("swamp_slime");
    public static final Item FLY_WINGS = new ItemSingleDescription("fly_wings");

    /**
     * Crimson Dimension Items
     */

    public static final Item ENERGETIC_STEEL_PICKAXE = new ToolPickaxe("energetic_steel_pickaxe", Materials.ENERGETIC_PICKAXE);
    public static final Item CRIMSON_PELLET = new Item();
    public static final Item ELYSIUM_WINGS = new ItemModElytra(Materials.ARMOR);

    /**
     * Random
     */

    // The sound events are unregistered because they are null at the point in the loading procedure
    public static final Item NEW_WORLD_RECORD = new ItemModRecord("music_disc_new_world", new SoundEvent(new ResourceLocation(IntoTheMaelstrom.MOD_ID, "music.new_world")));
    public static final Item WANDERING_RECORD = new ItemModRecord("music_disc_wandering", new SoundEvent(new ResourceLocation(IntoTheMaelstrom.MOD_ID, "music.wandering")));


    public static void register(RegistryEvent.Register<Item> event) {
        for (Field field : ModItems.class.getFields()) {
            try {
                Object value = field.get(null);
                if (value instanceof Item) {
                    Item item = (Item) value;
                    if (item.getRegistryName() == null) {
                        String name = field.getName().toLowerCase(Locale.ROOT);
                        item.setRegistryName(name).setUnlocalizedName(IntoTheMaelstrom.MOD_ID + "." + name);
                    }
                    event.getRegistry().register(item);
                    ITEMS.add(item);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
