package com.barribob.intothemaelstrom.init;

import com.barribob.intothemaelstrom.IntoTheMaelstrom;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Keeps track of all the sound resources and registers them
 */
public class ModSoundEvents {

    public static JsonObject object = new JsonObject();
    private static File soundsFile = new File("config/sounds.json");

    private static final boolean DEBUG = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");


    public static SoundEvent ENTITY_MAELSTROM_MAGE_AMBIENT1;
    public static SoundEvent ENTITY_MAELSTROM_MAGE_AMBIENT2;
    public static SoundEvent ENTITY_MAELSTROM_MAGE_AMBIENT3;

    public static SoundEvent ENTITY_MAELSTROM_MAGE_HURT1;
    public static SoundEvent ENTITY_MAELSTROM_MAGE_HURT2;
    public static SoundEvent ENTITY_MAELSTROM_MAGE_HURT3;

    public static SoundEvent ENTITY_MAELSTROM_MAGE_DEATH1;
    public static SoundEvent ENTITY_MAELSTROM_MAGE_DEATH2;
    public static SoundEvent ENTITY_MAELSTROM_MAGE_DEATH3;

    public static SoundEvent ENTITY_MAELSTROM_MAGE_WARN;

    public static SoundEvent ENTITY_MAELSTROM_MAGE_SHOOT;


    public static SoundEvent ENTITY_MAELSTROM_HEALER_AMBIENT1;
    public static SoundEvent ENTITY_MAELSTROM_HEALER_AMBIENT2;
    public static SoundEvent ENTITY_MAELSTROM_HEALER_AMBIENT3;

    public static SoundEvent ENTITY_MAELSTROM_HEALER_HURT1;
    public static SoundEvent ENTITY_MAELSTROM_HEALER_HURT2;
    public static SoundEvent ENTITY_MAELSTROM_HEALER_HURT3;

    public static SoundEvent ENTITY_MAELSTROM_HEALER_DEATH1;
    public static SoundEvent ENTITY_MAELSTROM_HEALER_DEATH2;
    public static SoundEvent ENTITY_MAELSTROM_HEALER_DEATH3;

    public static SoundEvent ENTITY_MAELSTROM_HEALER_CAST;


    public static SoundEvent ENTITY_MAELSTROM_FURY_AMBIENT1;
    public static SoundEvent ENTITY_MAELSTROM_FURY_AMBIENT2;
    public static SoundEvent ENTITY_MAELSTROM_FURY_AMBIENT3;

    public static SoundEvent ENTITY_MAELSTROM_FURY_HURT1;
    public static SoundEvent ENTITY_MAELSTROM_FURY_HURT2;
    public static SoundEvent ENTITY_MAELSTROM_FURY_HURT3;

    public static SoundEvent ENTITY_MAELSTROM_FURY_DEATH1;
    public static SoundEvent ENTITY_MAELSTROM_FURY_DEATH2;
    public static SoundEvent ENTITY_MAELSTROM_FURY_DEATH3;


    public static SoundEvent ENTITY_MAELSTROM_LANCER_AMBIENT1;
    public static SoundEvent ENTITY_MAELSTROM_LANCER_AMBIENT2;
    public static SoundEvent ENTITY_MAELSTROM_LANCER_AMBIENT3;

    public static SoundEvent ENTITY_MAELSTROM_LANCER_HURT1;
    public static SoundEvent ENTITY_MAELSTROM_LANCER_HURT2;
    public static SoundEvent ENTITY_MAELSTROM_LANCER_HURT3;

    public static SoundEvent ENTITY_MAELSTROM_LANCER_DEATH1;
    public static SoundEvent ENTITY_MAELSTROM_LANCER_DEATH2;
    public static SoundEvent ENTITY_MAELSTROM_LANCER_DEATH3;


    public static SoundEvent ENTITY_SWAMP_CRAWLER_AMBIENT1;
    public static SoundEvent ENTITY_SWAMP_CRAWLER_AMBIENT2;
    public static SoundEvent ENTITY_SWAMP_CRAWLER_AMBIENT3;

    public static SoundEvent ENTITY_SWAMP_CRAWLER_HURT1;
    public static SoundEvent ENTITY_SWAMP_CRAWLER_HURT2;
    public static SoundEvent ENTITY_SWAMP_CRAWLER_HURT3;

    public static SoundEvent ENTITY_SWAMP_CRAWLER_DEATH1;
    public static SoundEvent ENTITY_SWAMP_CRAWLER_DEATH2;
    public static SoundEvent ENTITY_SWAMP_CRAWLER_DEATH3;

    public static SoundEvent ENTITY_SWAMP_CRAWLER_WARN;
    public static SoundEvent ENTITY_SWAMP_CRAWLER_FIRE;

    //aka executioner
    public static SoundEvent ENTITY_IRON_SHADE_AMBIENT1;
    public static SoundEvent ENTITY_IRON_SHADE_AMBIENT2;
    public static SoundEvent ENTITY_IRON_SHADE_AMBIENT3;

    public static SoundEvent ENTITY_IRON_SHADE_HURT1;
    public static SoundEvent ENTITY_IRON_SHADE_HURT2;
    public static SoundEvent ENTITY_IRON_SHADE_HURT3;

