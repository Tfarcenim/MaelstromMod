package com.barribob.intothemaelstrom.entity.render;

import com.barribob.intothemaelstrom.IntoTheMaelstrom;
import com.barribob.intothemaelstrom.entity.entities.EntityMaelstromBeast;
import com.barribob.intothemaelstrom.entity.model.ModelMaelstromBeast;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderMaelstromBeast extends RenderModEntity<EntityMaelstromBeast> {
    public ResourceLocation TEXTURES_1 = new ResourceLocation(IntoTheMaelstrom.MOD_ID + ":textures/entity/maelstrom_beast.png");
    public ResourceLocation TEXTURES_2 = new ResourceLocation(IntoTheMaelstrom.MOD_ID + ":textures/entity/skeleton_minotaur.png");

    public RenderMaelstromBeast(RenderManager rendermanagerIn) {
        super(rendermanagerIn, "", new ModelMaelstromBeast());
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityMaelstromBeast entity) {
        if (entity.isRaged()) {
            return TEXTURES_2;
        }
        return TEXTURES_1;
    }
}
