package chase.mods.medicme.plants;

import cpw.mods.fml.common.registry.GameRegistry;

public class MedicMePlants
{
	public final static BasePlant leafPlant = new BasePlant("leftPlant");
	
	public static void init()
	{
		GameRegistry.registerBlock(leafPlant, leafPlant.getName());
	}
}
