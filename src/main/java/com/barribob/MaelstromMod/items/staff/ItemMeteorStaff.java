package com.barribob.MaelstromMod.items.staff;

import com.barribob.MaelstromMod.config.ModConfig;
import com.barribob.MaelstromMod.entity.projectile.Projectile;
import com.barribob.MaelstromMod.entity.projectile.ProjectileMeteorSpawner;
import com.barribob.MaelstromMod.util.ModUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemMeteorStaff extends ItemStaff {
    public ItemMeteorStaff(int useTime, float level) {
        super(useTime, level);
    }

    public float getBaseDamage() {
        return 10 * ModConfig.balance.weapon_damage;
    }

    @Override
    protected void shoot(World world, EntityPlayer player, EnumHand handIn, ItemStack stack) {
        float inaccuracy = 0.0f;
        float velocity = 3f;

        Projectile projectile = new ProjectileMeteorSpawner(world, player, ModUtils.getEnchantedDamage(stack, this.getLevel(), getBaseDamage()), stack);
        projectile.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, velocity, inaccuracy);
        projectile.setTravelRange(50);

        world.spawnEntity(projectile);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(ModUtils.getDamageTooltip(ModUtils.getEnchantedDamage(stack, this.getLevel(), getBaseDamage())));
        tooltip.add(TextFormatting.GRAY + ModUtils.translateDesc("meteor_staff"));
    }

    @Override
    public boolean doesDamage() {
        return true;
    }
}
