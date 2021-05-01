package com.barribob.intothemaelstrom.mana;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ManaProvider implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(IMana.class)
    public static final Capability<IMana> MANA = null;

    private final IMana instance = MANA.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return MANA == capability;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return MANA == capability ? MANA.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return MANA.getStorage().writeNBT(MANA, instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        MANA.getStorage().readNBT(MANA, instance, null, nbt);
    }
}
