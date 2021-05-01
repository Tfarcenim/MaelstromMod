package com.barribob.intothemaelstrom.items;

import com.barribob.intothemaelstrom.IntoTheMaelstrom;
import com.barribob.intothemaelstrom.config.ModConfig;
import com.barribob.intothemaelstrom.mana.IMana;
import com.barribob.intothemaelstrom.mana.ManaProvider;
import com.barribob.intothemaelstrom.packets.MessageMana;
import com.barribob.intothemaelstrom.packets.MessageManaUnlock;
import com.barribob.intothemaelstrom.util.ModUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemCatalyst extends Item {

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote && playerIn instanceof EntityPlayerMP) {
            IMana mana = playerIn.getCapability(ManaProvider.MANA, null);
            if (mana.isLocked()) {
                mana.setLocked(false);
                mana.set(ModConfig.misc.mana_capacity); // Fill the mana bar initially
                IntoTheMaelstrom.network.sendTo(new MessageManaUnlock(), (EntityPlayerMP) playerIn); // Spawn celebratory particles
                IntoTheMaelstrom.network.sendTo(new MessageMana(mana.getMana()), (EntityPlayerMP) playerIn); // Update the mana bar
                playerIn.playSound(SoundEvents.ENTITY_ILLAGER_CAST_SPELL, 1.0F, 0.4F / (worldIn.rand.nextFloat() * 0.4F + 0.8F));

                if (!playerIn.capabilities.isCreativeMode) {
                    itemstack.shrink(1);
                    return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
                }
            }
        }
        return new ActionResult<>(EnumActionResult.PASS, itemstack);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.GRAY + ModUtils.translateDesc("catalyst"));
    }
}