    public static SoundEvent ENTITY_IRON_SHADE_DEATH1;
    public static SoundEvent ENTITY_IRON_SHADE_DEATH2;
    public static SoundEvent ENTITY_IRON_SHADE_DEATH3;

    public static SoundEvent ENTITY_IRON_SHADE_FRONT_FLIP;
    public static SoundEvent ENTITY_IRON_SHADE_SPIN_SLASH;

    //aka cauldron
    public static SoundEvent ENTITY_HORROR_AMBIENT1;
    public static SoundEvent ENTITY_HORROR_AMBIENT2;
    public static SoundEvent ENTITY_HORROR_AMBIENT3;

    public static SoundEvent ENTITY_HORROR_HURT1;
    public static SoundEvent ENTITY_HORROR_HURT2;
    public static SoundEvent ENTITY_HORROR_HURT3;

    public static SoundEvent ENTITY_HORROR_DEATH1;
    public static SoundEvent ENTITY_HORROR_DEATH2;
    public static SoundEvent ENTITY_HORROR_DEATH3;

    //aka scout
    public static SoundEvent ENTITY_SHADE_AMBIENT1;
    public static SoundEvent ENTITY_SHADE_AMBIENT2;
    public static SoundEvent ENTITY_SHADE_AMBIENT3;

    public static SoundEvent ENTITY_SHADE_HURT1;
    public static SoundEvent ENTITY_SHADE_HURT2;
    public static SoundEvent ENTITY_SHADE_HURT3;

    public static SoundEvent ENTITY_SHADE_DEATH1;
    public static SoundEvent ENTITY_SHADE_DEATH2;
    public static SoundEvent ENTITY_SHADE_DEATH3;


    public static SoundEvent ENTITY_FLOATING_SKULL_AMBIENT1;
    public static SoundEvent ENTITY_FLOATING_SKULL_AMBIENT2;
    public static SoundEvent ENTITY_FLOATING_SKULL_AMBIENT3;

    public static SoundEvent ENTITY_FLOATING_SKULL_HURT1;
    public static SoundEvent ENTITY_FLOATING_SKULL_HURT2;
    public static SoundEvent ENTITY_FLOATING_SKULL_HURT3;

    public static SoundEvent ENTITY_FLOATING_SKULL_DEATH1;
    public static SoundEvent ENTITY_FLOATING_SKULL_DEATH2;
    public static SoundEvent ENTITY_FLOATING_SKULL_DEATH3;


    public static SoundEvent ENTITY_CLIFF_GOLEM_AMBIENT1;
    public static SoundEvent ENTITY_CLIFF_GOLEM_AMBIENT2;
    public static SoundEvent ENTITY_CLIFF_GOLEM_AMBIENT3;

    public static SoundEvent ENTITY_CLIFF_GOLEM_HURT1;
    public static SoundEvent ENTITY_CLIFF_GOLEM_HURT2;
    public static SoundEvent ENTITY_CLIFF_GOLEM_HURT3;

    public static SoundEvent ENTITY_CLIFF_GOLEM_DEATH1;
    public static SoundEvent ENTITY_CLIFF_GOLEM_DEATH2;
    public static SoundEvent ENTITY_CLIFF_GOLEM_DEATH3;

    public static SoundEvent ENTITY_CLIFF_GOLEM_WARN;
    public static SoundEvent ENTITY_CLIFF_GOLEM_SMASH;


    public static SoundEvent ENTITY_CLIFF_FLY_AMBIENT1;
    public static SoundEvent ENTITY_CLIFF_FLY_AMBIENT2;
    public static SoundEvent ENTITY_CLIFF_FLY_AMBIENT3;

    public static SoundEvent ENTITY_CLIFF_FLY_HURT1;
    public static SoundEvent ENTITY_CLIFF_FLY_HURT2;
    public static SoundEvent ENTITY_CLIFF_FLY_HURT3;

    public static SoundEvent ENTITY_CLIFF_FLY_DEATH1;
    public static SoundEvent ENTITY_CLIFF_FLY_DEATH2;
    public static SoundEvent ENTITY_CLIFF_FLY_DEATH3;

    public static SoundEvent ENTITY_CLIFF_FLY_WARN;
    public static SoundEvent ENTITY_CLIFF_FLY_FIRE;


    public static SoundEvent ENTITY_MONOLITH_AMBIENT1;
    public static SoundEvent ENTITY_MONOLITH_AMBIENT2;
    public static SoundEvent ENTITY_MONOLITH_AMBIENT3;

    public static SoundEvent ENTITY_MONOLITH_HURT1;
    public static SoundEvent ENTITY_MONOLITH_HURT2;
    public static SoundEvent ENTITY_MONOLITH_HURT3;

    public static SoundEvent ENTITY_MONOLITH_DEATH1;
    public static SoundEvent ENTITY_MONOLITH_DEATH2;
    public static SoundEvent ENTITY_MONOLITH_DEATH3;

    public static SoundEvent ENTITY_MONOLITH_LAZER_SHOOT;
    public static SoundEvent ENTITY_MONOLITH_FIREBALL_SHOOT;

    //aka minotaur
    public static SoundEvent ENTITY_MAELSTROM_BEAST_AMBIENT1;
    public static SoundEvent ENTITY_MAELSTROM_BEAST_AMBIENT2;
    public static SoundEvent ENTITY_MAELSTROM_BEAST_AMBIENT3;

