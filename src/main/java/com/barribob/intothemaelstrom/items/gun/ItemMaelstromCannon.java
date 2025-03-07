package com.barribob.intothemaelstrom.items.gun;

import com.barribob.intothemaelstrom.config.ModConfig;
import com.barribob.intothemaelstrom.entity.projectile.ProjectileEntity;
import com.barribob.intothemaelstrom.items.gun.bullet.BulletFactory;
import com.barribob.intothemaelstrom.items.gun.bullet.MaelstromCannon;
import com.barribob.intothemaelstrom.items.staff.ItemStaff;
import com.barribob.intothemaelstrom.util.ModUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

/**
 * A simple medium range weapon
 */
public class ItemMaelstromCannon extends ItemStaff {
    private BulletFactory factory = new MaelstromCannon();

    public ItemMaelstromCannon(int maxDamage, float level) {
        super(maxDamage, level);
    }

    public float getBaseDamage() {
        return 5 * ModConfig.balance.weapon_damage;
    }

    /**
     * Shoot a single projectile
     */
    @Override
    protected void shoot(World world, EntityPlayer player, EnumHand handIn, ItemStack stack) {
        world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 0.5F,
                0.6F / (itemRand.nextFloat() * 0.4F + 0.8F));

        int power = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
        int knockback = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
        float velocity = 1.0F;
        float inaccuracy = 3.0f;
        float degreesUp = 20;

        ProjectileEntity projectile = factory.get(world, player, stack, ModUtils.getEnchantedDamage(stack, this.getLevel(), getBaseDamage()));
        projectile.shoot(player, player.rotationPitch - degreesUp, player.rotationYaw, 0.0F, velocity, inaccuracy);
        projectile.setTravelRange(25f);
        world.spawnEntity(projectile);
    }

    public Item setFactory(BulletFactory factory) {
        this.factory = factory;
        return this;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(ModUtils.getDamageTooltip(ModUtils.getEnchantedDamage(stack, this.getLevel(), getBaseDamage())));
        tooltip.add(TextFormatting.GRAY + ModUtils.translateDesc("maelstrom_cannon"));
    }

    @Override
    public boolean doesDamage() {
        return true;
    }
}
