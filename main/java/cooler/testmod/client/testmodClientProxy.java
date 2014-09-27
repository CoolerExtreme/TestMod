package cooler.testmod.client;

import java.util.Map;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.GroupObject;
import net.minecraftforge.client.model.obj.WavefrontObject;
import net.minecraftforge.client.model.techne.TechneModel;
import cooler.testmod.EntityThrowingRock;
import cooler.testmod.TestMod;
import cooler.testmod.blockMarker;
import cooler.testmod.blockTable;
import cooler.testmod.testmodCommonProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class testmodClientProxy extends testmodCommonProxy
{
    public static tableRenderer table;
    public static markerRenderer marker;

    @Override
    public void preInit()
    {
        blockTable.renderid = RenderingRegistry.getNextAvailableRenderId();
        blockMarker.renderid = RenderingRegistry.getNextAvailableRenderId();

    }
	public void registerRenderers()
	{		
		RenderingRegistry.registerEntityRenderingHandler(EntityThrowingRock.class, new RenderItemRock(TestMod.itemRock));
		
        table = new tableRenderer(new WavefrontObject(new ResourceLocation("tm","models/cube.obj")), blockTable.renderid);
        marker = new markerRenderer(blockMarker.renderid);
		RenderingRegistry.registerBlockHandler(table);
		RenderingRegistry.registerBlockHandler(marker);

	}
}