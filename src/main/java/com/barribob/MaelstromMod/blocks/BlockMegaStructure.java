package com.barribob.MaelstromMod.blocks;

import com.barribob.MaelstromMod.entity.tileentity.TileEntityMegaStructure;
import com.barribob.MaelstromMod.init.ModBlocks;
import com.barribob.MaelstromMod.init.ModItems;
import net.minecraft.block.BlockStructure;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * A structure block that lets me save structures larger than 32 x 32 x 32
 * It is hardcoded at 100 x 100 x 100 because trying to make it work properly is
 * not worth the effort for what I'm using it for.
 */
public class BlockMegaStructure extends BlockStructure {
    public BlockMegaStructure(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityMegaStructure();
    }
}
