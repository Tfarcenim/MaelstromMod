package com.barribob.intothemaelstrom.util.handlers;

import com.barribob.intothemaelstrom.IntoTheMaelstrom;
import com.barribob.intothemaelstrom.entity.entities.*;
import com.barribob.intothemaelstrom.entity.entities.gauntlet.EntityAlternativeMaelstromGauntletStage1;
import com.barribob.intothemaelstrom.entity.entities.gauntlet.EntityAlternativeMaelstromGauntletStage2;
import com.barribob.intothemaelstrom.entity.entities.gauntlet.EntityCrimsonCrystal;
import com.barribob.intothemaelstrom.entity.entities.gauntlet.EntityMaelstromGauntlet;
import com.barribob.intothemaelstrom.entity.entities.npc.*;
import com.barribob.intothemaelstrom.entity.model.*;
import com.barribob.intothemaelstrom.entity.projectile.*;
import com.barribob.intothemaelstrom.entity.render.*;
import com.barribob.intothemaelstrom.entity.util.*;
import com.barribob.intothemaelstrom.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import java.util.function.Function;

public class RenderHandler {
    public static void registerEntityRenderers() {
        registerModEntityRenderer(EntityShade.class, new ModelMaelstromWarrior(), "shade_base.png", "shade_azure.png", "shade_golden.png", "shade_crimson.png");
        registerModEntityRenderer(EntityHorror.class, new ModelHorror(), "horror.png");
        registerModEntityRenderer(EntityDreamElk.class, new ModelDreamElk(), "dream_elk.png");
        registerModEntityRenderer(EntityBeast.class, new ModelBeast(), "beast.png", "beast.png", "beast.png", "beast_crimson.png");
        registerModEntityRenderer(EntityMaelstromMage.class, new ModelMaelstromMage(), "maelstrom_mage.png", "maelstrom_mage_azure.png", "maelstrom_mage_golden.png", "maelstrom_mage_crimson.png");
        registerModEntityRenderer(EntityFloatingSkull.class, new ModelFloatingSkull(), "floating_skull.png");
        registerModEntityRenderer(HerobrineEntity.class, (manager) -> new RenderHerobrine<>(manager, new ResourceLocation(IntoTheMaelstrom.MOD_ID + ":textures/entity/herobrine_1.png")));
        registerModEntityRenderer(EntityHerobrineOne.class, (manager) -> new RenderHerobrine<>(manager, new ResourceLocation(IntoTheMaelstrom.MOD_ID + ":textures/entity/shadow_clone.png")));
        registerModEntityRenderer(NexusGunTrader.class, new ModelGunTrader(), "gun_trader.png");
        registerModEntityRenderer(NexusMageTrader.class, new ModelMageTrader(), "mage_trader.png");
        registerModEntityRenderer(NexusArmorer.class, new ModelArmorer(), "armorer.png");
        registerModEntityRenderer(NexusBladesmith.class, new ModelBladesmith(), "bladesmith.png");
        registerModEntityRenderer(NexusSpecialTrader.class, new ModelNexusSaiyan(), "nexus_saiyan.png");
        registerModEntityRenderer(EntityGoldenPillar.class, new ModelGoldenPillar(), "golden_pillar.png");
        registerModEntityRenderer(EntityGoldenBoss.class, RenderStatueOfNirvana::new);
        registerModEntityRenderer(EntityMaelstromWitch.class, new ModelMaelstromWitch(), "maelstrom_witch.png");
        registerModEntityRenderer(EntitySwampCrawler.class, new ModelSwampCrawler(), "swamp_crawler.png");
        registerModEntityRenderer(EntityIronShade.class, new ModelIronShade(), "iron_shade.png", null, null, "iron_shade_crimson.png");
        registerModEntityRenderer(EntityCliffFly.class, new ModelCliffFly(), "cliff_fly.png");
        registerModEntityRenderer(EntityAzureVillager.class, RenderAzureVillager::new);
        registerModEntityRenderer(EntityMaelstromIllager.class, RenderMaelstromIllager::new);
        registerModEntityRenderer(EntityAzureGolem.class, (manager) -> new RenderAzureGolem(manager, "azure_golem.png"));
        registerModEntityRenderer(EntityCliffGolem.class, (manager) -> new RenderAzureGolem(manager, "cliff_golem.png"));
        registerModEntityRenderer(EntityMaelstromBeast.class, RenderMaelstromBeast::new);
        registerModEntityRenderer(EntityMonolith.class, RenderMonolith::new);
        registerModEntityRenderer(EntityWhiteMonolith.class, RenderWhiteMonolith::new);
        registerModEntityRenderer(EntityMaelstromLancer.class, new ModelMaelstromLancer(), "maelstrom_lancer.png", "maelstrom_lancer_azure.png", "maelstrom_lancer_golden.png", "maelstrom_lancer_crimson.png");
        registerModEntityRenderer(EntityChaosKnight.class, (manager) -> new RenderChaosKnight(manager, "chaos_knight.png"));
        registerModEntityRenderer(EntityMaelstromHealer.class, new ModelMaelstromHealer(), "maelstrom_healer.png");
        registerModEntityRenderer(EntityMaelstromGauntlet.class, (manager) -> new RenderMaelstromGauntlet(manager, "maelstrom_gauntlet.png"));
        registerModEntityRenderer(EntityTuningForkLazer.class, RenderTuningForkLazer::new);
        registerModEntityRenderer(ProjectileEntityMegaFireball.class, (manager) -> new RenderNonLivingEntity<>(manager, "fireball.png", new ModelFireball(), -1.501F));
        registerModEntityRenderer(EntityMaelstromStatueOfNirvana.class, new ModelStatueOfNirvana(), "maelstrom_statue.png");
        registerModEntityRenderer(ProjectileEntityHomingFlame.class, (manager) -> new RenderNonLivingEntity<>(manager, "homing_fireball.png", new ModelHomingFlame(), -0.2F));
        registerModEntityRenderer(EntityAlternativeMaelstromGauntletStage2.class, new ModelMaelstromGauntlet(), "maelstrom_gauntlet_stage_2.png");
        registerModEntityRenderer(EntityMaelstromFury.class, new ModelMaelstromFury(), "maelstrom_fury.png");
        registerModEntityRenderer(EntityAlternativeMaelstromGauntletStage1.class, new ModelMaelstromGauntlet(), "maelstrom_gauntlet.png");
        registerModEntityRenderer(EntityCrimsonCrystal.class, (manager) -> new RenderCrimsonCrystal(manager, "crystal.png", -0.5f));

        registerProjectileRenderer(ProjectileEntity.class);
        registerProjectileRenderer(ProjectileEntityBullet.class);
        registerProjectileRenderer(EntityPortalSpawn.class);
        registerProjectileRenderer(EntityNexusParticleSpawner.class);
        registerProjectileRenderer(ProjectileEntitySwampSpittle.class, ModItems.SWAMP_SLIME);
        registerProjectileRenderer(EntityParticleSpawner.class);
        registerProjectileRenderer(ProjectileEntityBone.class, Items.BONE);
        registerProjectileRenderer(ProjectileEntityHorrorAttack.class, ModItems.MAELSTROM_PELLET);
        registerProjectileRenderer(ProjectileEntityFireball.class, Items.FIRE_CHARGE);
        registerProjectileRenderer(ProjectileEntityBlackFireball.class, Items.FIRE_CHARGE);
        registerProjectileRenderer(ProjectileEntityGoldenFireball.class, Items.FIRE_CHARGE);
        registerProjectileRenderer(ProjectileEntityMonolithFireball.class, Items.FIRE_CHARGE);
        registerProjectileRenderer(ProjectileEntityGoldenMissile.class, ModItems.GOLD_PELLET);
        registerProjectileRenderer(EntityCrimsonTowerSpawner.class);
        registerProjectileRenderer(EntityHealerOrb.class);
        registerProjectileRenderer(ProjectileEntityChaosFireball.class, ModItems.CRIMSON_PELLET);
        registerProjectileRenderer(ProjectileEntityStatueMaelstromMissile.class, ModItems.MAELSTROM_PELLET);
        registerProjectileRenderer(ProjectileEntityBeastFireball.class, Items.FIRE_CHARGE);
        registerProjectileRenderer(EntityMaelstromTowerDestroyer.class);
    }

    /**
     * Registers an entity with a model and sets it up for rendering
     */
    private static <T extends EntityLiving, U extends ModelBase> void registerModEntityRenderer(Class<T> entityClass, U model, String... textures) {
        registerModEntityRenderer(entityClass, (manager) -> new RenderModEntity<>(manager, model, textures));
    }

    private static <T extends Entity> void registerModEntityRenderer(Class<T> entityClass, Function<RenderManager, Render<? super T>> renderClass) {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, renderClass::apply);
    }

    private static <T extends Entity> void registerProjectileRenderer(Class<T> projectileClass) {
        registerProjectileRenderer(projectileClass, null);
    }

    /**
     * Makes a projectile render with the given item
     *
     * @param projectileClass
     */
    private static <T extends Entity> void registerProjectileRenderer(Class<T> projectileClass, Item item) {
        RenderingRegistry.registerEntityRenderingHandler(projectileClass, manager -> new RenderProjectile<>(manager, Minecraft.getMinecraft().getRenderItem(), item));
    }
}
