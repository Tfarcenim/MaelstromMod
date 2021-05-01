package com.barribob.intothemaelstrom.entity.projectile;

import com.barribob.intothemaelstrom.config.ModConfig;
import com.barribob.intothemaelstrom.entity.entities.EntityMaelstromMob;
import com.barribob.intothemaelstrom.init.ModEnchantments;
import com.barribob.intothemaelstrom.util.Element;
import com.barribob.intothemaelstrom.util.IElement;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * A helper class to record enchantments from the gun stack
 */
public class ProjectileEntityGun extends ProjectileEntity {
    private int knockbackStrength;
    private int maelstromDestroyer;
    private int criticalHit;
    private boolean isCritical;
    private static final byte CRITICAL_BYTE = 5;

    public ProjectileEntityGun(World worldIn, EntityLivingBase throwerIn, double baseDamage, ItemStack stack, Element element) {
        this(worldIn, throwerIn, baseDamage, stack);
        this.setElement(element);
    }

    public ProjectileEntityGun(World worldIn, EntityLivingBase throwerIn, double baseDamage, ItemStack stack) {
        super(worldIn, throwerIn, baseDamage);

        if (stack != null) {
            this.knockbackStrength = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.impact, stack);
            this.maelstromDestroyer = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.maelstrom_destroyer, stack);
            this.criticalHit = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.critical_hit, stack);
            if (rand.nextInt(8) == 0 && this.criticalHit > 0 && !world.isRemote) {
                this.isCritical = true;
                this.setDamage(this.getDamage() * this.criticalHit * 2.5f);
            }
            if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.gun_flame, stack) > 0) {
                this.setFire(100);
            }
            if (stack.getItem() instanceof IElement) {
                this.setElement(((IElement) stack.getItem()).getElement());
            }
        }
    }

    protected int getKnockback() {
        return this.knockbackStrength;
    }

    protected double getGunDamage(Entity entity) {
        if (!EntityMaelstromMob.CAN_TARGET.apply(entity)) {
            float maxDamageBonus = (float) (Math.pow(ModConfig.balance.progression_scale, 2.5) - 1); // Max damage is slightly more than the damage enchantment
            double damageBonus = super.getDamage() * maxDamageBonus * (this.maelstromDestroyer / (float) ModEnchantments.maelstrom_destroyer.getMaxLevel());
            return super.getDamage() + damageBonus;
        }

        return super.getDamage();
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.isCritical) {
            world.setEntityState(this, CRITICAL_BYTE);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == CRITICAL_BYTE) {
            world.spawnParticle(EnumParticleTypes.REDSTONE, this.posX, this.posY, this.posZ, 0, 0, 0);
        } else {
            super.handleStatusUpdate(id);
        }
    }

    public ProjectileEntityGun(World worldIn) {
        super(worldIn);
    }

    public ProjectileEntityGun(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }
}
