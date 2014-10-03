package chase.mods.medicme.tileentity.machine;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import chase.mods.medicme.block.machine.MedicStationCore;
import chase.mods.medicme.block.machine.MedicStationCoreType;
import chase.mods.medicme.block.machine.MedicStationFrame;
import chase.mods.medicme.block.machine.MedicStationPlate;
import chase.mods.medicme.tileentity.BaseTEBlockPower;

public class TEMedicStationCore extends BaseTEBlockPower
{
	MedicStationCoreType type;
	private int health = 0;
	private int tick = 0;
	ForgeDirection botPlateAt = ForgeDirection.UNKNOWN;
	
	public TEMedicStationCore()
	{
		createInventory(2);
		storage.setCapacity(3200);
	}
	
	public TEMedicStationCore(MedicStationCoreType type)
	{
		this();
		this.type = type;
		if (type == MedicStationCoreType.rf)
		{
			recieveAllSides();
		}
	}
	
	public void addHealth(int val)
	{
		health += val;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	@Override
	public void updateEntity()
	{
		if (!worldObj.isRemote)
		{
			if (isMultiBlock())
			{
				tick++;
				if (tick % 5 == 0)
				{
					int minX = (int) Math.floor(this.xCoord + botPlateAt.offsetX);
					int minY = (int) Math.floor(this.yCoord - 1);
					int minZ = (int) Math.floor(this.zCoord + botPlateAt.offsetZ);
					
					int maxX = (int) Math.ceil(this.xCoord + botPlateAt.offsetX + 1);
					int maxY = (int) Math.ceil(this.yCoord);
					int maxZ = (int) Math.ceil(this.zCoord + botPlateAt.offsetZ + 1);
					AxisAlignedBB bound = AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
					List<EntityPlayer> players = worldObj.getEntitiesWithinAABB(EntityPlayer.class, bound);
					if (players != null && players.size() > 0)
					{
						for (int i = 0; i < players.size(); i++)
						{
							if (players.get(i) instanceof EntityPlayer)
							{
								EntityPlayer player = players.get(i);
								if (this.type == MedicStationCoreType.xp && health > 0)
								{
									if (player.getHealth() < 20)
									{
										player.heal(1);
										health -= 1;
									}
									if (player.getFoodStats().needFood())
									{
										player.getFoodStats().addStats(1, 0.5f);
										health -= 1;
									}
								}
								if (this.type == MedicStationCoreType.rf)
								{
									if (this.storage.getEnergyStored() > 80 && player.getHealth() < 20)
									{
										this.storage.extractEnergy(40, false);
										player.heal(1);
										if (player.getFoodStats().needFood())
										{
											player.getFoodStats().addStats(1, 0.5f);
											this.storage.extractEnergy(40, false);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("type", type.ordinal());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		if (nbt.hasKey("type"))
		{
			this.type = MedicStationCoreType.values()[nbt.getInteger("type")];
		}
	}
	
	public boolean isMultiBlock()
	{
		boolean hasFrame = false;
		boolean hasBotPlate = false;
		boolean hasTopPlate = false;
		if (worldObj.getBlock(xCoord, yCoord - 1, zCoord) instanceof MedicStationFrame)
		{
			hasFrame = true;
		}
		
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
		{
			if (dir == ForgeDirection.UP || dir == ForgeDirection.DOWN)
			{
				continue;
			}
			
			int x = xCoord + dir.offsetX;
			int z = zCoord + dir.offsetZ;
			int y = yCoord - 2;
			
			if (worldObj.getBlock(x, y, z) instanceof MedicStationPlate)
			{
				hasBotPlate = true;
				botPlateAt = dir;
				break;
			}
			
		}
		
		if (botPlateAt != ForgeDirection.UNKNOWN)
		{
			int x = xCoord + botPlateAt.offsetX;
			int z = zCoord + botPlateAt.offsetZ;
			int y = yCoord;
			if (worldObj.getBlock(x, y, z) instanceof MedicStationPlate)
			{
				hasTopPlate = true;
			}
		}
		return hasFrame && hasBotPlate && hasTopPlate;
	}
	
}
