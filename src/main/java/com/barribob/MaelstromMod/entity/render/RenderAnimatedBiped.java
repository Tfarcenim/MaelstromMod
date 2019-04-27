package com.barribob.MaelstromMod.entity.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderAnimatedBiped extends RenderBiped
{
    private ResourceLocation textures;

    public RenderAnimatedBiped(RenderManager renderManagerIn, ModelBiped modelBipedIn, float shadowSize, ResourceLocation textures)
    {
	super(renderManagerIn, modelBipedIn, shadowSize);
	this.textures = textures;
	this.addLayer(new LayerBipedArmor(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLiving entity)
    {
	return this.textures;
    }
}
