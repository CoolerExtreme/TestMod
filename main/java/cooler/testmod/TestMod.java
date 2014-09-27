package cooler.testmod;

import cooler.testmod.client.testmodClientProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "tm", name = "Test Mod", version = "1.0")
public class TestMod {
	@SidedProxy(clientSide="cooler.testmod.client.testmodClientProxy", serverSide="cooler.testmod.common.testmodCommonProxy")
	public static testmodClientProxy proxy;
	
	public static Item itemRock;
	public static Block blockTable;
	public static Block blockMarker;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		itemRock = new itemRock();
		
		blockTable = new blockTable();
		blockMarker = new blockMarker();
		
		GameRegistry.registerItem(itemRock, itemRock.getUnlocalizedName().substring(5));
		
		GameRegistry.registerBlock(blockTable, blockTable.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(blockMarker, blockMarker.getUnlocalizedName().substring(5));

		EntityRegistry.registerModEntity(EntityThrowingRock.class, "Throwing Rock", 1, this, 64, 10, true);
		
		proxy.preInit();
		proxy.registerRenderers();
	}
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	
	public static CreativeTabs tabTestMod = new CreativeTabs("tabTestMod")
	{
		@Override
		public Item getTabIconItem()
		{
			return new ItemStack(itemRock).getItem();
		}
	};
}
