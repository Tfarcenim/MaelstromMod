package com.barribob.intothemaelstrom.items.gun.bullet;

import com.barribob.intothemaelstrom.entity.projectile.ProjectileEntity;
import com.barribob.intothemaelstrom.entity.projectile.ProjectileEntityMaelstromCannon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MaelstromCannon implements BulletFactory {
    @Override
    public ProjectileEntity get(World world, EntityPlayer player, ItemStack stack, float damage) {
        return new ProjectileEntityMaelstromCannon(world, player, damage, stack);
    }
}
