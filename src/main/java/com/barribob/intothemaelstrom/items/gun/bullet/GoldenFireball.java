package com.barribob.intothemaelstrom.items.gun.bullet;

import com.barribob.intothemaelstrom.entity.projectile.ProjectileEntity;
import com.barribob.intothemaelstrom.entity.projectile.ProjectileEntityGoldenFireball;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GoldenFireball implements BulletFactory {
    @Override
    public ProjectileEntity get(World world, EntityPlayer player, ItemStack stack, float damage) {
        return new ProjectileEntityGoldenFireball(world, player, damage, stack);
    }
}
