package com.supraland.macguffin;

import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends ServerProxy {
    @Override
    public void preInit() {
        RenderingRegistry.registerEntityRenderingHandler(EntityRedProjectile.class, renderManager -> new RenderRedProjectile(renderManager));
    }
}
