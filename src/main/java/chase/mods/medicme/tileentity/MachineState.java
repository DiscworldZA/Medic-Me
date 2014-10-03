package chase.mods.medicme.tileentity;

public enum MachineState
{
	OFF, ON, PAUSED;
	
	public int getID()
	{
		return this.ordinal();
	}
}
