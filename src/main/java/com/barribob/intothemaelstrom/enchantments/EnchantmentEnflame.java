package com.barribob.intothemaelstrom.enchantments;

import com.barribob.intothemaelstrom.items.gun.ItemGun;
import com.barribob.intothemaelstrom.items.staff.ItemStaff;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

/**
 * Added the flame enchantment to guns
 */
public class EnchantmentEnflame extends Enchantment {
    public EnchantmentEnflame(String registryName, Rarity rarityIn, EntityEquipmentSlot[] slots) {
        // The enum enchantment type doesn't matter here
        super(rarityIn, EnumEnchantmentType.ALL, slots);
        this.setRegistryName(registryName);
        this.setName(registryName);
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 20;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return 50;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return stack.getItem() instanceof ItemGun || (stack.getItem() instanceof ItemStaff && ((ItemStaff) stack.getItem()).doesDamage());
    }
}
