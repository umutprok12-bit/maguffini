package com.supraland.macguffin;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityRedProjectile extends EntityThrowable {
    private static final DataParameter<Float> SCALE = EntityDataManager.createKey(EntityRedProjectile.class, DataSerializers.FLOAT);

    public EntityRedProjectile(World worldIn) {
        super(worldIn);
    }

    public EntityRedProjectile(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
        this.setNoGravity(true); // Ensures it shoots straight
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(SCALE, 1.0f);
    }

    public float getProjectileScale() {
        return this.dataManager.get(SCALE);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.world.isRemote) {
            float currentScale = getProjectileScale();
            // Grows gradually up to 5x its original size
            if (currentScale < 5.0f) {
                this.dataManager.set(SCALE, currentScale + 0.1f);
            }
            if (this.ticksExisted > 60) {
                this.setDead();
            }
        }
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            if (result.entityHit != null) {
                result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 5.0F + getProjectileScale());
            }
            this.setDead();
        }
    }
}
