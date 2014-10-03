package chase.mods.medicme.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import chase.mods.medicme.MedicMe;
import chase.mods.medicme.creativetab.CreativeTab;
import chase.mods.medicme.helpers.BlockHelper;
import chase.mods.medicme.reference.Gui;
import chase.mods.medicme.reference.Gui.ID;
import chase.mods.medicme.reference.Textures;
import cofh.api.item.IToolHammer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Chase
 * 
 */
public class BaseBlock extends Block
{	
	Gui.ID guiID;
	
	public String textureLocation;
	
	public BaseBlock()
	{
		this(Material.rock);
	}
	
	public BaseBlock(Material material)
	{
		super(material);
		this.setCreativeTab(CreativeTab.MM_Tab);
	}
	
	public Gui.ID getGUIID()
	{
		return guiID;
	}
	
	protected void setGUIID(ID id)
	{
		this.guiID = id;
	}
	
	public boolean hasGUIID()
	{
		return this.guiID != null;
	}
	
	public void setTexture(String location)
	{
		this.textureLocation = location;
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
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return String.format("tile.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		dropInventory(world, x, y, z);
		super.breakBlock(world, x, y, z, block, meta);
	}
	
	public boolean getUseNeighborBrightness()
	{
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack)
	{
		ForgeDirection direction = BlockHelper.getFacingForHeadingEntity(entityLivingBase);
		int meta = world.getBlockMetadata(x, y, z);
		world.setBlockMetadataWithNotify(x, y, z, BlockHelper.setOrientation(meta, direction), 2);
	}
	
	protected void dropInventory(World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		
		if (!(tileEntity instanceof IInventory))
		{
			return;
		}
		
		IInventory inventory = (IInventory) tileEntity;
		
		for (int i = 0; i < inventory.getSizeInventory(); i++)
		{
			ItemStack itemStack = inventory.getStackInSlot(i);
			
			if (itemStack != null && itemStack.stackSize > 0)
			{
				Random rand = new Random();
				
				float dX = rand.nextFloat() * 0.8F + 0.1F;
				float dY = rand.nextFloat() * 0.8F + 0.1F;
				float dZ = rand.nextFloat() * 0.8F + 0.1F;
				
				EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, itemStack.copy());
				
				if (itemStack.hasTagCompound())
				{
					entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
				}
				
				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				itemStack.stackSize = 0;
			}
		}
	}
	
	public boolean breakWithHammer(World world, EntityPlayer player, int x, int y, int z)
	{
		if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof IToolHammer)
		{
			this.dropBlockAsItem(world, x, y, z, new ItemStack(this));
			dropInventory(world, x, y, z);
			world.setBlock(x, y, z, net.minecraft.init.Blocks.air);
			world.removeTileEntity(x, y, z);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float lookPosX, float lookPosY, float lookPosZ)
	{
		if (this.hasGUIID() && !player.isSneaking())
		{
			player.openGui(MedicMe.instance, this.getGUIID().getID(), world, x, y, z);
			return true;
		}
		return false;
	}
}
