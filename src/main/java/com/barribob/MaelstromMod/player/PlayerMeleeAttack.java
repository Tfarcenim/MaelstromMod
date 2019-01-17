package com.barribob.MaelstromMod.player;

import com.barribob.MaelstromMod.items.tools.ISweepAttackOverride;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

/**
 * 
 * Methods taken from the player class to implement a custom sweep event
 *
 */
public class PlayerMeleeAttack
{
    private static boolean onPlayerAttackTarget(EntityPlayer player, Entity target)
    {
	ItemStack stack = player.getHeldItemMainhand();
	return stack.isEmpty() || !stack.getItem().onLeftClickEntity(stack, player, target);
    }

    /**
     * Attacks for the player the targeted entity with the currently equipped item.
     * The equipped item has hitEntity called on it. Args: targetEntity
     */
    public static void attackTargetEntityWithCurrentItem(EntityPlayer player, Entity targetEntity, ISweepAttackOverride item)
    {
	if (!onPlayerAttackTarget(player, targetEntity))
	    return;

	if (targetEntity.canBeAttackedWithItem())
	{
	    if (!targetEntity.hitByEntity(player))
	    {
		float damage = (float) player.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
		float mainhandDamage;

		if (targetEntity instanceof EntityLivingBase)
		{
		    mainhandDamage = EnchantmentHelper.getModifierForCreature(player.getHeldItemMainhand(), ((EntityLivingBase) targetEntity).getCreatureAttribute());
		}
		else
		{
		    mainhandDamage = EnchantmentHelper.getModifierForCreature(player.getHeldItemMainhand(), EnumCreatureAttribute.UNDEFINED);
		}

		float atkCooldown = player.getCooledAttackStrength(0.5F);
		damage = damage * (0.2F + atkCooldown * atkCooldown * 0.8F);
		mainhandDamage = mainhandDamage * atkCooldown;
		player.resetCooldown();

		if (damage > 0.0F || mainhandDamage > 0.0F)
		{
		    boolean cooldownCharged = atkCooldown > 0.9F;
		    boolean sprintAtk = false;
		    int knockback = 0;
		    knockback = knockback + EnchantmentHelper.getKnockbackModifier(player);

		    if (player.isSprinting() && cooldownCharged)
		    {
			player.world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK,
				player.getSoundCategory(), 1.0F, 1.0F);
			++knockback;
			sprintAtk = true;
		    }

		    boolean critical = cooldownCharged && player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater()
			    && !player.isPotionActive(MobEffects.BLINDNESS) && !player.isRiding() && targetEntity instanceof EntityLivingBase;
		    critical = critical && !player.isSprinting();

		    net.minecraftforge.event.entity.player.CriticalHitEvent hitResult = net.minecraftforge.common.ForgeHooks.getCriticalHit(player, targetEntity, critical,
			    critical ? 1.5F : 1.0F);
		    critical = hitResult != null;
		    if (critical)
		    {
			damage *= hitResult.getDamageModifier();
		    }

		    damage = damage + mainhandDamage;
		    boolean sweepAttack = false;
		    double d0 = (double) (player.distanceWalkedModified - player.prevDistanceWalkedModified);

		    if (cooldownCharged && !critical && !sprintAtk && player.onGround && d0 < (double) player.getAIMoveSpeed())
		    {
			sweepAttack = true;
		    }

		    float health = 0.0F;
		    boolean hasFireAspect = false;
		    int fireAspect = EnchantmentHelper.getFireAspectModifier(player);

		    if (targetEntity instanceof EntityLivingBase)
		    {
			health = ((EntityLivingBase) targetEntity).getHealth();

			if (fireAspect > 0 && !targetEntity.isBurning())
			{
			    hasFireAspect = true;
			    targetEntity.setFire(1);
			}
		    }

		    double mx = targetEntity.motionX;
		    double my = targetEntity.motionY;
		    double mz = targetEntity.motionZ;
		    boolean attackSuccessful = targetEntity.attackEntityFrom(DamageSource.causePlayerDamage(player), damage); // Actually attacks the entity

		    if (attackSuccessful)
		    {
			// Apply knockback
			if (knockback > 0)
			{
			    if (targetEntity instanceof EntityLivingBase)
			    {
				((EntityLivingBase) targetEntity).knockBack(player, (float) knockback * 0.5F, (double) MathHelper.sin(player.rotationYaw * 0.017453292F),
					(double) (-MathHelper.cos(player.rotationYaw * 0.017453292F)));
			    }
			    else
			    {
				targetEntity.addVelocity((double) (-MathHelper.sin(player.rotationYaw * 0.017453292F) * (float) knockback * 0.5F), 0.1D,
					(double) (MathHelper.cos(player.rotationYaw * 0.017453292F) * (float) knockback * 0.5F));
			    }

			    player.motionX *= 0.6D;
			    player.motionZ *= 0.6D;
			    player.setSprinting(false);
			}

			// Do the overridden sweep attack
			if (sweepAttack && targetEntity instanceof EntityLivingBase)
			{
			    item.doSweepAttack(player, (EntityLivingBase) targetEntity);
			}

			if (targetEntity instanceof EntityPlayerMP && targetEntity.velocityChanged)
			{
			    ((EntityPlayerMP) targetEntity).connection.sendPacket(new SPacketEntityVelocity(targetEntity));
			    targetEntity.velocityChanged = false;
			    targetEntity.motionX = mx;
			    targetEntity.motionY = my;
			    targetEntity.motionZ = mz;
			}

			if (critical)
			{
			    player.world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_CRIT,
				    player.getSoundCategory(), 1.0F, 1.0F);
			    player.onCriticalHit(targetEntity);
			}

			if (!critical && !sweepAttack)
			{
			    if (cooldownCharged)
			    {
				player.world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_STRONG,
					player.getSoundCategory(), 1.0F, 1.0F);
			    }
			    else
			    {
				player.world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_WEAK,
					player.getSoundCategory(), 1.0F, 1.0F);
			    }
			}

			if (mainhandDamage > 0.0F)
			{
			    player.onEnchantmentCritical(targetEntity);
			}

			player.setLastAttackedEntity(targetEntity);

			if (targetEntity instanceof EntityLivingBase)
			{
			    EnchantmentHelper.applyThornEnchantments((EntityLivingBase) targetEntity, player);
			}

			EnchantmentHelper.applyArthropodEnchantments(player, targetEntity);
			ItemStack itemstack1 = player.getHeldItemMainhand();
			Entity entity = targetEntity;

			if (targetEntity instanceof MultiPartEntityPart)
			{
			    IEntityMultiPart ientitymultipart = ((MultiPartEntityPart) targetEntity).parent;

			    if (ientitymultipart instanceof EntityLivingBase)
			    {
				entity = (EntityLivingBase) ientitymultipart;
			    }
			}

			// Decrements item damage I think
			if (!itemstack1.isEmpty() && entity instanceof EntityLivingBase)
			{
			    ItemStack beforeHitCopy = itemstack1.copy();
			    itemstack1.hitEntity((EntityLivingBase) entity, player);

			    if (itemstack1.isEmpty())
			    {
				net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, beforeHitCopy, EnumHand.MAIN_HAND);
				player.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
			    }
			}

			if (targetEntity instanceof EntityLivingBase)
			{
			    float f5 = health - ((EntityLivingBase) targetEntity).getHealth();
			    player.addStat(StatList.DAMAGE_DEALT, Math.round(f5 * 10.0F));

			    if (fireAspect > 0)
			    {
				targetEntity.setFire(fireAspect * 4);
			    }

			    if (player.world instanceof WorldServer && f5 > 2.0F)
			    {
				int k = (int) ((double) f5 * 0.5D);
				((WorldServer) player.world).spawnParticle(EnumParticleTypes.DAMAGE_INDICATOR, targetEntity.posX,
					targetEntity.posY + (double) (targetEntity.height * 0.5F), targetEntity.posZ, k, 0.1D, 0.0D, 0.1D, 0.2D);
			    }
			}

			player.addExhaustion(0.1F);
		    }
		    else
		    {
			player.world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE,
				player.getSoundCategory(), 1.0F, 1.0F);

			if (hasFireAspect)
			{
			    targetEntity.extinguish();
			}
		    }
		}
	    }
	}
    }
}
