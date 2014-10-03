package chase.mods.medicme.block.machine;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import chase.mods.medicme.block.BaseBlock;
import chase.mods.medicme.helpers.BlockHelper;
import chase.mods.medicme.tileentity.BaseTEBlockPower;
import chase.mods.medicme.tileentity.MachineState;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BaseBlockMachine extends BaseBlock implements ITileEntityProvider
{
	public BaseBlockMachine()
	{
		this(Material.iron);
	}
	
	public BaseBlockMachine(Material material)
	{
		super(material);
		this.setHardness(0.5F);
	}
	
	@Override
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return null;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		if (this.textureLocation != null && this.textureLocation != "")
		{
			blockIcon = iconRegister.registerIcon(this.textureLocation);
		}
		else
		{
			blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
		}
	}
}
