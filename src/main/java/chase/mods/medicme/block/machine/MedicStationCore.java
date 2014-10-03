package chase.mods.medicme.block.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import chase.mods.medicme.reference.Gui;
import chase.mods.medicme.reference.Names;
import chase.mods.medicme.tileentity.machine.TEMedicStationCore;

public class MedicStationCore extends BaseBlockMachine
{
	MedicStationCoreType type;
	
	public MedicStationCore(MedicStationCoreType type)
	{
		this.setBlockName(type.getName());
		this.type = type;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TEMedicStationCore(type);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float lookPosX, float lookPosY, float lookPosZ)
	{
		if (player.getHeldItem() == null && player.isSneaking())
		{
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile instanceof TEMedicStationCore)
			{
				TEMedicStationCore core = (TEMedicStationCore) tile;
				if (type == MedicStationCoreType.xp)
				{
					if (player.experienceLevel > 0)
					{
						player.experienceLevel--;
						core.addHealth(10);
					}
				}
				if (type == MedicStationCoreType.hp)
				{
					if (player.getHealth() > 1)
					{
						player.heal(-1);
						core.addHealth(1);
					}
				}
			}
		}
		return super.onBlockActivated(world, x, y, z, player, metadata, lookPosX, lookPosY, lookPosZ);
	}
	
}
