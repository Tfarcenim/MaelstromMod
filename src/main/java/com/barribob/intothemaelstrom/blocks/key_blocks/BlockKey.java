package com.barribob.intothemaelstrom.blocks.key_blocks;

import com.barribob.intothemaelstrom.blocks.BlockBase;
import com.barribob.intothemaelstrom.entity.tileentity.TileEntityUpdater;
import com.barribob.intothemaelstrom.entity.util.EntityAzurePortalSpawn;
import com.barribob.intothemaelstrom.init.ModCreativeTabs;
import com.barribob.intothemaelstrom.util.IBlockUpdater;
import com.barribob.intothemaelstrom.util.ModColors;
import com.barribob.intothemaelstrom.util.ModUtils;
import com.barribob.intothemaelstrom.util.handlers.ParticleManager;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class BlockKey extends BlockBase implements IBlockUpdater, ITileEntityProvider {
    private final Supplier<Item> activationItem;
    protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.25D, 1.0D);
    int counter = 0;
    BiFunction<World, BlockPos, Entity> spawnPortal;

    public BlockKey(String name) {
        this(name, null, (world, pos) -> new EntityAzurePortalSpawn(world, pos.getX(), pos.getY(), pos.getZ()));
    }

    public BlockKey(String name, Supplier<Item> activationItem, BiFunction<World, BlockPos, Entity> spawnPortal) {
        super(name, Material.ROCK, 1000, 10000, SoundType.STONE);
        this.setBlockUnbreakable();
        this.activationItem = activationItem;
        this.hasTileEntity = true;
        this.spawnPortal = spawnPortal;
        this.setCreativeTab(ModCreativeTabs.BLOCKS);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AABB;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public void update(World world, BlockPos pos) {
        counter++;
        if (counter % 5 == 0) {
            List<EntityPlayerSP> list = world.<EntityPlayerSP>getPlayers(EntityPlayerSP.class, player -> player.getHeldItem(EnumHand.MAIN_HAND).getItem() == activationItem);

            if (list.size() > 0) {
                ModUtils.performNTimes(50, (i) -> {
                    ParticleManager.spawnFirework(world, new Vec3d(pos).add(new Vec3d(0.5, 1 + i, 0.5)), ModColors.WHITE, ModUtils.yVec(-0.1f));
                });
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityUpdater();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        super.breakBlock(worldIn, pos, state);
        worldIn.removeTileEntity(pos);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY,
                                    float hitZ) {
        if (playerIn.getHeldItemMainhand() != null && playerIn.getHeldItemMainhand().getItem() == this.activationItem) {
            worldIn.spawnEntity(this.spawnPortal.apply(worldIn, pos));
            worldIn.setBlockToAir(pos);
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
}
