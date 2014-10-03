package chase.mods.medicme.block.machine;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import chase.mods.medicme.block.BaseBlock;
import chase.mods.medicme.reference.Names;

public class MedicStationPlate extends BaseBlock
{
	
	boolean state = false;
	
	public MedicStationPlate()
	{
		this.setBlockName(Names.Block.MedicStationPlate);
		this.setBlockBounds(0, 0.9F, 0, 1, 1F, 1);
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
	{
		this.setBlockBounds(0, 0.9F, 0, 1, 1F, 1);
	}
	
	@Override
	public void setBlockBoundsForItemRender()
	{
		this.setBlockBounds(0, 0.9F, 0, 1, 1F, 1);
	}
}
