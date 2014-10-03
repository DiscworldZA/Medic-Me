package chase.mods.medicme.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import chase.mods.medicme.reference.Names;

public class CreativeTab
{
	public static final CreativeTabs MM_Tab = new CreativeTabs(Names.Mod.ID.toLowerCase())
	{
		@Override
		public Item getTabIconItem()
		{
			return Items.clock;
		}
	};
}
