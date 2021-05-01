package com.barribob.intothemaelstrom.items.staff;

import com.barribob.intothemaelstrom.config.ModConfig;
import com.barribob.intothemaelstrom.entity.projectile.ProjectileEntity;
import com.barribob.intothemaelstrom.items.gun.bullet.BulletFactory;
import com.barribob.intothemaelstrom.items.gun.bullet.Fireball;
import com.barribob.intothemaelstrom.util.ModUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemFireballStaff extends ItemStaff {
    private BulletFactory factory = new Fireball();

    public ItemFireballStaff(int useTime, float level) {
        super(useTime, level);
    }

    private float getBaseDamage() {
        return 10 * ModConfig.balance.weapon_damage;
    }

    @Override
    protected void shoot(World world, EntityPlayer player, EnumHand handIn, ItemStack stack) {
        world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.NEUTRAL, 1.0F,
                0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        float inaccuracy = 2.0f;
        float velocity = 1.3f;

        ProjectileEntity projectile = factory.get(world, player, stack, ModUtils.getEnchantedDamage(stack, this.getLevel(), getBaseDamage()));
        projectile.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, velocity, inaccuracy);
        projectile.setTravelRange(25);

        world.spawnEntity(projectile);
    }

    public ItemFireballStaff setFactory(BulletFactory factory) {
        this.factory = factory;
        return this;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(ModUtils.getDamageTooltip(ModUtils.getEnchantedDamage(stack, this.getLevel(), getBaseDamage())));
        tooltip.add(TextFormatting.GRAY + ModUtils.translateDesc("fireball_staff"));
    }

    @Override
    public boolean doesDamage() {
        return true;
    }
}
