package com.barribob.intothemaelstrom;

import com.barribob.intothemaelstrom.commands.CommandDimensionTeleport;
import com.barribob.intothemaelstrom.commands.CommandInvasion;
import com.barribob.intothemaelstrom.commands.CommandReloadConfigs;
import com.barribob.intothemaelstrom.commands.CommandRunUnitTests;
import com.barribob.intothemaelstrom.config.JsonConfigManager;
import com.barribob.intothemaelstrom.init.*;
import com.barribob.intothemaelstrom.loot.functions.ModEnchantWithLevels;
import com.barribob.intothemaelstrom.mana.IMana;
import com.barribob.intothemaelstrom.mana.Mana;
import com.barribob.intothemaelstrom.mana.ManaStorage;
import com.barribob.intothemaelstrom.packets.*;
import com.barribob.intothemaelstrom.util.Reference;
import com.barribob.intothemaelstrom.init.ModSoundEvents;
import com.barribob.intothemaelstrom.world.gen.WorldGenCustomStructures;
import com.barribob.intothemaelstrom.world.gen.WorldGenOre;
import com.typesafe.config.Config;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;

/**
 * Main mod class Many of the base boilerplate here is thanks to loremaster's
 * tutorials https://www.youtube.com/channel/UC3n-lKS-MYlunVtErgzSFZg Entities,
 * world generation, and dimension frameworks are thanks to Harry Talks
 * https://www.youtube.com/channel/UCUAawSqNFBEj-bxguJyJL9g Also thanks to
 * Julian Abelar for a bunch of modding tutorials and articles
 * https://jabelarminecraft.blogspot.com/
 * <p>
 * Also other tools that I used: World Edit from Single Player Commands, as well as MCEdit
 */
@Mod(modid = IntoTheMaelstrom.MOD_ID, name = Reference.NAME, version = Reference.VERSION, updateJSON = "https://raw.githubusercontent.com/miyo6032/MaelstromMod/LibraryIntegration/update.json")
public class IntoTheMaelstrom {
    public static final String MOD_ID = "intothemaelstrom";
    @Instance
    public static IntoTheMaelstrom instance;

    public static SimpleNetworkWrapper network;

    public static final JsonConfigManager CONFIG_MANAGER = new JsonConfigManager();
    public static Config itemsConfig;
    public static Config invasionsConfig;
    public static Config mobsConfig;
    public static Config soundsConfig;
    public static Config manaConfig;
    public static Config maelstromFriendsConfig;

    public static Logger log;

    public static final String CONFIG_DIRECTORY_NAME = "Maelstrom Mod";

    /**
     * Basically initializes the entire mod by calling all of the init methods in
     * the static classes
     */
    @EventHandler
    public static void PreInit(FMLPreInitializationEvent event) {
        log = event.getModLog();

        loadConfigs();

        GameRegistry.registerWorldGenerator(new WorldGenOre(), 2);
        GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 3);

        ModEntities.registerEntities();

        // Register a network to communicate to the server for client stuff (e.g. client
        // raycast rendering for extended melee reach)
        IntoTheMaelstrom.network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.NETWORK_CHANNEL_NAME);

        int packetId = 0;

        IntoTheMaelstrom.network.registerMessage(MessageExtendedReachAttack.Handler.class, MessageExtendedReachAttack.class, packetId++, Side.SERVER);
        IntoTheMaelstrom.network.registerMessage(MessageMana.MessageHandler.class, MessageMana.class, packetId++, Side.CLIENT);
        IntoTheMaelstrom.network.registerMessage(MessageLeap.MessageHandler.class, MessageLeap.class, packetId++, Side.CLIENT);
        IntoTheMaelstrom.network.registerMessage(MessageManaUnlock.MessageHandler.class, MessageManaUnlock.class, packetId++, Side.CLIENT);
        IntoTheMaelstrom.network.registerMessage(MessageDirectionForRender.Handler.class, MessageDirectionForRender.class, packetId++, Side.CLIENT);
        IntoTheMaelstrom.network.registerMessage(MessageModParticles.MessageHandler.class, MessageModParticles.class, packetId++, Side.CLIENT);
        IntoTheMaelstrom.network.registerMessage(MessageSyncConfig.Handler.class, MessageSyncConfig.class, packetId++, Side.CLIENT);
        IntoTheMaelstrom.network.registerMessage(MessageBBAnimation.Handler.class, MessageBBAnimation.class, packetId++, Side.CLIENT);
        IntoTheMaelstrom.network.registerMessage(MessageLoopAnimationUpdate.Handler.class, MessageLoopAnimationUpdate.class, packetId++, Side.CLIENT);
        IntoTheMaelstrom.network.registerMessage(MessageStartElytraFlying.Handler.class, MessageStartElytraFlying.class, packetId++, Side.SERVER);
        IntoTheMaelstrom.network.registerMessage(MessageEmptySwing.Handler.class, MessageEmptySwing.class, packetId++, Side.SERVER);
        IntoTheMaelstrom.network.registerMessage(MessagePlayDarkNexusWindSound.Handler.class, MessagePlayDarkNexusWindSound.class, packetId++, Side.CLIENT);

        CapabilityManager.INSTANCE.register(IMana.class, new ManaStorage(), Mana::new);
        // CapabilityManager.INSTANCE.register(IInvasion.class, new InvasionStorage(),
        // Invasion.class);

        ModBBAnimations.registerAnimations();
        ModDimensions.registerDimensions();
        LootFunctionManager.registerFunction(new ModEnchantWithLevels.Serializer());
    }

    @EventHandler
    public static void Init(FMLInitializationEvent event) {
        ModRecipes.init();
        ModSoundEvents.registerSounds();
        ModStructures.registerStructures();
        ModProfessions.associateCareersAndTrades();
    }

    @EventHandler
    public static void PostInit(FMLPostInitializationEvent event) {

    }

    @EventHandler
    public static void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandDimensionTeleport());
        event.registerServerCommand(new CommandReloadConfigs());
        event.registerServerCommand(new CommandInvasion());
        event.registerServerCommand(new CommandRunUnitTests());
    }

    public static void loadConfigs() {
        itemsConfig = CONFIG_MANAGER.handleConfigLoad(CONFIG_DIRECTORY_NAME, "items");
        invasionsConfig = CONFIG_MANAGER.handleConfigLoad(CONFIG_DIRECTORY_NAME, "invasions");
        mobsConfig = CONFIG_MANAGER.handleConfigLoad(CONFIG_DIRECTORY_NAME, "mobs");
        soundsConfig = CONFIG_MANAGER.handleConfigLoad(CONFIG_DIRECTORY_NAME, "sounds");
        manaConfig = CONFIG_MANAGER.handleConfigLoad(CONFIG_DIRECTORY_NAME, "mana");
        maelstromFriendsConfig = CONFIG_MANAGER.handleConfigLoad(CONFIG_DIRECTORY_NAME, "maelstrom_friends");
    }
}
