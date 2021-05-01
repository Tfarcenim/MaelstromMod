package com.barribob.intothemaelstrom.entity.projectile;

import com.barribob.intothemaelstrom.entity.entities.EntityLeveledMob;
import com.barribob.intothemaelstrom.entity.entities.EntityMaelstromBeast;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class ProjectileEntityBoneQuake extends ProjectileEntityBeastQuake {

    public ProjectileEntityBoneQuake(World worldIn, EntityLivingBase throwerIn, double baseDamage) {
        super(worldIn, throwerIn, baseDamage);
    }

    public ProjectileEntityBoneQuake(World worldIn) {
        super(worldIn);
    }

    public ProjectileEntityBoneQuake(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    public void onUpdate() {
        if (this.shootingEntity instanceof EntityLeveledMob) {
            EntityMaelstromBeast.spawnBone(world, this.getPositionVector(), (EntityLeveledMob) this.shootingEntity);
        }
        super.onUpdate();
    }
}
