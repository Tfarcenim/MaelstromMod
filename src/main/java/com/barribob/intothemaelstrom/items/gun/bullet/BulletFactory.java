package com.barribob.intothemaelstrom.items.gun.bullet;

import com.barribob.intothemaelstrom.entity.projectile.ProjectileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface BulletFactory {
    ProjectileEntity get(World world, EntityPlayer player, ItemStack stack, float damage);
}
