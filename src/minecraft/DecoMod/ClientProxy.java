package DecoMod;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class ClientProxy extends CommonProxy
{
        @SideOnly(Side.CLIENT)
	public static void registerRenderInformation()
	{
		
	}
}