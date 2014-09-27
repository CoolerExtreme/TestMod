package cooler.testmod;

import cooler.testmod.client.testmodClientProxy;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.IBlockAccess;

public class blockTable extends Block
{
	public static int renderid;
	public blockTable()
	{
		super(Material.glass);
		this.setCreativeTab(TestMod.tabTestMod);
		this.setHardness(3.0F);
		this.setBlockName("BlockTable");
	}
	@Override
    public void registerBlockIcons(IIconRegister register)
    {
        this.blockIcon = register.registerIcon("tm:cubetexture");
    }
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	@Override
	public int getRenderType()
	{
		return renderid;
	}
}