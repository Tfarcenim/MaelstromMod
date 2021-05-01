package com.barribob.intothemaelstrom.blocks;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;

public class BlockStairsBase extends BlockStairs {
    protected BlockStairsBase(IBlockState modelState) {
        super(modelState);
    }

    public BlockStairsBase(String name, IBlockState modelState) {
        super(modelState);
        setUnlocalizedName(name);
        setRegistryName(name);
    }

    public BlockStairsBase(String name, IBlockState modelState, float hardness, float resistance, SoundType soundType) {
        this(name, modelState);
        setHardness(hardness);
        setResistance(resistance);
        setSoundType(soundType);
    }
}