    public static SoundEvent ENTITY_MAELSTROM_BEAST_HURT1;
    public static SoundEvent ENTITY_MAELSTROM_BEAST_HURT2;
    public static SoundEvent ENTITY_MAELSTROM_BEAST_HURT3;

    public static SoundEvent ENTITY_MAELSTROM_BEAST_DEATH1;
    public static SoundEvent ENTITY_MAELSTROM_BEAST_DEATH2;
    public static SoundEvent ENTITY_MAELSTROM_BEAST_DEATH3;

    public static SoundEvent ENTITY_MAELSTROM_BEAST_ROAR;
    public static SoundEvent ENTITY_MAELSTROM_BEAST_SWIPE;
    public static SoundEvent ENTITY_MAELSTROM_BEAST_BONE_PROJECTILE_IMPACT;


    public static SoundEvent ENTITY_BEAST_AMBIENT;
    public static SoundEvent ENTITY_BEAST_HURT;

    //aka champion
    public static SoundEvent ENTITY_CHAOS_KNIGHT_BLOCK;

    public static SoundEvent ENTITY_CHAOS_KNIGHT_AMBIENT1;
    public static SoundEvent ENTITY_CHAOS_KNIGHT_AMBIENT2;
    public static SoundEvent ENTITY_CHAOS_KNIGHT_AMBIENT3;

    public static SoundEvent ENTITY_CHAOS_KNIGHT_HURT1;
    public static SoundEvent ENTITY_CHAOS_KNIGHT_HURT2;
    public static SoundEvent ENTITY_CHAOS_KNIGHT_HURT3;

    public static SoundEvent ENTITY_CHAOS_KNIGHT_DEATH1;
    public static SoundEvent ENTITY_CHAOS_KNIGHT_DEATH2;
    public static SoundEvent ENTITY_CHAOS_KNIGHT_DEATH3;

    public static SoundEvent ENTITY_CHAOS_KNIGHT_SIDE_SWIPE;
    public static SoundEvent ENTITY_CHAOS_KNIGHT_SPIN_SLASH;


    public static SoundEvent ENTITY_MAELSTROM_STATUE_OF_NIRVANA_AMBIENT1;
    public static SoundEvent ENTITY_MAELSTROM_STATUE_OF_NIRVANA_AMBIENT2;
    public static SoundEvent ENTITY_MAELSTROM_STATUE_OF_NIRVANA_AMBIENT3;

    public static SoundEvent ENTITY_MAELSTROM_STATUE_OF_NIRVANA_HURT1;
    public static SoundEvent ENTITY_MAELSTROM_STATUE_OF_NIRVANA_HURT2;
    public static SoundEvent ENTITY_MAELSTROM_STATUE_OF_NIRVANA_HURT3;

    public static SoundEvent ENTITY_MAELSTROM_STATUE_OF_NIRVANA_DEATH1;
    public static SoundEvent ENTITY_MAELSTROM_STATUE_OF_NIRVANA_DEATH2;
    public static SoundEvent ENTITY_MAELSTROM_STATUE_OF_NIRVANA_DEATH3;

    public static SoundEvent ENTITY_MAELSTROM_STATUE_OF_NIRVANA_MISSILE_FIRE;

    public static SoundEvent ENTITY_MAELSTROM_STATUE_OF_NIRVANA_SUMMON;


    //aka statue of nirvana?
    public static SoundEvent ENTITY_GOLDEN_BOSS_AMBIENT1;
    public static SoundEvent ENTITY_GOLDEN_BOSS_AMBIENT2;
    public static SoundEvent ENTITY_GOLDEN_BOSS_AMBIENT3;

    public static SoundEvent ENTITY_GOLDEN_BOSS_HURT1;
    public static SoundEvent ENTITY_GOLDEN_BOSS_HURT2;
    public static SoundEvent ENTITY_GOLDEN_BOSS_HURT3;

    public static SoundEvent ENTITY_GOLDEN_BOSS_DEATH1;
    public static SoundEvent ENTITY_GOLDEN_BOSS_DEATH2;
    public static SoundEvent ENTITY_GOLDEN_BOSS_DEATH3;


    public static SoundEvent ENTITY_MAELSTROM_GAUNTLET_AMBIENT1;
    public static SoundEvent ENTITY_MAELSTROM_GAUNTLET_AMBIENT2;
    public static SoundEvent ENTITY_MAELSTROM_GAUNTLET_AMBIENT3;
    
    public static SoundEvent ENTITY_MAELSTROM_GAUNTLET_HURT1;
    public static SoundEvent ENTITY_MAELSTROM_GAUNTLET_HURT2;
    public static SoundEvent ENTITY_MAELSTROM_GAUNTLET_HURT3;

    public static SoundEvent ENTITY_MAELSTROM_GAUNTLET_DEATH1;
    public static SoundEvent ENTITY_MAELSTROM_GAUNTLET_DEATH2;
    public static SoundEvent ENTITY_MAELSTROM_GAUNTLET_DEATH3;


    public static SoundEvent ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_1_AMBIENT1;
    public static SoundEvent ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_1_AMBIENT2;
    public static SoundEvent ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_1_AMBIENT3;

    public static SoundEvent ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_1_HURT1;
    public static SoundEvent ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_1_HURT2;
    public static SoundEvent ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_1_HURT3;

