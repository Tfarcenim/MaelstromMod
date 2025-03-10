package com.barribob.intothemaelstrom.entity.render;

import com.barribob.intothemaelstrom.entity.entities.EntityLeveledMob;
import com.barribob.intothemaelstrom.entity.model.ModelAzureGolem;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderAzureGolem extends RenderScaledMob<EntityLeveledMob> {
    public static final float AZURE_GOLEM_SIZE = 1.4f;

    public RenderAzureGolem(RenderManager rendermanagerIn, String textures) {
        super(rendermanagerIn, textures, new ModelAzureGolem(), AZURE_GOLEM_SIZE);
    }

    @Override
    protected void applyRotations(EntityLeveledMob entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);

        if (entityLiving.limbSwingAmount >= 0.01D) {
            float f = 13.0F;
            float f1 = entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            GlStateManager.rotate(6.5F * f2, 0.0F, 0.0F, 1.0F);
        }
    }
}
