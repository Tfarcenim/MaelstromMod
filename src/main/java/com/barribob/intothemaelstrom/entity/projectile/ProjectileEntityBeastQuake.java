package com.barribob.intothemaelstrom.entity.projectile;

import com.barribob.intothemaelstrom.util.ModColors;
import com.barribob.intothemaelstrom.util.ModDamageSource;
import com.barribob.intothemaelstrom.util.ModRandom;
import com.barribob.intothemaelstrom.util.ModUtils;
import com.barribob.intothemaelstrom.util.handlers.ParticleManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ProjectileEntityBeastQuake extends ProjectileEntityQuake {
    public ProjectileEntityBeastQuake(World worldIn, EntityLivingBase throwerIn, double baseDamage) {
        super(worldIn, throwerIn, baseDamage, null);
        this.updates = 10;
    }

    public ProjectileEntityBeastQuake(World worldIn) {
        super(worldIn);
    }

    public ProjectileEntityBeastQuake(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    protected void spawnParticles() {
        for(int j = 0; j < 10; j++) {
            int randHeight = ModRandom.range(4, 6);
            for (int i = 0; i < randHeight; i++) {
                ParticleManager.spawnDust(world,
                        getPositionVector()
                                .add(ModUtils.Y_AXIS.scale(i * 0.5))
                                .add(ModRandom.randVec().scale(0.5))
                                .add(ModUtils.planeProject(ModUtils.getEntityVelocity(this), ModUtils.Y_AXIS).scale(2)),
                        ModColors.MAELSTROM,
                        Vec3d.ZERO,
                        ModRandom.range(10, 15));
            }
        }
    }

    @Override
    protected void onQuakeUpdate() {
        if(this.shootingEntity != null && !world.isRemote) {
            DamageSource source = ModDamageSource.builder()
                    .directEntity(this)
                    .indirectEntity(shootingEntity)
                    .type(ModDamageSource.MOB)
                    .stoppedByArmorNotShields()
                    .element(getElement())
                    .build();

            for(int i = 0; i < 4; i++) {
                ModUtils.handleAreaImpact(0.5f,
                        e -> this.getDamage(),
                        this.shootingEntity,
                        getPositionVector().add(ModUtils.Y_AXIS.scale(i * 0.5f)),
                        source,
                        0.25f, 0, false);
            }
        }
    }
}
