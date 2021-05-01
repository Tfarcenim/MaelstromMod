package com.barribob.intothemaelstrom.blocks;

import com.barribob.intothemaelstrom.entity.tileentity.TileEntityBossSpawner;
import com.barribob.intothemaelstrom.entity.tileentity.TileEntityDisappearingSpawner;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Spawns maelstrom mobs, and then disappears, like a one-time mob spawner
 */
public class BlockDisappearingSpawner extends Block {
    private final boolean boss;

    public BlockDisappearingSpawner(String name, Material material, boolean boss) {
        super(material);
        this.boss = boss;
        setUnlocalizedName(name);
        setRegistryName(name);
        this.setSoundType(SoundType.STONE);
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
        return boss ? new TileEntityBossSpawner() : new TileEntityDisappearingSpawner();
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }
}
