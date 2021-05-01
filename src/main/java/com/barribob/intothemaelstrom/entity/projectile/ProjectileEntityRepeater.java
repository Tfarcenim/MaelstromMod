package com.barribob.intothemaelstrom.entity.projectile;

import com.barribob.intothemaelstrom.util.Element;
import com.barribob.intothemaelstrom.util.ModRandom;
import com.barribob.intothemaelstrom.util.handlers.ParticleManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ProjectileEntityRepeater extends ProjectileEntityBullet {
    public ProjectileEntityRepeater(World worldIn, EntityLivingBase throwerIn, float baseDamage, ItemStack stack) {
        super(worldIn, throwerIn, baseDamage, stack);
    }

    public ProjectileEntityRepeater(World worldIn) {
        super(worldIn);
    }

    public ProjectileEntityRepeater(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    protected void spawnParticles() {
        if (this.getElement() == Element.NONE) {
            world.spawnParticle(EnumParticleTypes.REDSTONE, this.posX, this.posY, this.posZ, 0, 0, 0);
        } else {
            ParticleManager.spawnDust(world, getPositionVector(), this.getElement().particleColor, Vec3d.ZERO, ModRandom.range(10, 15));
        }
    }
}