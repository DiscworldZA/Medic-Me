package chase.mods.medicme.proxy;

import chase.mods.medicme.reference.Names;
import chase.mods.medicme.tileentity.machine.TEMedicStationCore;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy
{
	
	@Override
	public void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TEMedicStationCore.class, "tile." + Names.Block.MedicStationCore);
	}
	
	@Override
	public void registerEventHandlers()
	{
		// TODO Auto-generated method stub
		
	}
}
