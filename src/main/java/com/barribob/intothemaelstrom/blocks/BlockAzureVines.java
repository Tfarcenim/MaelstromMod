package com.barribob.intothemaelstrom.blocks;

import com.barribob.intothemaelstrom.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockAzureVines extends BlockVine {
    public BlockAzureVines(String name) {
        super();
        setUnlocalizedName(name);
        setRegistryName(name);
    }

    public BlockAzureVines(String name, float hardness, float resistance, SoundType soundType) {
        this(name);
        setHardness(hardness);
        setResistance(resistance);
        setSoundType(soundType);
    }

    public boolean canAttachTo(World world, BlockPos pos, EnumFacing direction) {
        Block block = world.getBlockState(pos.up()).getBlock();
        return this.isAcceptableNeighbor(world, pos.offset(direction.getOpposite()), direction) && (block == Blocks.AIR || block == ModBlocks.AZURE_VINES || this.isAcceptableNeighbor(world, pos.up(), EnumFacing.UP));
    }

    private boolean isAcceptableNeighbor(World p_193396_1_, BlockPos p_193396_2_, EnumFacing p_193396_3_) {
        IBlockState iblockstate = p_193396_1_.getBlockState(p_193396_2_);
        return iblockstate.getBlockFaceShape(p_193396_1_, p_193396_2_, p_193396_3_) == BlockFaceShape.SOLID && !isExceptBlockForAttaching(iblockstate.getBlock());
    }
}
