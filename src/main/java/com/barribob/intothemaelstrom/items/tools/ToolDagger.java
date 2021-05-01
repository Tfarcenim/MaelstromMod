package com.barribob.intothemaelstrom.items.tools;

import com.barribob.intothemaelstrom.items.IExtendedReach;
import com.barribob.intothemaelstrom.items.ISweepAttackOverride;
import com.barribob.intothemaelstrom.util.ModUtils;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

/**
 * The dagger tool is a high damage, but short reached weapon
 */
public class ToolDagger extends ToolSword implements IExtendedReach, ISweepAttackOverride {
    private static final UUID REACH_MODIFIER = UUID.fromString("a6323e02-d8e9-44c6-b941-f5d7155bb406");

    public ToolDagger(ToolMaterial material, float level) {
        super(material, level);
    }

    /**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit
     * damage.
     */
    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(EntityPlayer.REACH_DISTANCE.getName(), new AttributeModifier(REACH_MODIFIER, "Extended Reach Modifier", -1.0D, 0).setSaved(false));
        }
        return multimap;
    }

    @Override
    public float getAttackDamage() {
        return super.getAttackDamage() * 1.5f;
    }

    @Override
    protected double getAttackSpeed() {
        return -2.1D;
    }

    @Override
    public float getReach() {
        return 2.0f;
    }

    // Remove the sweep attack for the dagger
    @Override
    public void doSweepAttack(EntityPlayer player, EntityLivingBase target) {
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(TextFormatting.GRAY + ModUtils.translateDesc("dagger"));
    }
}
