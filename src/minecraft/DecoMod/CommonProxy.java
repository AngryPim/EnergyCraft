package DecoMod;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Tessellator;
import net.minecraft.src.TileEntityRenderer;
import net.minecraft.src.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy implements IGuiHandler
{
	public static int modelID;
	public static String Entitys =   "/DecoMod/Medieval/Entitys/";
	public static int RenderParticleMachineID;

	public static Block particleMachine = (new BlockParticleMachine(2000, 0, TileEntityParticleMachine.class , true, Entitys +"ParticleMachine Off.png")).setBlockName("forgetestblock").setHardness(1F).setResistance(10F).setCreativeTab(CreativeTabs.tabBlock);
	public static Block particleMachineOn = (new BlockParticleMachine(2001, 0, TileEntityParticleMachine.class, true, Entitys +"ParticleMachine Off.png")).setBlockName("forgetgerdfgfdestblock").setHardness(1F).setResistance(10F).setCreativeTab(CreativeTabs.tabBlock);
	
	public static void registerRenderInformation()
	{
		
		GameRegistry.registerTileEntity(TileEntityParticleMachine.class, "particleMachine");
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		return null;
	}
	public void renderInvBlock(Block block, int metadata, int modelID, RenderBlocks renderer, BlockModelRenderer modelRenderer)
	{
		Tessellator tessellator = Tessellator.instance;
		if (modelID == RenderParticleMachineID)
		{	
			TileEntityRenderer.instance.renderTileEntityAt(new TileEntityParticleMachine(), -0.5D, -0.5D, -0.5D, -50.0F);
		}
	}
}