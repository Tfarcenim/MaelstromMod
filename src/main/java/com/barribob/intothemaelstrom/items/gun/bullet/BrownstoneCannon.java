package com.barribob.intothemaelstrom.items.gun.bullet;

import com.barribob.intothemaelstrom.entity.projectile.ProjectileEntity;
import com.barribob.intothemaelstrom.entity.projectile.ProjectileEntityBrownstoneCannon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BrownstoneCannon implements BulletFactory {
    @Override
    public ProjectileEntity get(World world, EntityPlayer player, ItemStack stack, float damage) {
        return new ProjectileEntityBrownstoneCannon(world, player, damage, stack);
    }
}
