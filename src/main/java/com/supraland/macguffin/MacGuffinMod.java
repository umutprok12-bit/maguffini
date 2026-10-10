package com.supraland.macguffin;

import net.minecraft.item.Item;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

@Mod(modid = "supraland", name = "Supraland MacGuffin", version = "1.0")
@Mod.EventBusSubscriber
public class MacGuffinMod {
    @SidedProxy(clientSide = "com.supraland.macguffin.ClientProxy", serverSide = "com.supraland.macguffin.ServerProxy")
    public static ServerProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    public static final Item MACGUFFIN = new ItemMacGuffin();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(MACGUFFIN);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(MACGUFFIN, 0, new ModelResourceLocation(MACGUFFIN.getRegistryName(), "inventory"));
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        event.getRegistry().register(EntityEntryBuilder.create()
            .entity(EntityRedProjectile.class)
            .id(new ResourceLocation("supraland", "red_projectile"), 1)
            .name("red_projectile")
            .tracker(64, 3, true)
            .build());
    }
}
