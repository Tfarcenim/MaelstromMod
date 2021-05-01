package com.barribob.intothemaelstrom.renderer;

import net.minecraft.util.math.Vec3d;

import java.util.Optional;

public interface ITarget {
    Optional<Vec3d> getTarget();
}
