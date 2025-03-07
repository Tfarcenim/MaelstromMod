package com.barribob.intothemaelstrom.entity.tileentity;

import com.barribob.intothemaelstrom.util.IBlockUpdater;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

import javax.annotation.Nullable;

public class TileEntityUpdater extends TileEntity implements ITickable {

    @Override
    public void update() {
        if (world.isRemote && this.getBlockType() instanceof IBlockUpdater) {
            ((IBlockUpdater) this.getBlockType()).update(world, pos);
        }
    }

    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 1, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }
}
