package com.barribob.MaelstromMod.init;

import com.barribob.MaelstromMod.IntoTheMaelstrom;
import com.barribob.MaelstromMod.potions.ModPotion;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@Mod.EventBusSubscriber()
@ObjectHolder(IntoTheMaelstrom.MOD_ID)
public class ModPotions {
    public static final Potion water_strider = null;

    @SubscribeEvent
    public static void onPotionRegistry(final RegistryEvent.Register<Potion> event) {
        event.getRegistry().registerAll(new ModPotion("water_strider", false, 0x00ccff, 0, 0));
    }
}
