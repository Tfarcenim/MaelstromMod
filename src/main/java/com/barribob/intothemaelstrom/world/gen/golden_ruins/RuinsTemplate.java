package com.barribob.intothemaelstrom.world.gen.golden_ruins;

import com.barribob.intothemaelstrom.entity.entities.*;
import com.barribob.intothemaelstrom.entity.tileentity.MobSpawnerLogic.MobSpawnData;
import com.barribob.intothemaelstrom.entity.tileentity.TileEntityMobSpawner;
import com.barribob.intothemaelstrom.init.ModBlocks;
import com.barribob.intothemaelstrom.init.ModEntities;
import com.barribob.intothemaelstrom.util.Element;
import com.barribob.intothemaelstrom.util.ModRandom;
import com.barribob.intothemaelstrom.util.handlers.LevelHandler;
import com.barribob.intothemaelstrom.util.handlers.LootTableHandler;
import com.barribob.intothemaelstrom.world.gen.ModStructureTemplate;
import com.barribob.intothemaelstrom.world.gen.WorldGenStructure;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

/**
 * The specific template used for generating the maelstrom fortress
 */
public class RuinsTemplate extends ModStructureTemplate {
    public RuinsTemplate() {
    }

    public RuinsTemplate(TemplateManager manager, String type, BlockPos pos, Rotation rotation, int distance, boolean overwriteIn) {
        super(manager, type, pos, distance, rotation, overwriteIn);
    }

    /**
     * Loads structure block data markers and handles them by their name
     */
    @Override
    protected void handleDataMarker(String function, BlockPos pos, World worldIn, Random rand, StructureBoundingBox sbb) {
        if (function.startsWith("chest")) {
            worldIn.setBlockToAir(pos);
            BlockPos blockpos = pos.down();
            if (rand.nextFloat() < 0.3) {
                if (sbb.isVecInside(blockpos)) {
                    TileEntity tileentity = worldIn.getTileEntity(blockpos);

                    if (tileentity instanceof TileEntityChest) {
                        ((TileEntityChest) tileentity).setLootTable(LootTableHandler.GOLDEN_RUINS, rand.nextLong());
                    }
                }
            } else {
                worldIn.setBlockToAir(blockpos);
            }
        } else if (function.startsWith("final_chest")) {
            worldIn.setBlockToAir(pos);
            BlockPos blockpos = pos.down();

            if (sbb.isVecInside(blockpos)) {
                TileEntity tileentity = worldIn.getTileEntity(blockpos);

                if (tileentity instanceof TileEntityChest) {
                    ((TileEntityChest) tileentity).setLootTable(LootTableHandler.GOLDEN_RUINS_BOSS, rand.nextLong());
                }
            }
        } else if (function.startsWith("mob")) {
            if (rand.nextFloat() > 0.3) {
                worldIn.setBlockState(pos, ModBlocks.DISAPPEARING_SPAWNER.getDefaultState(), 2);
                TileEntity tileentity = worldIn.getTileEntity(pos);

                if (tileentity instanceof TileEntityMobSpawner) {
                    ((TileEntityMobSpawner) tileentity).getSpawnerBaseLogic().setData(
                            new MobSpawnData[]{
                                    new MobSpawnData(ModEntities.getID(EntityMaelstromMage.class), new Element[]{Element.NONE, Element.GOLDEN}, new int[]{3, 1}, 1),
                                    new MobSpawnData(ModEntities.getID(EntityMaelstromLancer.class), new Element[]{Element.NONE, Element.GOLDEN}, new int[]{3, 1}, 1),
                                    new MobSpawnData(ModEntities.getID(EntityShade.class), new Element[]{Element.NONE, Element.GOLDEN}, new int[]{3, 1}, 1),
                                    new MobSpawnData(ModEntities.getID(EntityGoldenPillar.class), Element.GOLDEN, 2)
                            },
                            new int[]{1, 1, 1, 1},
                            4,
                            LevelHandler.CLIFF_ENDGAME,
                            20);
                }
            } else {
                worldIn.setBlockToAir(pos);
            }
        } else if (function.startsWith("boss")) {
            worldIn.setBlockState(pos, ModBlocks.BOSS_SPAWNER.getDefaultState(), 2);
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityMobSpawner) {
                ((TileEntityMobSpawner) tileentity).getSpawnerBaseLogic().setData(new MobSpawnData(ModEntities.getID(EntityGoldenBoss.class), Element.GOLDEN), 1, LevelHandler.CLIFF_ENDGAME, 16);
            }
        } else if (function.startsWith("lava")) {
            if (rand.nextInt(3) == 0) {
                WorldGenStructure lavaPool = new WorldGenStructure("golden_ruins/" + ModRandom.choice(new String[]{"lava_fountain", "lava_pool", "lava_statue"}));
                lavaPool.generate(worldIn, rand, pos.add(new BlockPos(-2, -2, -2)));
            } else {
                worldIn.setBlockToAir(pos);
            }
        }
    }

    @Override
    public String templateLocation() {
        return "golden_ruins";
    }
}
