package com.barribob.MaelstromMod.world.gen.cliff;

import java.util.Arrays;
import java.util.Random;

import com.barribob.MaelstromMod.entity.tileentity.TileEntityMobSpawner;
import com.barribob.MaelstromMod.init.ModBlocks;
import com.barribob.MaelstromMod.util.ModRandom;
import com.barribob.MaelstromMod.util.Reference;
import com.barribob.MaelstromMod.util.handlers.LootTableHandler;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenMaelstromCave extends WorldGenCliffLedge
{
    public WorldGenMaelstromCave()
    {
	super("cliff/maelstrom_cave", -6);
    }

    @Override
    protected void handleDataMarker(String function, BlockPos pos, World worldIn, Random rand)
    {
	if (function.startsWith("enemy"))
	{
	    worldIn.setBlockState(pos, ModBlocks.DISAPPEARING_SPAWNER.getDefaultState(), 2);
	    TileEntity tileentity = worldIn.getTileEntity(pos);

	    if (tileentity instanceof TileEntityMobSpawner)
	    {
		String[] enemies = { "golden_mage" };
		((TileEntityMobSpawner) tileentity).getSpawnerBaseLogic().setEntities(new ResourceLocation(Reference.MOD_ID + ":" + ModRandom.choice(enemies)), 2);
	    }
	}
	else if (function.startsWith("chest"))
	{
	    worldIn.setBlockToAir(pos);
	    pos = pos.down();
	    TileEntity tileentity = worldIn.getTileEntity(pos);

	    if (tileentity instanceof TileEntityChest)
	    {
		((TileEntityChest) tileentity).setLootTable(LootTableHandler.MAELSTROM_RUINS, rand.nextLong());
	    }
	}
	else
	{
	    if (isBlockNearby(worldIn, pos))
	    {
		worldIn.setBlockState(pos, ModBlocks.AZURE_MAELSTROM.getDefaultState());
	    }
	    else
	    {
		worldIn.setBlockToAir(pos);
	    }
	}
    };

    private boolean isBlockNearby(World world, BlockPos pos)
    {
	for (BlockPos dir : Arrays.asList(pos.up(), pos.east(), pos.west(), pos.north(), pos.south(), pos.down()))
	{
	    if (world.getBlockState(dir).getBlock() == ModBlocks.CLIFF_STONE)
	    {
		return true;
	    }
	}
	return false;
    }
}
