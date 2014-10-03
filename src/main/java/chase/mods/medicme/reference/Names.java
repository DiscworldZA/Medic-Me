package chase.mods.medicme.reference;

public class Names
{
	public static final class Mod
	{
		public static final String Name = "Medic Me";
		public static final String ID = "medicme";
		public static final String Version = "0.1 Alpha";
	}
	
	public static final class NBT
	{
		public static final class TileEntity
		{
			public static final String Direction = "direction";
			public static final String CustomName = "customname";
			public static final String Owner = "owner";
			public static final String Items = "items";
			
			public static final String State = "state";
			
			public static final String Temp = "temperature";
		}
	}
	
	public static final class Proxy
	{
		public static final String CLIENT_CLASS = "chase.mods.medicme.proxy.ClientProxy";
		public static final String SERVER_CLASS = "chase.mods.medicme.proxy.ServerProxy";
	}
	
	public static final class Block
	{
		public static final String MedicStationCore = "medicStationCore";
		public static final String MedicStationCoreXP = "medicStationCoreXP";
		public static final String MedicStationCoreHP = "medicStationCoreHP";
		public static final String MedicStationCoreRF = "medicStationCoreRF";
		public static final String MedicStationPlate = "medicStationPlate";
		public static final String MedicStationFrame = "medicStationFrame";
	}
}
