package cooler.test;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAnim extends Block implements ITileEntityProvider
{
	protected BlockAnim(Material material)
	{
		super(material);
		this.setBlockName("blockAnime");
		this.setHardness(3.0f);
		this.setCreativeTab(testcore.tabtest);
		this.setBlockBounds(0, 0, 0, 1.0f, 0.4f, 1.0f);
	}

	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.blockIcon = register.registerIcon("test:PlanetaryTex");
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
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new Anime();
	}
}
