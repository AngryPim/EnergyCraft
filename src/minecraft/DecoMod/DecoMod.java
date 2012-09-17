package DecoMod;

import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Tessellator;
import net.minecraft.src.TileEntityRenderer;

public class DecoMod {
	public static int RenderParticleMachineID;

	public void renderInvBlock(Block block, int metadata, int modelID, RenderBlocks renderer, BlockModelRenderer modelRenderer)
	{
		Tessellator tessellator = Tessellator.instance;
		if (modelID == RenderParticleMachineID)
		{	
			TileEntityRenderer.instance.renderTileEntityAt(new TileEntityParticleMachine(), -0.5D, -0.5D, -0.5D, -50.0F);
		}
	}

	public boolean renderWorldBlock(RenderBlocks renderer, IBlockAccess world,
			int x, int y, int z, Block block, int modelId) {
		// TODO Auto-generated method stub
		return false;
	}
}
