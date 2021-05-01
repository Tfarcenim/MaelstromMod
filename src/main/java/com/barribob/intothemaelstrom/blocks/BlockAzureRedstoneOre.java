package com.barribob.intothemaelstrom.blocks;

import com.barribob.intothemaelstrom.init.ModBlocks;
import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Replicates redstone ore for the azure dimension
 */
public class BlockAzureRedstoneOre extends BlockRedstoneOre {
    public BlockAzureRedstoneOre(String name) {
        super(false);
        setUnlocalizedName(name);
        setRegistryName(name);
    }

    public BlockAzureRedstoneOre(String name, float hardness, float resistance, SoundType soundType) {
        this(name);
        setHardness(hardness);
        setResistance(resistance);
        setSoundType(soundType);
    }

    /**
     * Called when the block is right clicked by a player.
     */
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        this.activate(worldIn, pos);
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    /**
     * Called when the given entity walks on this Block
     */
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        this.activate(worldIn, pos);
        super.onEntityWalk(worldIn, pos, entityIn);
    }

    private void activate(World worldIn, BlockPos pos) {
        if (this == ModBlocks.AZURE_REDSTONE_ORE) {
            worldIn.setBlockState(pos, ModBlocks.AZURE_LIT_REDSTONE_ORE.getDefaultState());
        }
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (this == Blocks.LIT_REDSTONE_ORE) {
            worldIn.setBlockState(pos, ModBlocks.AZURE_REDSTONE_ORE.getDefaultState());
        }
    }

    protected ItemStack getSilkTouchDrop(IBlockState state) {
        return new ItemStack(ModBlocks.AZURE_REDSTONE_ORE);
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(ModBlocks.AZURE_REDSTONE_ORE), 1, this.damageDropped(state));
    }
}