    public static SoundEvent ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_1_DEATH1;
    public static SoundEvent ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_1_DEATH2;
    public static SoundEvent ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_1_DEATH3;


    public static SoundEvent ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_2_AMBIENT1;
    public static SoundEvent ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_2_AMBIENT2;
    public static SoundEvent ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_2_AMBIENT3;

    public static SoundEvent ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_2_HURT1;
    public static SoundEvent ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_2_HURT2;
    public static SoundEvent ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_2_HURT3;

    public static SoundEvent ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_2_DEATH1;
    public static SoundEvent ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_2_DEATH2;
    public static SoundEvent ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_2_DEATH3;


    public static SoundEvent ENTITY_HORROR_ATTACK_EXPLODE;


    public static SoundEvent ENTITY_MAELSTROM_GAUNTLET_LAZER_CHARGE;
    
    public static SoundEvent NONE;

    public static SoundEvent ENTITY_ILLAGER_SPELL_CHARGE;
    public static SoundEvent ENTITY_ILLAGER_DOME_CHARGE;
    public static SoundEvent ENTITY_ILLAGER_MAGIC_MISSLE_SHOOT;
    public static SoundEvent ENTITY_ILLAGER_VORTEX;
    public static SoundEvent ENTITY_ILLAGER_DOME;

    //scout, cauldron

