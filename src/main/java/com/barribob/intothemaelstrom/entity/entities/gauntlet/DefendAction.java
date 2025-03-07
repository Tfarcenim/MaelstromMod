package com.barribob.intothemaelstrom.entity.entities.gauntlet;

import com.barribob.intothemaelstrom.entity.entities.EntityLeveledMob;
import com.barribob.intothemaelstrom.init.ModBBAnimations;
import com.barribob.intothemaelstrom.util.ModDamageSource;
import com.barribob.intothemaelstrom.util.ModUtils;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;

import java.util.function.Function;

public class DefendAction implements IGauntletAction {
    private final EntityLeveledMob entity;
    private boolean isDefending;

    public DefendAction(EntityLeveledMob entity) {
        this.entity = entity;
    }

    @Override
    public void doAction() {
        ModBBAnimations.animation(entity, "gauntlet.defend", false);
        entity.addEvent(() -> {
            isDefending = true;
            entity.height = 2;
        }, 5);
        entity.addEvent(() -> {
            isDefending = false;
            entity.height = 4;
        }, 22);
    }

    @Override
    public void update() {
        if(isDefending) {
            DamageSource source = ModDamageSource.builder()
                    .type(ModDamageSource.MOB)
                    .directEntity(entity)
                    .stoppedByArmorNotShields()
                    .element(entity.getElement()).build();

            Function<Entity, Double> kineticDamage = (e) -> entity.getAttack() * ModUtils.getEntityVelocity(e).lengthVector();
            ModUtils.handleAreaImpact(1.0f, kineticDamage, entity,
                    entity.getPositionEyes(1), source, 1, 0, false);
        }
    }

    @Override
    public boolean isImmuneToDamage() {
        return isDefending;
    }

    @Override
    public int attackLength() {
        return 23;
    }

    @Override
    public int attackCooldown() {
        return 20;
    }
}
