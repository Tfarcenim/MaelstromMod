package com.barribob.intothemaelstrom.enchantments;

import com.barribob.intothemaelstrom.items.gun.ItemGun;
import com.barribob.intothemaelstrom.items.staff.ItemStaff;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EnchantmentCriticalHit extends Enchantment {
    public EnchantmentCriticalHit(String registryName, Rarity rarityIn, EntityEquipmentSlot[] slots) {
        // The enum enchantment type doesn't matter here
        super(rarityIn, EnumEnchantmentType.ALL, slots);
        this.setRegistryName(registryName);
        this.setName(registryName);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 12 + (enchantmentLevel - 1) * 20;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 25;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return stack.getItem() instanceof ItemGun || (stack.getItem() instanceof ItemStaff && ((ItemStaff) stack.getItem()).doesDamage());
    }
}
