package com.barribob.intothemaelstrom.event_handlers;

import com.barribob.intothemaelstrom.IntoTheMaelstrom;
import com.barribob.intothemaelstrom.entity.model.LayerModElytra;
import com.barribob.intothemaelstrom.items.ItemModElytra;
import com.barribob.intothemaelstrom.packets.MessageStartElytraFlying;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

@Mod.EventBusSubscriber(modid = IntoTheMaelstrom.MOD_ID, value = Side.CLIENT)
public class ClientElytraEventHandler {
    static boolean prevJumpTick;
    private static Set<EntityPlayer> layeredPlayers = Collections.newSetFromMap(new WeakHashMap<>());

    @SubscribeEvent
    public static void onPressKey(InputUpdateEvent event) {
        if (event.getEntityPlayer() instanceof EntityPlayerSP) {
            EntityPlayerSP player = (EntityPlayerSP) event.getEntityPlayer();
            if (!prevJumpTick && player.movementInput.jump && !player.onGround && player.motionY < 0.0D && !player.isElytraFlying() && !player.capabilities.isFlying) {
                ItemStack itemstack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);

                if (itemstack.getItem() instanceof ItemModElytra) {
                    IntoTheMaelstrom.network.sendToServer(new MessageStartElytraFlying());
                }
            }
            prevJumpTick = player.movementInput.jump;
        }
    }

    // Jenky way of registering layers for player
    @SubscribeEvent
    public static void onRenderLiving(RenderLivingEvent.Pre<AbstractClientPlayer> event) {
        if (event.getEntity() instanceof EntityPlayer && !layeredPlayers.contains(event.getEntity())) {
            event.getRenderer().addLayer(new LayerModElytra(event.getRenderer()));
            layeredPlayers.add((EntityPlayer) event.getEntity());
        }
    }
}
