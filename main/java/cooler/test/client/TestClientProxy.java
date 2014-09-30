package cooler.test.client;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.WavefrontObject;
import cooler.test.Anime;
import cooler.test.TestCommonProxy;
import cpw.mods.fml.client.registry.ClientRegistry;

public class TestClientProxy extends TestCommonProxy
{
	TileAnimeRenderer AnRend = new TileAnimeRenderer(new WavefrontObject(new ResourceLocation("test","models/test.obj")));
	public void registerTESR()
	{
        ClientRegistry.bindTileEntitySpecialRenderer(Anime.class, AnRend);
    }
}