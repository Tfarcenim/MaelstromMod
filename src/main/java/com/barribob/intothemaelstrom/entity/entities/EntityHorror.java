package com.barribob.intothemaelstrom.entity.entities;

import com.barribob.intothemaelstrom.entity.ai.EntityAIRangedAttack;
import com.barribob.intothemaelstrom.entity.projectile.ProjectileEntityHorrorAttack;
import com.barribob.intothemaelstrom.util.ModRandom;
import com.barribob.intothemaelstrom.util.handlers.ParticleManager;
import com.barribob.intothemaelstrom.init.ModSoundEvents;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

//aka cauldron
public class EntityHorror extends EntityMaelstromMob {
    public static final float PROJECTILE_INACCURACY = 0;
    public static final float PROJECTILE_VELOCITY = 0.5f;
    public static final float PROJECTILE_VARIATION_FACTOR = 0.5f;

    public EntityHorror(World worldIn) {
        super(worldIn);
        this.setSize(1.3F, 1.3F);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(4, new EntityAIRangedAttack<EntityMaelstromMob>(this, 1.0f, 20, 5.0f, 0.5f));
    }

    /**
     * Spawns smoke out of the middle of the entity
     */
    @Override
    public void onUpdate() {
        super.onUpdate();
        if (world.isRemote) {
            for (int i = 0; i < 5; i++) {
                ParticleManager.spawnMaelstromSmoke(world, rand, new Vec3d(this.posX + ModRandom.getFloat(0.4f), this.posY + 1, this.posZ + ModRandom.getFloat(0.4f)), true);
            }
        }
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        if (!world.isRemote) {
            ProjectileEntityHorrorAttack projectile = new ProjectileEntityHorrorAttack(this.world, this, this.getAttack());
            double xDir = (rand.nextFloat() - rand.nextFloat()) * PROJECTILE_VARIATION_FACTOR;
            double yDir = 1;
            double zDir = (rand.nextFloat() - rand.nextFloat()) * PROJECTILE_VARIATION_FACTOR;
            projectile.shoot(xDir, yDir, zDir, PROJECTILE_VELOCITY, PROJECTILE_INACCURACY);
            this.playSound(SoundEvents.BLOCK_ANVIL_BREAK, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
            this.world.spawnEntity(projectile);
        }
    }

    private static final SoundEvent[] ambient = new SoundEvent[]{ModSoundEvents.ENTITY_HORROR_AMBIENT1,ModSoundEvents.ENTITY_HORROR_AMBIENT2,ModSoundEvents.ENTITY_HORROR_AMBIENT3};
    private static final SoundEvent[] hurt = new SoundEvent[]{ModSoundEvents.ENTITY_HORROR_HURT1,ModSoundEvents.ENTITY_HORROR_HURT2,ModSoundEvents.ENTITY_HORROR_HURT3};
    private static final SoundEvent[] death = new SoundEvent[]{ModSoundEvents.ENTITY_HORROR_DEATH1,ModSoundEvents.ENTITY_HORROR_DEATH2,ModSoundEvents.ENTITY_HORROR_DEATH3};


    @Override
    protected SoundEvent getAmbientSound() {
        return ambient[rand.nextInt(3)];
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return hurt[rand.nextInt(3)];
    }

    @Override
    protected SoundEvent getDeathSound() {
        return death[rand.nextInt(3)];
    }

    @Override
    protected float getSoundVolume() {
        return 0.25f;
    }
}
