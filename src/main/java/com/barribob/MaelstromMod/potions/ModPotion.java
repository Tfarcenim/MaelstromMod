package com.barribob.MaelstromMod.potions;

import com.barribob.MaelstromMod.IntoTheMaelstrom;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class ModPotion extends Potion {
    public ModPotion(String name, boolean isBadEffectIn, int liquidColorIn, int iconX, int iconY) {
        super(isBadEffectIn, liquidColorIn);
        this.setIconIndex(iconX, iconY);
        this.setPotionName(IntoTheMaelstrom.MOD_ID + ".effect." + name);
        this.setRegistryName(new ResourceLocation(IntoTheMaelstrom.MOD_ID + ":" + name));
    }

    private static final ResourceLocation ICONS = new ResourceLocation(IntoTheMaelstrom.MOD_ID, "textures/gui/potion_effects.png");

    @Override
    public boolean hasStatusIcon() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(ICONS);
        return true;
    }
}
