package chase.mods.medicme;

import chase.mods.medicme.init.MedicMeBlocks;
import chase.mods.medicme.plants.MedicMePlants;
import chase.mods.medicme.proxy.IProxy;
import chase.mods.medicme.reference.Names;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Names.Mod.ID, name = Names.Mod.Name, version = Names.Mod.Version)
public class MedicMe
{
	@Instance(Names.Mod.ID)
	public static MedicMe instance;
	
	@SidedProxy(clientSide = Names.Proxy.CLIENT_CLASS, serverSide = Names.Proxy.SERVER_CLASS)
	public static IProxy proxy;
	
	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event)
	{
		// Register Commands
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		// Init Config
		// Init PacketHandler
		// Init Items
		// Init Blocks
		MedicMeBlocks.init();
		MedicMePlants.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		// Register Gui Handler
		// Register WorldGen
		// Register Tile Entities
		proxy.registerTileEntities();
		// Init Custom Rendering and Textures
		// Register EventHandlers
		// Init CraftingHandler
		// Init RecipeHandler
		// Register FuelHandler
	}
}
