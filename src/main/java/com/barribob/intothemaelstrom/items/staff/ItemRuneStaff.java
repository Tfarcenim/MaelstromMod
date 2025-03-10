package com.barribob.intothemaelstrom.items.staff;

import com.barribob.intothemaelstrom.config.ModConfig;
import com.barribob.intothemaelstrom.entity.projectile.ProjectileEntityRuneWisp;
import com.barribob.intothemaelstrom.init.ModItems;
import com.barribob.intothemaelstrom.util.ModUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemRuneStaff extends ItemStaff {
    public ItemRuneStaff(float level) {
        super(ModItems.STAFF_USE_TIME, level);
    }

    public float getBaseDamage() {
        return 7f * ModConfig.balance.weapon_damage;
    }

    @Override
    protected void shoot(World world, EntityPlayer player, EnumHand handIn, ItemStack stack) {
        world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_BLAZE_AMBIENT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        float inaccuracy = 0.0f;
        float speed = 1f;

        ProjectileEntityRuneWisp projectile = new ProjectileEntityRuneWisp(world, player, ModUtils.getEnchantedDamage(stack, getLevel(), getBaseDamage()), stack);
        projectile.setElement(getElement());
        projectile.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, speed, inaccuracy);
        projectile.setTravelRange(9f);

        world.spawnEntity(projectile);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(ModUtils.getDamageTooltip(ModUtils.getEnchantedDamage(stack, this.getLevel(), getBaseDamage())));
        tooltip.add(TextFormatting.GRAY + ModUtils.translateDesc("rune_staff"));
    }

    @Override
    public boolean doesDamage() {
        return true;
    }
}
