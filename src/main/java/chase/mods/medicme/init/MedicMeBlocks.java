package chase.mods.medicme.init;

import cpw.mods.fml.common.registry.GameRegistry;
import chase.mods.medicme.block.*;
import chase.mods.medicme.block.machine.*;
import chase.mods.medicme.reference.Names;

public class MedicMeBlocks
{
	public static BaseBlock MedicStationCoreXP = new MedicStationCore(MedicStationCoreType.xp);
	public static BaseBlock MedicStationCoreHP = new MedicStationCore(MedicStationCoreType.hp);
	public static BaseBlock MedicStationCoreRF = new MedicStationCore(MedicStationCoreType.rf);
	public static BaseBlock MedicStationPlate = new MedicStationPlate();
	public static BaseBlock MedicStationFrame = new MedicStationFrame();
	
	public static void init()
	{
		GameRegistry.registerBlock(MedicStationCoreXP, Names.Block.MedicStationCoreXP);
		GameRegistry.registerBlock(MedicStationCoreHP, Names.Block.MedicStationCoreHP);
		GameRegistry.registerBlock(MedicStationCoreRF, Names.Block.MedicStationCoreRF);
		GameRegistry.registerBlock(MedicStationPlate, Names.Block.MedicStationPlate);
		GameRegistry.registerBlock(MedicStationFrame, Names.Block.MedicStationFrame);
	}
}
