package cooler.testmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityThrowingRock extends EntityThrowable
{
	public EntityThrowingRock(World par1World)
	{
		super(par1World);
	}
	public EntityThrowingRock(World par1World, EntityLivingBase par2EntityLivingBase)
	{
		super(par1World, par2EntityLivingBase);
	}
	public EntityThrowingRock(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
	}

	protected void onImpact(MovingObjectPosition movObjPos)
	{
		if (movObjPos.entityHit != null)
		{
			float damage = 10.0F;
			movObjPos.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)damage);
		}
		for (int i = 0; i < 8; ++i)
		{
			this.worldObj.spawnParticle("crit", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		}
		if (!this.worldObj.isRemote)
		{
			this.setDead();
		}
	}
}