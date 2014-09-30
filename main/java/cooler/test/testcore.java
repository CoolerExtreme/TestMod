package cooler.test;

import cooler.test.client.TestClientProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "test",name = "Test Mod",version = "1.0")
public class testcore
{
	public static Block anim;
	public static TileEntity anime;
	
	@SidedProxy(clientSide="cooler.test.client.TestClientProxy", serverSide="cooler.test.TestCommonProxy")
	public static TestCommonProxy CommonProxy;
	public static TestClientProxy ClientProxy;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		anim = new BlockAnim(Material.rock);
		anime = new Anime();
		GameRegistry.registerBlock(anim, anim.getUnlocalizedName().substring(5));
		GameRegistry.registerTileEntity(Anime.class, "test.animated");
	}

	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		ClientProxy.registerTESR();
	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent event)
	{

	}

	public static CreativeTabs tabtest = new CreativeTabs("tabtest")
	{
		@Override
		public Item getTabIconItem()
		{
			return new ItemStack(Items.redstone).getItem();
		}
	};
}
