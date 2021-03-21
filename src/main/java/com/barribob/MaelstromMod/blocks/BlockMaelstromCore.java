package com.barribob.MaelstromMod.blocks;

import com.barribob.MaelstromMod.Main;
import com.barribob.MaelstromMod.entity.tileentity.TileEntityMalestromSpawner;
import com.barribob.MaelstromMod.init.ModBlocks;
import com.barribob.MaelstromMod.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * The maelstrom mob spawner. Also prevent decay of the maelstrom block
 */
public class BlockMaelstromCore extends Block {
    private Item itemDropped;

    public BlockMaelstromCore(String name, Material material, Item itemDropped) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);

        // Add both an item as a block and the block itself
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
        this.itemDropped = itemDropped;
    }

    public BlockMaelstromCore(String name, Material material, float hardness, float resistance, SoundType soundType, Item itemDropped) {
        this(name, material, itemDropped);
        setHardness(hardness);
        setResistance(resistance);
        setSoundType(soundType);
    }

    /**
     * Tile Entity methods to make the tile entity spawner work
     */
    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityMalestromSpawner();
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return itemDropped;
    }
}
