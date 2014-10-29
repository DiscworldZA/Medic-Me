package chase.mods.medicme.plants;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BasePlant extends Block
{
	
	private String plantName;
	public ArrayList<ItemStack> itemDrops;
	public Item plantSeed;
	
	public BasePlant(String plantName)
	{
		super(Material.grass);
		this.setTickRandomly(true);
		this.setCreativeTab((CreativeTabs) null);
		this.setName(plantName);
		plantSeed = new ItemSeeds(this, Blocks.farmland);
	}
	
	public void addDrop(ItemStack item)
	{
		itemDrops.add(item);
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
	}
	
	public int getRenderType()
	{
		return 6;
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public BasePlant setName(String name)
	{
		this.setBlockName(name);
		this.plantName = name;
		return this;
	}
	
	public String getName()
	{
		return plantName;
	}
	
	public ItemStack getSeed()
	{
		return new ItemStack(plantSeed, 1);
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		for (ItemStack item : itemDrops)
		{
			drops.add(item);
		}
		for (int i = 0; i < world.rand.nextInt(3); i++)
		{
			drops.add(this.getSeed());
		}
		return drops;
	}
	
	public boolean fertilize(World world, int x, int y, int z, EntityPlayer player)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta > 13) return false;
		int l = meta + MathHelper.getRandomIntegerInRange(world.rand, 2, 5);
		if (meta < 6 && l > 6)
		{
			l = 6;
		}
		else if (meta > 6 && l > 13)
		{
			l = 13;
		}
		world.setBlockMetadataWithNotify(x, y, z, l, 3);
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z)
	{
		return this.plantSeed;
	}
}
