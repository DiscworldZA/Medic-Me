package chase.mods.medicme.block.machine;

import chase.mods.medicme.reference.Names;

public enum MedicStationCoreType
{
	xp(Names.Block.MedicStationCoreXP),
	hp(Names.Block.MedicStationCoreHP),
	rf(Names.Block.MedicStationCoreRF);
	
	private String Name;
	
	private MedicStationCoreType(String Name)
    {
		this.Name = Name;
    }
	
	public String getName()
	{
		return Name;
	}
}
