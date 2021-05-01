package com.barribob.intothemaelstrom.world.gen.maelstrom_fortress;

import com.barribob.intothemaelstrom.entity.entities.EntityHorror;
import com.barribob.intothemaelstrom.entity.entities.EntityMaelstromIllager;
import com.barribob.intothemaelstrom.entity.entities.EntityMaelstromMage;
import com.barribob.intothemaelstrom.entity.entities.EntityShade;
import com.barribob.intothemaelstrom.entity.tileentity.MobSpawnerLogic.MobSpawnData;
import com.barribob.intothemaelstrom.entity.tileentity.TileEntityMobSpawner;
import com.barribob.intothemaelstrom.init.ModBlocks;
import com.barribob.intothemaelstrom.init.ModEntities;
import com.barribob.intothemaelstrom.util.Element;
import com.barribob.intothemaelstrom.util.handlers.LevelHandler;
import com.barribob.intothemaelstrom.util.handlers.LootTableHandler;
import com.barribob.intothemaelstrom.world.gen.ModStructureTemplate;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTableList;

import java.util.Random;

/**
 * The specific template used for generating the maelstrom fortress
 */
public class FortressTemplate extends ModStructureTemplate {
    private int distance;

    public FortressTemplate() {
    }

    public FortressTemplate(TemplateManager manager, String type, int distance, BlockPos pos, Rotation rotation, boolean overwriteIn) {
        super(manager, type, pos, rotation, overwriteIn);
        this.distance = distance;
    }

    @Override
    public int getDistance() {
        return this.distance;
    }

    /**
     * Loads structure block data markers and handles them by their name
     */
    @Override
    protected void handleDataMarker(String function, BlockPos pos, World worldIn, Random rand, StructureBoundingBox sbb) {
        BlockPos blockpos = pos.down();
        if (function.startsWith("chest")) {
            if (rand.nextInt(3) == 0) {
                TileEntity tileentity = worldIn.getTileEntity(blockpos);

                if (tileentity instanceof TileEntityChest) {
                    ((TileEntityChest) tileentity).setLootTable(LootTableHandler.AZURE_FORTRESS, rand.nextLong());
                }
            } else {
                worldIn.setBlockToAir(blockpos);
            }
        } else if (function.startsWith("boss_chest")) {
            TileEntity tileentity = worldIn.getTileEntity(blockpos);

            if (tileentity instanceof TileEntityChest) {
                ((TileEntityChest) tileentity).setLootTable(LootTableHandler.AZURE_FORTRESS, rand.nextLong());
            }
        } else if (function.startsWith("book_chest")) {
            TileEntity tileentity = worldIn.getTileEntity(blockpos);

            if (tileentity instanceof TileEntityChest) {
                ((TileEntityChest) tileentity).setLootTable(LootTableList.CHESTS_STRONGHOLD_LIBRARY, rand.nextLong());
            }
        } else if (function.startsWith("blacksmith_chest")) {
            if (rand.nextInt(2) == 0) {
                TileEntity tileentity = worldIn.getTileEntity(blockpos);

                if (tileentity instanceof TileEntityChest) {
                    ((TileEntityChest) tileentity).setLootTable(LootTableHandler.AZURE_FORTRESS_FORGE, rand.nextLong());
                }
            } else {
                worldIn.setBlockToAir(blockpos);
            }
        } else if (function.startsWith("boss")) {
            EntityMaelstromIllager entity = new EntityMaelstromIllager(worldIn);
            entity.setPosition(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
            entity.setLevel(LevelHandler.AZURE_ENDGAME);
            worldIn.spawnEntity(entity);
        } else if (function.startsWith("enemy")) {
            worldIn.setBlockState(pos, ModBlocks.DISAPPEARING_SPAWNER.getDefaultState(), 2);
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityMobSpawner) {
                ((TileEntityMobSpawner) tileentity).getSpawnerBaseLogic().setData(
                        new MobSpawnData[]{
                                new MobSpawnData(ModEntities.getID(EntityShade.class), new Element[]{Element.AZURE, Element.NONE}, new int[]{1, 4}, 1),
                                new MobSpawnData(ModEntities.getID(EntityHorror.class), Element.NONE),
                                new MobSpawnData(ModEntities.getID(EntityMaelstromMage.class), new Element[]{Element.AZURE, Element.NONE}, new int[]{1, 4}, 1)
                        },
                        new int[]{1, 1, 1},
                        3,
                        LevelHandler.AZURE_ENDGAME,
                        16);
            }
        }
    }

    @Override
    public String templateLocation() {
        return "maelstrom_fortress";
    }
}
