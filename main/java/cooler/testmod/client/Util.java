package cooler.testmod.client;

import java.util.Map;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.model.obj.WavefrontObject;
import net.minecraftforge.client.model.obj.GroupObject;
import net.minecraftforge.client.model.obj.Face;
import net.minecraftforge.client.model.obj.Vertex;
import net.minecraftforge.client.model.obj.TextureCoordinate;

public class Util
{
	public static void renderWithIcon(WavefrontObject model, IIcon icon, Tessellator tes)
	{
		for (GroupObject go : model.groupObjects)
		{
			for (Face f : go.faces)
			{
				Vertex n = f.faceNormal;
				tes.setNormal(n.x, n.y, n.z);
				for (int i = 0; i < f.vertices.length; i++)
				{
					Vertex v = f.vertices[i];
					TextureCoordinate t = f.textureCoordinates[i];
					tes.addVertexWithUV(v.x, v.y, v.z, icon.getInterpolatedU(t.u * 16), icon.getInterpolatedV(t.v * 16));
				}
			}
		}
	}
}