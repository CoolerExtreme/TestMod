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
		tes.addVertexWithUV(0, 0, 0, test.getMinU(), test.getMinV());//bottom left texture
		tes.addVertexWithUV(0, 1, 0, test.getMinU(), test.getMaxV());//top left
		tes.addVertexWithUV(1, 1, 0, test.getMaxU(), test.getMaxV());//top right
		tes.addVertexWithUV(1, 0, 0, test.getMaxU(), test.getMinV());
		tes.addTranslation(x, y, z);
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
