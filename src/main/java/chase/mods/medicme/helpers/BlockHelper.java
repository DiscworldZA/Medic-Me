package chase.mods.medicme.helpers;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;

public class BlockHelper
{
	public static final int MASK_REDSTONE = 0x8;
	public static final int MASK_ORIENTATION = 0x7;
	
	public static final int[] DIRECTIONS = new int[] { 2, 5, 3, 4 };
	
	public static ForgeDirection getOrientation(int metadata)
	{
		return ForgeDirection.getOrientation(metadata & MASK_ORIENTATION);
	}
	
	public static int setOrientation(int metadata, ForgeDirection orientation)
	{
		return (metadata & ~MASK_ORIENTATION) | orientation.ordinal();
	}
	
	public static boolean getRedstoneSignal(int metadata)
	{
		return (metadata & MASK_REDSTONE) != 0;
	}
	
	public static int setRedstoneSignal(int metadata, boolean signal)
	{
		if (signal)
		{
			return metadata | MASK_REDSTONE;
		}
		else
		{
			return metadata & ~MASK_REDSTONE;
		}
	}
	
	public static ForgeDirection determineOrientation(int x, int y, int z, EntityLivingBase entityLivingBase)
	{
		if (MathHelper.abs((float) entityLivingBase.posX - x) < 2.0F && MathHelper.abs((float) entityLivingBase.posZ - z) < 2.0F)
		{
			double d0 = entityLivingBase.posY + 1.82D - entityLivingBase.yOffset;
			
			if (d0 - y > 2.0D)
			{
				return ForgeDirection.UP;
			}
			
			if (y - d0 > 0.0D)
			{
				return ForgeDirection.DOWN;
			}
		}
		int l = MathHelper.floor_double((entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		return l == 0 ? ForgeDirection.NORTH : (l == 1 ? ForgeDirection.EAST : (l == 2 ? ForgeDirection.SOUTH : (l == 3 ? ForgeDirection.WEST : ForgeDirection.DOWN)));
	}
	
	public static ForgeDirection getFacingForHeadingEntity(EntityLivingBase entity)
	{
		return getFacingForHeading(MathHelper.floor_double(
				(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3);
	}
	
	public static ForgeDirection getFacingForHeading(int heading) 
	{
	    return ForgeDirection.getOrientation(DIRECTIONS[heading]);
	}
	
	
	
	public void registerToOreDictionary(String string, Block block)
	{
		OreDictionary.registerOre(string, block);
	}
}
