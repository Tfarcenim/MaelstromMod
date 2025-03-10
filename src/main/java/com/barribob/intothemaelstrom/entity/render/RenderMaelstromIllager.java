package com.barribob.intothemaelstrom.entity.render;

import com.barribob.intothemaelstrom.entity.entities.EntityMaelstromIllager;
import com.barribob.intothemaelstrom.entity.model.ModelMaelstromIllager;
import net.minecraft.client.renderer.entity.RenderManager;

/**
 * Renders illager armor when arms are raised, otherwise it renders the illager
 * model normally
 */
public class RenderMaelstromIllager extends RenderModEntity<EntityMaelstromIllager> {
    public RenderMaelstromIllager(RenderManager rendermanagerIn) {
        super(rendermanagerIn, "maelstrom_illager.png", new ModelMaelstromIllager());
        this.addLayer(new LayerMaelstromIllagerArmor(this));
    }
}
