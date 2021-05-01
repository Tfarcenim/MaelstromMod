package com.barribob.intothemaelstrom.blocks;

import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockFenceBase extends BlockFence {

    public BlockFenceBase(Material materialIn, MapColor mapColorIn) {
        super(materialIn, mapColorIn);
    }

    public BlockFenceBase(String name, Material material) {
        super(material, MapColor.WOOD);
        setUnlocalizedName(name);
        setRegistryName(name);
    }

    public BlockFenceBase(String name, Material material, float hardness, float resistance, SoundType soundType) {
        this(name, material);
        setHardness(hardness);
        setResistance(resistance);
        setSoundType(soundType);
    }
}
