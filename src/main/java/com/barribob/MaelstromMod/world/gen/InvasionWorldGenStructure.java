package com.barribob.MaelstromMod.world.gen;

import com.barribob.MaelstromMod.IntoTheMaelstrom;
import com.barribob.MaelstromMod.entity.entities.EntityMonolith;
import com.barribob.MaelstromMod.entity.entities.EntityShade;
import com.barribob.MaelstromMod.entity.tileentity.MobSpawnerLogic;
import com.barribob.MaelstromMod.entity.tileentity.TileEntityMobSpawner;
import com.barribob.MaelstromMod.init.ModBlocks;
import com.barribob.MaelstromMod.init.ModEntities;
import com.barribob.MaelstromMod.util.Element;
import com.barribob.MaelstromMod.util.handlers.LevelHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.Random;

public class InvasionWorldGenStructure extends WorldGenStructure {

    public static String name = "invasion/invasion_tower";

    public List<Template> templates;

    /**
     */
    public InvasionWorldGenStructure() {
        super(null);
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        this.generateStructure(worldIn, position, Rotation.NONE);
        return true;
    }

    protected Template getTemplate(World world) {
        if (templates != null) {
            return templates.get(world.rand.nextInt(templates.size()));
        }

        MinecraftServer mcServer = world.getMinecraftServer();
        TemplateManager manager = ((WorldServer)world).getStructureTemplateManager();
        int i = 0;
        String tempName = name + i;
        ResourceLocation location = new ResourceLocation(IntoTheMaelstrom.MOD_ID, tempName);
        Template temp = manager.get(mcServer, location);
        if (temp == null) {
            LogManager.getLogger().debug("The template, " + location + " could not be loaded");
        }
        while (temp != null) {
            tempName = name + i;
            location = new ResourceLocation(IntoTheMaelstrom.MOD_ID, tempName);
            temp = manager.get(mcServer, location);
            if (temp != null) {
                templates.add(temp);
            }
            i++;
        }
        LogManager.getLogger().debug(templates.size() +" towers loaded");
        return templates.get(world.rand.nextInt(templates.size()));
    }

    @Override
    protected void handleDataMarker(String function, BlockPos pos, World worldIn, Random rand) {
        if (function.startsWith("boss")) {
            worldIn.setBlockToAir(pos);
            EntityMonolith entity = new EntityMonolith(worldIn);
            entity.setPosition(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
            worldIn.spawnEntity(entity);
        } else if (function.startsWith("scout")) {
            worldIn.setBlockState(pos, ModBlocks.BOSS_SPAWNER.getDefaultState(), 2);
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityMobSpawner) {
                ((TileEntityMobSpawner) tileentity).getSpawnerBaseLogic().setData(new MobSpawnerLogic.MobSpawnData(ModEntities.getID(EntityShade.class), Element.NONE), 1, LevelHandler.INVASION, 8);
            }
        }
    }
}