    public static void registerSounds() {


        ENTITY_MAELSTROM_HEALER_AMBIENT1 = registerSound("maelstrom_healer.ambient1", "entity");
        ENTITY_MAELSTROM_HEALER_AMBIENT2 = registerSound("maelstrom_healer.ambient2", "entity");
        ENTITY_MAELSTROM_HEALER_AMBIENT3 = registerSound("maelstrom_healer.ambient3", "entity");

        ENTITY_MAELSTROM_HEALER_HURT1 = registerSound("maelstrom_healer.hurt1", "entity");
        ENTITY_MAELSTROM_HEALER_HURT2 = registerSound("maelstrom_healer.hurt2", "entity");
        ENTITY_MAELSTROM_HEALER_HURT3 = registerSound("maelstrom_healer.hurt3", "entity");

        ENTITY_MAELSTROM_HEALER_DEATH1 = registerSound("maelstrom_healer.death1", "entity");
        ENTITY_MAELSTROM_HEALER_DEATH2 = registerSound("maelstrom_healer.death2", "entity");
        ENTITY_MAELSTROM_HEALER_DEATH3 = registerSound("maelstrom_healer.death3", "entity");

        ENTITY_MAELSTROM_HEALER_CAST = registerSound("maelstrom_healer.cast", "entity");


        ENTITY_MAELSTROM_MAGE_AMBIENT1 = registerSound("maelstrom_mage.ambient1", "entity");
        ENTITY_MAELSTROM_MAGE_AMBIENT2 = registerSound("maelstrom_mage.ambient2", "entity");
        ENTITY_MAELSTROM_MAGE_AMBIENT3 = registerSound("maelstrom_mage.ambient3", "entity");

        ENTITY_MAELSTROM_MAGE_HURT1 = registerSound("maelstrom_mage.hurt1", "entity");
        ENTITY_MAELSTROM_MAGE_HURT2 = registerSound("maelstrom_mage.hurt2", "entity");
        ENTITY_MAELSTROM_MAGE_HURT3 = registerSound("maelstrom_mage.hurt3", "entity");

        ENTITY_MAELSTROM_MAGE_DEATH1 = registerSound("maelstrom_mage.death1", "entity");
        ENTITY_MAELSTROM_MAGE_DEATH2 = registerSound("maelstrom_mage.death2", "entity");
        ENTITY_MAELSTROM_MAGE_DEATH3 = registerSound("maelstrom_mage.death3", "entity");

        ENTITY_MAELSTROM_MAGE_WARN = registerSound("maelstrom_gauntlet.warn", "entity");

        ENTITY_MAELSTROM_MAGE_SHOOT = registerSound("maelstrom_gauntlet.shoot", "entity");


        ENTITY_MAELSTROM_FURY_AMBIENT1 = registerSound("maelstrom_fury.ambient1", "entity");
        ENTITY_MAELSTROM_FURY_AMBIENT2 = registerSound("maelstrom_fury.ambient2", "entity");
        ENTITY_MAELSTROM_FURY_AMBIENT3 = registerSound("maelstrom_fury.ambient3", "entity");

        ENTITY_MAELSTROM_FURY_HURT1 = registerSound("maelstrom_fury.hurt1", "entity");
        ENTITY_MAELSTROM_FURY_HURT2 = registerSound("maelstrom_fury.hurt2", "entity");
        ENTITY_MAELSTROM_FURY_HURT3 = registerSound("maelstrom_fury.hurt3", "entity");

        ENTITY_MAELSTROM_FURY_DEATH1 = registerSound("maelstrom_fury.death1", "entity");
        ENTITY_MAELSTROM_FURY_DEATH2 = registerSound("maelstrom_fury.death2", "entity");
        ENTITY_MAELSTROM_FURY_DEATH3 = registerSound("maelstrom_fury.death3", "entity");


        ENTITY_MAELSTROM_LANCER_AMBIENT1 = registerSound("maelstrom_lancer.ambient1", "entity");
        ENTITY_MAELSTROM_LANCER_AMBIENT2 = registerSound("maelstrom_lancer.ambient2", "entity");
        ENTITY_MAELSTROM_LANCER_AMBIENT3 = registerSound("maelstrom_lancer.ambient3", "entity");

        ENTITY_MAELSTROM_LANCER_HURT1 = registerSound("maelstrom_lancer.hurt1", "entity");
        ENTITY_MAELSTROM_LANCER_HURT2 = registerSound("maelstrom_lancer.hurt2", "entity");
        ENTITY_MAELSTROM_LANCER_HURT3 = registerSound("maelstrom_lancer.hurt3", "entity");

        ENTITY_MAELSTROM_LANCER_DEATH1 = registerSound("maelstrom_lancer.death1", "entity");
        ENTITY_MAELSTROM_LANCER_DEATH2 = registerSound("maelstrom_lancer.death2", "entity");
        ENTITY_MAELSTROM_LANCER_DEATH3 = registerSound("maelstrom_lancer.death3", "entity");


        ENTITY_SWAMP_CRAWLER_AMBIENT1 = registerSound("swamp_crawler.ambient1", "entity");
        ENTITY_SWAMP_CRAWLER_AMBIENT2 = registerSound("swamp_crawler.ambient2", "entity");
        ENTITY_SWAMP_CRAWLER_AMBIENT3 = registerSound("swamp_crawler.ambient3", "entity");

        ENTITY_SWAMP_CRAWLER_HURT1 = registerSound("swamp_crawler.hurt1", "entity");
        ENTITY_SWAMP_CRAWLER_HURT2 = registerSound("swamp_crawler.hurt2", "entity");
        ENTITY_SWAMP_CRAWLER_HURT3 = registerSound("swamp_crawler.hurt3", "entity");

        ENTITY_SWAMP_CRAWLER_DEATH1 = registerSound("swamp_crawler.death1", "entity");
        ENTITY_SWAMP_CRAWLER_DEATH2 = registerSound("swamp_crawler.death2", "entity");
        ENTITY_SWAMP_CRAWLER_DEATH3 = registerSound("swamp_crawler.death3", "entity");

        ENTITY_SWAMP_CRAWLER_WARN = registerSound("swamp_crawler.warn", "entity");
        ENTITY_SWAMP_CRAWLER_FIRE = registerSound("swamp_crawler.fire", "entity");


        ENTITY_IRON_SHADE_AMBIENT1 = registerSound("iron_shade.ambient1", "entity");
        ENTITY_IRON_SHADE_AMBIENT2 = registerSound("iron_shade.ambient2", "entity");
        ENTITY_IRON_SHADE_AMBIENT3 = registerSound("iron_shade.ambient3", "entity");

        ENTITY_IRON_SHADE_HURT1 = registerSound("iron_shade.hurt1", "entity");
        ENTITY_IRON_SHADE_HURT2 = registerSound("iron_shade.hurt2", "entity");
        ENTITY_IRON_SHADE_HURT3 = registerSound("iron_shade.hurt3", "entity");

        ENTITY_IRON_SHADE_DEATH1 = registerSound("iron_shade.death1", "entity");
        ENTITY_IRON_SHADE_DEATH2 = registerSound("iron_shade.death2", "entity");
        ENTITY_IRON_SHADE_DEATH3 = registerSound("iron_shade.death3", "entity");

        ENTITY_IRON_SHADE_FRONT_FLIP = registerSound("iron_shade.front_flip", "entity");
        ENTITY_IRON_SHADE_SPIN_SLASH = registerSound("iron_shade.spin_slash", "entity");


        ENTITY_HORROR_AMBIENT1 = registerSound("horror.ambient1", "entity");
        ENTITY_HORROR_AMBIENT2 = registerSound("horror.ambient2", "entity");
        ENTITY_HORROR_AMBIENT3 = registerSound("horror.ambient3", "entity");

        ENTITY_HORROR_HURT1 = registerSound("horror.hurt1", "entity");
        ENTITY_HORROR_HURT2 = registerSound("horror.hurt2", "entity");
        ENTITY_HORROR_HURT3 = registerSound("horror.hurt3", "entity");

        ENTITY_HORROR_DEATH1 = registerSound("horror.death1", "entity");
        ENTITY_HORROR_DEATH2 = registerSound("horror.death2", "entity");
        ENTITY_HORROR_DEATH3 = registerSound("horror.death3", "entity");


        ENTITY_SHADE_AMBIENT1 = registerSound("shade.ambient1", "entity");
        ENTITY_SHADE_AMBIENT2 = registerSound("shade.ambient2", "entity");
        ENTITY_SHADE_AMBIENT3 = registerSound("shade.ambient3", "entity");

        ENTITY_SHADE_HURT1 = registerSound("shade.hurt1", "entity");
        ENTITY_SHADE_HURT2 = registerSound("shade.hurt2", "entity");
        ENTITY_SHADE_HURT3 = registerSound("shade.hurt3", "entity");

        ENTITY_SHADE_DEATH1 = registerSound("shade.death1", "entity");
        ENTITY_SHADE_DEATH2 = registerSound("shade.death2", "entity");
        ENTITY_SHADE_DEATH3 = registerSound("shade.death3", "entity");


        ENTITY_FLOATING_SKULL_AMBIENT1 = registerSound("floating_skull.ambient1", "entity");
        ENTITY_FLOATING_SKULL_AMBIENT2 = registerSound("floating_skull.ambient2", "entity");
        ENTITY_FLOATING_SKULL_AMBIENT3 = registerSound("floating_skull.ambient3", "entity");

        ENTITY_FLOATING_SKULL_HURT1 = registerSound("floating_skull.hurt1", "entity");
        ENTITY_FLOATING_SKULL_HURT2 = registerSound("floating_skull.hurt2", "entity");
        ENTITY_FLOATING_SKULL_HURT3 = registerSound("floating_skull.hurt3", "entity");

        ENTITY_FLOATING_SKULL_DEATH1 = registerSound("floating_skull.death1", "entity");
        ENTITY_FLOATING_SKULL_DEATH2 = registerSound("floating_skull.death2", "entity");
        ENTITY_FLOATING_SKULL_DEATH3 = registerSound("floating_skull.death3", "entity");


        ENTITY_CLIFF_GOLEM_AMBIENT1 = registerSound("cliff_golem.ambient1", "entity");
        ENTITY_CLIFF_GOLEM_AMBIENT2 = registerSound("cliff_golem.ambient2", "entity");
        ENTITY_CLIFF_GOLEM_AMBIENT3 = registerSound("cliff_golem.ambient3", "entity");

        ENTITY_CLIFF_GOLEM_HURT1 = registerSound("cliff_golem.hurt1", "entity");
        ENTITY_CLIFF_GOLEM_HURT2 = registerSound("cliff_golem.hurt2", "entity");
        ENTITY_CLIFF_GOLEM_HURT3 = registerSound("cliff_golem.hurt3", "entity");

        ENTITY_CLIFF_GOLEM_DEATH1 = registerSound("cliff_golem.death1", "entity");
        ENTITY_CLIFF_GOLEM_DEATH2 = registerSound("cliff_golem.death2", "entity");
        ENTITY_CLIFF_GOLEM_DEATH3 = registerSound("cliff_golem.death3", "entity");

        ENTITY_CLIFF_GOLEM_WARN = registerSound("cliff_golem.warn", "entity");
        ENTITY_CLIFF_GOLEM_SMASH = registerSound("cliff_golem.smash", "entity");


        ENTITY_CLIFF_FLY_AMBIENT1 = registerSound("cliff_fly.ambient1", "entity");
        ENTITY_CLIFF_FLY_AMBIENT2 = registerSound("cliff_fly.ambient2", "entity");
        ENTITY_CLIFF_FLY_AMBIENT3 = registerSound("cliff_fly.ambient3", "entity");

        ENTITY_CLIFF_FLY_HURT1 = registerSound("cliff_fly.hurt1", "entity");
        ENTITY_CLIFF_FLY_HURT2 = registerSound("cliff_fly.hurt2", "entity");
        ENTITY_CLIFF_FLY_HURT3 = registerSound("cliff_fly.hurt3", "entity");

        ENTITY_CLIFF_FLY_DEATH1 = registerSound("cliff_fly.death1", "entity");
        ENTITY_CLIFF_FLY_DEATH2 = registerSound("cliff_fly.death2", "entity");
        ENTITY_CLIFF_FLY_DEATH3 = registerSound("cliff_fly.death3", "entity");

        ENTITY_CLIFF_FLY_WARN = registerSound("cliff_fly.warn", "entity");
        ENTITY_CLIFF_FLY_FIRE = registerSound("cliff_fly.fire", "entity");


        ENTITY_MONOLITH_AMBIENT1 = registerSound("monolith.ambient1", "entity");
        ENTITY_MONOLITH_AMBIENT2 = registerSound("monolith.ambient2", "entity");
        ENTITY_MONOLITH_AMBIENT3 = registerSound("monolith.ambient3", "entity");

        ENTITY_MONOLITH_HURT1 = registerSound("monolith.hurt1", "entity");
        ENTITY_MONOLITH_HURT2 = registerSound("monolith.hurt2", "entity");
        ENTITY_MONOLITH_HURT3 = registerSound("monolith.hurt3", "entity");

        ENTITY_MONOLITH_DEATH1 = registerSound("monolith.death1", "entity");
        ENTITY_MONOLITH_DEATH2 = registerSound("monolith.death2", "entity");
        ENTITY_MONOLITH_DEATH3 = registerSound("monolith.death3", "entity");

        ENTITY_MONOLITH_LAZER_SHOOT = registerSound("monolith.lazer_shoot", "entity");
        ENTITY_MONOLITH_FIREBALL_SHOOT = registerSound("monolith.fireball_shoot", "entity");


        ENTITY_MAELSTROM_BEAST_AMBIENT1 = registerSound("maelstrom_beast.ambient1", "entity");
        ENTITY_MAELSTROM_BEAST_AMBIENT2 = registerSound("maelstrom_beast.ambient2", "entity");
        ENTITY_MAELSTROM_BEAST_AMBIENT3 = registerSound("maelstrom_beast.ambient3", "entity");

        ENTITY_MAELSTROM_BEAST_HURT1 = registerSound("maelstrom_beast.hurt1", "entity");
        ENTITY_MAELSTROM_BEAST_HURT2 = registerSound("maelstrom_beast.hurt2", "entity");
        ENTITY_MAELSTROM_BEAST_HURT3 = registerSound("maelstrom_beast.hurt3", "entity");

        ENTITY_MAELSTROM_BEAST_DEATH1 = registerSound("maelstrom_beast.death1", "entity");
        ENTITY_MAELSTROM_BEAST_DEATH2 = registerSound("maelstrom_beast.death2", "entity");
        ENTITY_MAELSTROM_BEAST_DEATH3 = registerSound("maelstrom_beast.death3", "entity");

        ENTITY_MAELSTROM_BEAST_ROAR = registerSound("maelstrom_beast.roar", "entity");
        ENTITY_MAELSTROM_BEAST_SWIPE = registerSound("maelstrom_beast.swipe", "entity");


        ENTITY_BEAST_AMBIENT = registerSound("beast.ambient", "entity");
        ENTITY_BEAST_HURT = registerSound("beast.hurt", "entity");


        ENTITY_CHAOS_KNIGHT_BLOCK = registerSound("chaos_knight.block", "entity");

        ENTITY_CHAOS_KNIGHT_AMBIENT1 = registerSound("chaos_knight.ambient1", "entity");
        ENTITY_CHAOS_KNIGHT_AMBIENT2 = registerSound("chaos_knight.ambient2", "entity");
        ENTITY_CHAOS_KNIGHT_AMBIENT3 = registerSound("chaos_knight.ambient3", "entity");

        ENTITY_CHAOS_KNIGHT_HURT1 = registerSound("chaos_knight.hurt1", "entity");
        ENTITY_CHAOS_KNIGHT_HURT2 = registerSound("chaos_knight.hurt2", "entity");
        ENTITY_CHAOS_KNIGHT_HURT3 = registerSound("chaos_knight.hurt3", "entity");

        ENTITY_CHAOS_KNIGHT_DEATH1 = registerSound("chaos_knight.death1", "entity");
        ENTITY_CHAOS_KNIGHT_DEATH2 = registerSound("chaos_knight.death2", "entity");
        ENTITY_CHAOS_KNIGHT_DEATH3 = registerSound("chaos_knight.death3", "entity");

        ENTITY_CHAOS_KNIGHT_SIDE_SWIPE = registerSound("chaos_knight.side_swipe", "entity");
        ENTITY_CHAOS_KNIGHT_SPIN_SLASH = registerSound("chaos_knight.spin_slash", "entity");


        ENTITY_MAELSTROM_STATUE_OF_NIRVANA_AMBIENT1 = registerSound("maelstrom_statue_of_nirvana.ambient1", "entity");
        ENTITY_MAELSTROM_STATUE_OF_NIRVANA_AMBIENT2 = registerSound("maelstrom_statue_of_nirvana.ambient2", "entity");
        ENTITY_MAELSTROM_STATUE_OF_NIRVANA_AMBIENT3 = registerSound("maelstrom_statue_of_nirvana.ambient3", "entity");

        ENTITY_MAELSTROM_STATUE_OF_NIRVANA_HURT1 = registerSound("maelstrom_statue_of_nirvana.hurt1", "entity");
        ENTITY_MAELSTROM_STATUE_OF_NIRVANA_HURT2 = registerSound("maelstrom_statue_of_nirvana.hurt2", "entity");
        ENTITY_MAELSTROM_STATUE_OF_NIRVANA_HURT3 = registerSound("maelstrom_statue_of_nirvana.hurt3", "entity");

        ENTITY_MAELSTROM_STATUE_OF_NIRVANA_DEATH1 = registerSound("maelstrom_statue_of_nirvana.death1", "entity");
        ENTITY_MAELSTROM_STATUE_OF_NIRVANA_DEATH2 = registerSound("maelstrom_statue_of_nirvana.death2", "entity");
        ENTITY_MAELSTROM_STATUE_OF_NIRVANA_DEATH3 = registerSound("maelstrom_statue_of_nirvana.death3", "entity");

        ENTITY_MAELSTROM_STATUE_OF_NIRVANA_MISSILE_FIRE = registerSound("maelstrom_statue_of_nirvana.missile_fire", "entity");
        ENTITY_MAELSTROM_STATUE_OF_NIRVANA_SUMMON = registerSound("maelstrom_statue_of_nirvana.summon", "entity");


        ENTITY_GOLDEN_BOSS_AMBIENT1 = registerSound("golden_boss.ambient1", "entity");
        ENTITY_GOLDEN_BOSS_AMBIENT2 = registerSound("golden_boss.ambient2", "entity");
        ENTITY_GOLDEN_BOSS_AMBIENT3 = registerSound("golden_boss.ambient3", "entity");

        ENTITY_GOLDEN_BOSS_HURT1 = registerSound("golden_boss.hurt1", "entity");
        ENTITY_GOLDEN_BOSS_HURT2 = registerSound("golden_boss.hurt2", "entity");
        ENTITY_GOLDEN_BOSS_HURT3 = registerSound("golden_boss.hurt3", "entity");

        ENTITY_GOLDEN_BOSS_DEATH1 = registerSound("golden_boss.death1", "entity");
        ENTITY_GOLDEN_BOSS_DEATH2 = registerSound("golden_boss.death2", "entity");
        ENTITY_GOLDEN_BOSS_DEATH3 = registerSound("golden_boss.death3", "entity");


        ENTITY_MAELSTROM_GAUNTLET_AMBIENT1 = registerSound("maelstrom_gauntlet.ambient1", "entity");
        ENTITY_MAELSTROM_GAUNTLET_AMBIENT2 = registerSound("maelstrom_gauntlet.ambient2", "entity");
        ENTITY_MAELSTROM_GAUNTLET_AMBIENT3 = registerSound("maelstrom_gauntlet.ambient3", "entity");
        
        ENTITY_MAELSTROM_GAUNTLET_HURT1 = registerSound("maelstrom_gauntlet.hurt1", "entity");
        ENTITY_MAELSTROM_GAUNTLET_HURT2 = registerSound("maelstrom_gauntlet.hurt2", "entity");
        ENTITY_MAELSTROM_GAUNTLET_HURT3 = registerSound("maelstrom_gauntlet.hurt3", "entity");

        ENTITY_MAELSTROM_GAUNTLET_DEATH1 = registerSound("maelstrom_gauntlet.death1", "entity");
        ENTITY_MAELSTROM_GAUNTLET_DEATH2 = registerSound("maelstrom_gauntlet.death2", "entity");
        ENTITY_MAELSTROM_GAUNTLET_DEATH3 = registerSound("maelstrom_gauntlet.death3", "entity");


        ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_1_AMBIENT1 = registerSound("alternative_maelstrom_gauntlet_stage_1.ambient1", "entity");
        ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_1_AMBIENT2 = registerSound("alternative_maelstrom_gauntlet_stage_1.ambient2", "entity");
        ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_1_AMBIENT3 = registerSound("alternative_maelstrom_gauntlet_stage_1.ambient3", "entity");

        ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_1_HURT1 = registerSound("alternative_maelstrom_gauntlet_stage_1.hurt1", "entity");
        ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_1_HURT2 = registerSound("alternative_maelstrom_gauntlet_stage_1.hurt2", "entity");
        ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_1_HURT3 = registerSound("alternative_maelstrom_gauntlet_stage_1.hurt3", "entity");

        ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_1_DEATH1 = registerSound("alternative_maelstrom_gauntlet_stage_1.death1", "entity");
        ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_1_DEATH2 = registerSound("alternative_maelstrom_gauntlet_stage_1.death2", "entity");
        ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_1_DEATH3 = registerSound("alternative_maelstrom_gauntlet_stage_1.death3", "entity");


        ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_2_AMBIENT1 = registerSound("alternative_maelstrom_gauntlet_stage_2.ambient1", "entity");
        ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_2_AMBIENT2 = registerSound("alternative_maelstrom_gauntlet_stage_2.ambient2", "entity");
        ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_2_AMBIENT3 = registerSound("alternative_maelstrom_gauntlet_stage_2.ambient3", "entity");

        ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_2_HURT1 = registerSound("alternative_maelstrom_gauntlet_stage_2.hurt1", "entity");
        ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_2_HURT2 = registerSound("alternative_maelstrom_gauntlet_stage_2.hurt2", "entity");
        ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_2_HURT3 = registerSound("alternative_maelstrom_gauntlet_stage_2.hurt3", "entity");

        ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_2_DEATH1 = registerSound("alternative_maelstrom_gauntlet_stage_2.death1", "entity");
        ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_2_DEATH2 = registerSound("alternative_maelstrom_gauntlet_stage_2.death2", "entity");
        ENTITY_ALTERNATIVE_MAELSTROM_GAUNTLET_STAGE_2_DEATH3 = registerSound("alternative_maelstrom_gauntlet_stage_2.death3", "entity");


        ENTITY_MAELSTROM_GAUNTLET_LAZER_CHARGE = registerSound("maelstrom_gauntlet.lazer_charge", "entity");


        ENTITY_HORROR_ATTACK_EXPLODE = registerSound("horror_attack.explode", "entity");


        NONE = registerSound("none", "entity");

        ENTITY_ILLAGER_SPELL_CHARGE = registerSound("illager.spell_charge", "entity");
        ENTITY_ILLAGER_DOME_CHARGE = registerSound("illager.dome_charge", "entity");
        ENTITY_ILLAGER_VORTEX = registerSound("illager.vortex", "entity");
        ENTITY_ILLAGER_DOME = registerSound("illager.dome", "entity");

        ENTITY_ILLAGER_MAGIC_MISSLE_SHOOT = registerSound("illager.magic_missle_shoot", "entity");

        if (DEBUG) {
            try {
                FileWriter writer = new FileWriter(soundsFile);
                writer.write(g.toJson(object));
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static Gson g = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();


    private static SoundEvent registerSound(String name, String category) {
        String fullName = category + "." + name;
        String loca = fullName.replace(".","/");
        ResourceLocation location = new ResourceLocation(IntoTheMaelstrom.MOD_ID, fullName);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(fullName);
        ForgeRegistries.SOUND_EVENTS.register(event);

        if (DEBUG) {

            JsonArray sounds = new JsonArray();
            JsonObject obj1 = new JsonObject();
            obj1.addProperty("name", "intothemaelstrom:" + loca);
            obj1.addProperty("stream", true);
            sounds.add(obj1);
            JsonObject obj2 = new JsonObject();
            obj2.addProperty("category", "entity");
            obj2.addProperty("subtitle", fullName);
            obj2.add("sounds", sounds);

            object.add(fullName, obj2);

        }

        return event;
    }
}
