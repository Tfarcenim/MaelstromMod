package com.barribob.intothemaelstrom.entity.projectile;

import com.barribob.intothemaelstrom.init.ModItems;
import com.barribob.intothemaelstrom.util.Element;
import com.barribob.intothemaelstrom.util.ModDamageSource;
import com.barribob.intothemaelstrom.util.ModUtils;
import com.barribob.intothemaelstrom.util.handlers.ParticleManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ProjectileEntityBullet extends ProjectileEntityGun {
    public ProjectileEntityBullet(World worldIn, EntityLivingBase throwerIn, float damage, ItemStack stack, Element element) {
        super(worldIn, throwerIn, damage, stack, element);
    }

    public ProjectileEntityBullet(World worldIn, EntityLivingBase throwerIn, float damage, ItemStack stack) {
        super(worldIn, throwerIn, damage, stack);
    }

    public ProjectileEntityBullet(World worldIn) {
        super(worldIn);
    }

    public ProjectileEntityBullet(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    public Item getItemToRender() {
        if (getElement() == Element.GOLDEN) {
            return ModItems.GOLD_PELLET;
        } else if (getElement() == Element.CRIMSON) {
            return ModItems.CRIMSON_PELLET;
        }
        return ModItems.IRON_PELLET;
    }

    @Override
    protected void spawnParticles() {
        if (getElement() != Element.NONE) {
            ParticleManager.spawnEffect(world, ModUtils.entityPos(this), getElement().particleColor);
        }
    }

    @Override
    protected void onHit(RayTraceResult result) {
        ModUtils.handleBulletImpact(result.entityHit, this, this.getGunDamage(result.entityHit), ModDamageSource.causeElementalThrownDamage(this, this.shootingEntity, this.getElement()),
                this.getKnockback());
        super.onHit(result);
    }
}
