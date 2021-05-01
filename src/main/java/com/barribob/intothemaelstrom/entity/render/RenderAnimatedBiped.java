package com.barribob.intothemaelstrom.entity.render;

import com.barribob.intothemaelstrom.entity.model.ModelAnimatedBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderAnimatedBiped<T extends EntityLiving> extends RenderBiped<T> {
    private ResourceLocation textures;

    public RenderAnimatedBiped(RenderManager renderManagerIn, ModelAnimatedBiped modelBipedIn, float shadowSize, ResourceLocation textures) {
        super(renderManagerIn, modelBipedIn, shadowSize);
        this.textures = textures;
    }

    @Override
    protected ResourceLocation getEntityTexture(T entity) {
        return this.textures;
    }
}
