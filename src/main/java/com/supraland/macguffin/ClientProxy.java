package com.supraland.macguffin;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends ServerProxy {
    @Override
    public void preInit() {
        // OBJ Model Yükleyicisini (OBJLoader) modumuz için aktif ediyoruz!
        OBJLoader.INSTANCE.addDomain("supraland");
        
        // Merminin render ayarı (Burası aynı kalıyor)
        RenderingRegistry.registerEntityRenderingHandler(EntityRedProjectile.class, renderManager -> new RenderRedProjectile(renderManager));
    }
}
