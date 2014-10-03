package chase.mods.medicme.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import chase.mods.medicme.reference.Names;

public abstract class BaseTEBlock extends TileEntity
{
    public ForgeDirection orientation;
    public String name;
    public String customName;
    public String owner;
	
	
	public BaseTEBlock()
	{
		name = "";
        orientation = ForgeDirection.SOUTH;
        customName = "";
        owner = "";
	}
	

    public ForgeDirection getOrientation()
    {
        return orientation;
    }

    public void setOrientation(ForgeDirection orientation)
    {
        this.orientation = orientation;
    }

    public void setOrientation(int orientation)
    {
        this.orientation = ForgeDirection.getOrientation(orientation);
    }
    
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = "container."+ Names.Mod.ID + "." + name;
    }
    
    
    public String getCustomName()
    {
        return customName;
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    public String getOwner()
    {
        return owner;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        if (nbtTagCompound.hasKey(Names.NBT.TileEntity.Direction))
        {
            this.orientation = ForgeDirection.getOrientation(nbtTagCompound.getByte(Names.NBT.TileEntity.Direction));
        }

        if (nbtTagCompound.hasKey(Names.NBT.TileEntity.CustomName))
        {
            this.customName = nbtTagCompound.getString(Names.NBT.TileEntity.CustomName);
        }

        if (nbtTagCompound.hasKey(Names.NBT.TileEntity.Owner))
        {
            this.owner = nbtTagCompound.getString(Names.NBT.TileEntity.Owner);
        }
    }
    
    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setByte(Names.NBT.TileEntity.Direction, (byte) orientation.ordinal());

        if (this.hasCustomName())
        {
            nbtTagCompound.setString(Names.NBT.TileEntity.CustomName, customName);
        }

        if (this.hasOwner())
        {
            nbtTagCompound.setString(Names.NBT.TileEntity.Owner, owner);
        }
    }
    
    public boolean hasCustomName()
    {
        return customName != null && customName.length() > 0;
    }

    public boolean hasOwner()
    {
        return owner != null && owner.length() > 0;
    }
	
	public abstract void updateEntity();
	
	
}
