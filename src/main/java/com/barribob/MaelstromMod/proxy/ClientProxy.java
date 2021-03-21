package com.barribob.MaelstromMod.proxy;

import com.barribob.MaelstromMod.init.ModBlocks;
import com.barribob.MaelstromMod.init.ModItems;
import com.barribob.MaelstromMod.util.handlers.RenderHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy {

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {

        if (!Minecraft.getMinecraft().getFramebuffer().isStencilEnabled()) {
            Minecraft.getMinecraft().getFramebuffer().enableStencil();
        }

        RenderHandler.registerEntityRenderers();

        for (Item item : ModItems.ITEMS) {
            registerModel(item);
        }

        for (Block block : ModBlocks.BLOCKS) {
                registerModel(block);
        }
    }

    private static void registerModel(Block block) {
        registerModel(Item.getItemFromBlock(block));
    }

    private static void registerModel(Item item) {
        registerModel(item,0);
    }

    private static void registerModel(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

}
