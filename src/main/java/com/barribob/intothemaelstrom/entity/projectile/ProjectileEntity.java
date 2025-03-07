package com.barribob.intothemaelstrom.entity.projectile;

import com.barribob.intothemaelstrom.util.Element;
import com.barribob.intothemaelstrom.util.IElement;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * The base projectile class for most projectiles in the mod
 * <p>
 * The other two constructors immediately cause the projectile to despawn (e.g. if the projectile is saved in midair, just despawn it)
 * <p>
 * This is intended for offensive projectiles, as there is damage included
 */
public class ProjectileEntity extends EntityModThrowable implements IElement {
    protected float travelRange;
    private final Vec3d startPos;
    protected static final byte IMPACT_PARTICLE_BYTE = 3;
    private static final byte PARTICLE_BYTE = 4;
    private double damage = 0;
    protected static final DataParameter<Integer> ELEMENT = EntityDataManager.createKey(ProjectileEntity.class, DataSerializers.VARINT);
    protected float maxAge = 20 * 20;
    private Item itemToRender = Items.AIR;

    public ProjectileEntity(World worldIn, EntityLivingBase throwerIn, double damage) {
        super(worldIn, throwerIn);
        this.travelRange = 20.0f;
        this.setDamage(damage);
        this.startPos = new Vec3d(this.posX, this.posY, this.posZ);
        if (throwerIn instanceof IElement) {
            this.setElement(((IElement) throwerIn).getElement());
        }
    }

    public ProjectileEntity(World worldIn) {
        super(worldIn);
        this.startPos = new Vec3d(this.posX, this.posY, this.posZ);
    }

    public ProjectileEntity(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        this.startPos = new Vec3d(this.posX, this.posY, this.posZ);
    }

    protected double getDistanceTraveled() {
        return this.getDistance(startPos.x, startPos.y, startPos.z);
    }

    /**
     * Set how far the projectile will be from its shooting entity before despawning
     *
     * @param distance
     */
    public void setTravelRange(float distance) {
        this.travelRange = distance;
    }

    /**
     * Sets the damage for use by inherited projectiles
     */
    protected void setDamage(double damage) {
        this.damage = damage;
    }

    /*
     * For use of inhereted projectiles
     */
    protected double getDamage() {
        return this.damage;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        this.world.setEntityState(this, PARTICLE_BYTE);

        // Despawn if a certain distance away from its origin
        if (this.shootingEntity != null && getDistanceTraveled() > this.travelRange) {
            this.world.setEntityState(this, IMPACT_PARTICLE_BYTE);
            this.setDead();
        }

        // Despawn if older than a certain age
        if (this.ticksExisted > this.maxAge) {
            this.setDead();
        }
    }

    @Override
    protected void onHit(RayTraceResult result) {
        if (!world.isRemote) {
            this.world.setEntityState(this, IMPACT_PARTICLE_BYTE);
            this.setDead();
        }
    }

    /**
     * Handler for {@link World#setEntityState} Connected through setEntityState to
     * spawn particles
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == IMPACT_PARTICLE_BYTE) {
            spawnImpactParticles();
        }
        if (id == PARTICLE_BYTE) {
            spawnParticles();
        }
    }

    /**
     * Called every update to spawn particles
     *
     * @param world
     */
    protected void spawnParticles() {
    }

    /**
     * Called on impact to spawn impact particles
     */
    protected void spawnImpactParticles() {
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(ELEMENT, Element.NONE.id);
    }

    @Override
    public Element getElement() {
        return this.dataManager == null ? Element.getElementFromId(Element.NONE.id) : Element.getElementFromId(this.dataManager.get(ELEMENT));
    }

    public ProjectileEntity setElement(Element element) {
        this.dataManager.set(ELEMENT, element.id);
        return this;
    }

    public Item getItemToRender() {
        return itemToRender;
    }

    public ProjectileEntity setItemToRender(Item itemToRender) {
        this.itemToRender = itemToRender;
        return this;
    }
}
