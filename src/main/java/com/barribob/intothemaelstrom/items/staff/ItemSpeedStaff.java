package com.barribob.intothemaelstrom.items.staff;

import com.barribob.intothemaelstrom.util.ModUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemSpeedStaff extends ItemStaff {
    public ItemSpeedStaff(int maxDamage, float level) {
        super(maxDamage, level);
    }

    @Override
    protected void shoot(World world, EntityPlayer player, EnumHand handIn, ItemStack stack) {
        world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.NEUTRAL, 0.5F,
                0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 180, 2));
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(TextFormatting.GRAY + ModUtils.translateDesc("speed_staff"));
    }
}
