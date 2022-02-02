package com.barribob.intothemaelstrom.entity.action;

import com.barribob.intothemaelstrom.entity.entities.EntityHerobrineOne;
import com.barribob.intothemaelstrom.entity.entities.EntityLeveledMob;
import com.barribob.intothemaelstrom.init.ModSoundEvents;
import com.barribob.intothemaelstrom.util.Element;
import com.barribob.intothemaelstrom.util.ModDamageSource;
import com.barribob.intothemaelstrom.util.ModUtils;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;

/*
 * Deals damage in an area around the entity
 */
public class ActionSpinSlash<T extends ModelBase> implements IAction<T> {
    private float size = 2.2f;

    public ActionSpinSlash() {
    }

    public ActionSpinSlash(float size) {
        this.size = size;
    }

    @Override
    public void performAction(EntityLeveledMob<T> actor, EntityLivingBase target) {
        DamageSource source = ModDamageSource.builder()
                .type(ModDamageSource.MOB)
                .directEntity(actor)
                .element(actor.getElement())
                .disablesShields()
                .stoppedByArmorNotShields().build();
        
        ModUtils.handleAreaImpact(size, (e) -> actor.getAttack() * actor.getConfigDouble("spin_damage"), actor, actor.getPositionVector(), source, 0.3f, actor.getElement().matchesElement(Element.CRIMSON) ? 3 : 0, false);

        actor.playSound(ModSoundEvents.ENTITY_IRON_SHADE_SPIN_SLASH, 1.0F, 1.0F / (actor.getRNG().nextFloat() * 0.4F + 0.8F));

        actor.world.setEntityState(actor, EntityHerobrineOne.slashParticleByte);
    }
}
