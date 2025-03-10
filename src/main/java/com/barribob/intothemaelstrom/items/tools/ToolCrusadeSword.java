package com.barribob.intothemaelstrom.items.tools;

import com.barribob.intothemaelstrom.items.ISweepAttackOverride;
import com.barribob.intothemaelstrom.util.ModUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ToolCrusadeSword extends ToolSword implements ISweepAttackOverride {
    public ToolCrusadeSword(ToolMaterial material, float level) {
        super(material, level);
    }

    @Override
    public void doSweepAttack(EntityPlayer player, EntityLivingBase target) {
        ModUtils.doSweepAttack(player, target, getElement(), (e) -> {
        });
        player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 40, 0));
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(TextFormatting.GRAY + ModUtils.translateDesc("crusade_sword"));
    }
}
