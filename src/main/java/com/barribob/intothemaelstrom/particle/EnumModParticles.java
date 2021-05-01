package com.barribob.intothemaelstrom.particle;

import com.google.common.collect.Maps;

import javax.annotation.Nullable;
import java.util.Map;

public enum EnumModParticles {
    SWEEP_ATTACK(45, 3),
    EFFECT(46, 3);

    private final int particleID;
    private final int argumentCount;
    private static final Map<Integer, EnumModParticles> PARTICLES = Maps.newHashMap();

    EnumModParticles(int particleIDIn, int argumentCountIn) {
        this.particleID = particleIDIn;
        this.argumentCount = argumentCountIn;
    }

    EnumModParticles(int particleIDIn) {
        this(particleIDIn, 0);
    }

    public int getParticleID() {
        return this.particleID;
    }

    public int getArgumentCount() {
        return this.argumentCount;
    }

    /**
     * Gets the relative EnumParticleTypes by id.
     */
    @Nullable
    public static EnumModParticles getParticleFromId(int particleId) {
        return PARTICLES.get(particleId);
    }

    static {
        for (EnumModParticles enumparticletypes : values()) {
            PARTICLES.put(enumparticletypes.getParticleID(), enumparticletypes);
        }
    }
}