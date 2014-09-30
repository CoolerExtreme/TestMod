package cooler.test;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class Anime extends TileEntity
{
	public int customField = 0;
	
	public void updateEntity()
	{
		customField++;
		System.out.println(customField);
	}

	@Override
	public void writeToNBT(NBTTagCompound par1)
	{
		super.writeToNBT(par1);
		par1.setInteger("customField", customField);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1)
	{
		super.readFromNBT(par1);
		this.customField = par1.getInteger("customField");
	}
}
