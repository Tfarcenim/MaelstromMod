package com.barribob.intothemaelstrom.items;

import net.minecraft.util.math.Vec3d;

/**
 * Adds additional sweep attack particle to items with sweep attacks Works in
 * conjunction with ParticleSpawnerSwordSwing
 *
 * @see com.barribob.intothemaelstrom.entity.particleSpawners.ParticleSpawnerSwordSwing
 */
public interface ISweepAttackParticles {
    Vec3d getColor();

    float getSize();
}
