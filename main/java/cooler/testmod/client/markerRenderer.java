package cooler.testmod.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.obj.WavefrontObject;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class markerRenderer implements ISimpleBlockRenderingHandler
{
	private final int renderId;
	private final Tessellator tes = Tessellator.instance;
	private static int x,y,z;
	private static final float vox = 0.0625f; 
	private static final float[][][] p = 
		{{{x,y,z},{x,y+vox,z},{x,y+vox,z+vox},{x,y,z+vox}}
		,{{x+vox,y,z},{x+vox,y+vox,z},{x+vox,y+vox,z+vox},{x+vox,y,z+vox}}
		,{{x,y,z},{x+vox,y,z},{x+vox,y,z+vox},{x,y,z+vox}}
		,{{x,y+vox,z},{x+vox,y+vox,z},{x+vox,y+vox,z+vox},{x,y+vox,z+vox}}
		,{{x,y,z},{x+vox,y,z},{x+vox,y+vox,z},{x,y+vox,z}}
		,{{x,y,z+vox},{x+vox,y,z+vox},{x+vox,y+vox,z+vox},{x,y+vox,z+vox}}};

	public markerRenderer(int renderId)
	{
		this.renderId = renderId;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,RenderBlocks renderer)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,Block block, int modelId, RenderBlocks renderer)
	{
		IIcon test = block.getIcon(0,0);
		tes.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
		tes.setColorOpaque_F(1, 1, 1);
		tes.addTranslation(x, y, z);


		x=y=z=0;
		for(int i=0;i<6;i++)
		{
			tes.addVertexWithUV(p[i][0][0], p[i][0][1], p[i][0][2], test.getMinU(), test.getMinV());//bottom left texture
			tes.addVertexWithUV(p[i][1][0], p[i][1][1], p[i][1][2], test.getMinU(), test.getMaxV());//top left
			tes.addVertexWithUV(p[i][2][0], p[i][2][1], p[i][2][2], test.getMaxU(), test.getMaxV());//top right
			tes.addVertexWithUV(p[i][3][0], p[i][3][1], p[i][3][2], test.getMaxU(), test.getMinV());
		}
		for(int i=0;i<6;i++)
		{
			tes.addVertexWithUV(p[i][3][0], p[i][3][1], p[i][3][2], test.getMaxU(), test.getMinV());
			tes.addVertexWithUV(p[i][2][0], p[i][2][1], p[i][2][2], test.getMaxU(), test.getMaxV());//top right
			tes.addVertexWithUV(p[i][1][0], p[i][1][1], p[i][1][2], test.getMinU(), test.getMaxV());//top left
			tes.addVertexWithUV(p[i][0][0], p[i][0][1], p[i][0][2], test.getMinU(), test.getMinV());//bottom left texture	
		}





		tes.addTranslation(-x, -y, -z);
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getRenderId()
	{
		// TODO Auto-generated method stub
		return renderId;
	}

}