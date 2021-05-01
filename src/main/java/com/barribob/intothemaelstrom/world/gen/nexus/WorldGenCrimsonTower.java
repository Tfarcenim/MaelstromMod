package com.barribob.intothemaelstrom.world.gen.nexus;

import com.barribob.intothemaelstrom.entity.entities.EntityChaosKnight;
import com.barribob.intothemaelstrom.entity.entities.EntityIronShade;
import com.barribob.intothemaelstrom.entity.entities.EntityShade;
import com.barribob.intothemaelstrom.entity.tileentity.MobSpawnerLogic.MobSpawnData;
import com.barribob.intothemaelstrom.entity.tileentity.TileEntityMobSpawner;
import com.barribob.intothemaelstrom.init.ModBlocks;
import com.barribob.intothemaelstrom.init.ModEntities;
import com.barribob.intothemaelstrom.util.Element;
import com.barribob.intothemaelstrom.util.handlers.LevelHandler;
import com.barribob.intothemaelstrom.world.gen.WorldGenStructure;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

import java.util.Random;

public class WorldGenCrimsonTower extends WorldGenStructure {
    public WorldGenCrimsonTower() {
        super("nexus/crimson_tower");
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        this.generateStructure(worldIn, position, Rotation.CLOCKWISE_180);
        return true;
    }

    @Override
    protected void handleDataMarker(String function, BlockPos pos, World worldIn, Random rand) {
        worldIn.setBlockToAir(pos);
        if (function.startsWith("chest")) {
            worldIn.setBlockToAir(pos);
            BlockPos blockpos = pos.down();

            TileEntity tileentity = worldIn.getTileEntity(blockpos);

            if (tileentity instanceof TileEntityChest) {
                ((TileEntityChest) tileentity).setLootTable(LootTableList.CHESTS_STRONGHOLD_LIBRARY, rand.nextLong());
            }
        } else if (function.startsWith("scout")) {
            worldIn.setBlockState(pos, ModBlocks.BOSS_SPAWNER.getDefaultState());
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityMobSpawner) {
                ((TileEntityMobSpawner) tileentity).getSpawnerBaseLogic().setData(
                        new MobSpawnData[]{
                                new MobSpawnData(ModEntities.getID(EntityShade.class), new Element[]{Element.CRIMSON, Element.NONE}, new int[]{1, 3}, 1)
                        },
                        new int[]{1},
                        2,
                        LevelHandler.CRIMSON_START,
                        15);
            }
        } else if (function.startsWith("exe")) {
            worldIn.setBlockState(pos, ModBlocks.BOSS_SPAWNER.getDefaultState());
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityMobSpawner) {
                ((TileEntityMobSpawner) tileentity).getSpawnerBaseLogic().setData(
                        new MobSpawnData[]{
                                new MobSpawnData(ModEntities.getID(EntityIronShade.class), new Element[]{Element.CRIMSON}, new int[]{1}, 1)
                        },
                        new int[]{1},
                        1,
                        LevelHandler.CLIFF_ENDGAME,
                        8);
            }
        } else if (function.startsWith("boss")) {
            worldIn.setBlockState(pos, ModBlocks.BOSS_SPAWNER.getDefaultState());
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityMobSpawner) {
                ((TileEntityMobSpawner) tileentity).getSpawnerBaseLogic().setData(
                        new MobSpawnData[]{
                                new MobSpawnData(ModEntities.getID(EntityChaosKnight.class), new Element[]{Element.CRIMSON}, new int[]{1}, 1)
                        },
                        new int[]{1},
                        1,
                        LevelHandler.CLIFF_ENDGAME,
                        20);
            }
        }
    }
}
