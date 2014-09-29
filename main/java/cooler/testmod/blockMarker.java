package cooler.testmod;

import cooler.testmod.client.testmodClientProxy;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class blockMarker extends Block
{
	public static int renderid;
	private static final float vox = 0.0625f; 

    public blockMarker()
    {
        super(Material.glass);
        this.setCreativeTab(TestMod.tabTestMod);
        this.setHardness(3.0F);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, vox, vox, vox);
		this.setBlockName("BlockMarker");
    }
    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        this.blockIcon = register.registerIcon("tm:white");
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