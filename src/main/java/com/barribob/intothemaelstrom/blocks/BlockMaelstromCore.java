package com.barribob.intothemaelstrom.blocks;

import com.barribob.intothemaelstrom.entity.tileentity.TileEntityMalestromSpawner;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

/**
 * The maelstrom mob spawner. Also prevent decay of the maelstrom block
 */
public class BlockMaelstromCore extends Block {
    private final Supplier<Item> itemDropped;

    public BlockMaelstromCore( Material material, Supplier<Item> itemDropped) {
        super(material);
        this.itemDropped = itemDropped;
    }

    public BlockMaelstromCore(Material material, SoundType soundType, Supplier<Item> itemDropped) {
        this(material, itemDropped);
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
        return itemDropped.get();
    }
}
