package cooler.test.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class TileAnimeRenderer extends  TileEntitySpecialRenderer
{

	private final WavefrontObject model;
	public TileAnimeRenderer(WavefrontObject model)
	{
		this.model = model;
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f)
	{
		this.bindTexture(TextureMap.locationBlocksTexture);
		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		tessellator.startDrawing(GL11.GL_TRIANGLES);
		RenderingUtils.renderWithIcon(model, tileentity.blockType.getIcon(0, 0), tessellator);
		tessellator.draw();
		GL11.glPopMatrix();
	}
}
