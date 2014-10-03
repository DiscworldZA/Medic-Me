package chase.mods.medicme.reference;

import java.awt.Rectangle;

import net.minecraft.util.ResourceLocation;
import chase.mods.medicme.helpers.ResourceLocationHelper;

public class Textures
{
	public static final String RESOURCE_PREFIX = Names.Mod.ID.toLowerCase() + ":";
	
	public static final class GUI
	{
		public static final class BaseGui
		{
			public static final ResourceLocation BaseGuiResource = ResourceLocationHelper.getGuiLocation("BaseGui.png");
			public static final Rectangle Gui = new Rectangle(0, 0, 176, 166);
		}
	}
}
