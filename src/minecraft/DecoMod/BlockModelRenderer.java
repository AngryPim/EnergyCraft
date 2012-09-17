// Quintinity's Code
package DecoMod;
import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Tessellator;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

//NEW! This is now where you do your custom block renders
public class BlockModelRenderer implements ISimpleBlockRenderingHandler 
{
	public DecoMod mod;
	private int id;
	private boolean inv;
	
	public BlockModelRenderer(DecoMod mod, int id, boolean inv)
	{
		this.mod = mod;
		this.id = id;
		this.inv = inv;
	}
	
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) 
	{
		mod.renderInvBlock(block, metadata, modelID, renderer, this);
	}

	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) 
	{
		return mod.renderWorldBlock(renderer, world, x, y, z, block, modelId);
	}

	public boolean shouldRender3DInInventory() 
	{
		return inv;
	}

	public int getRenderId() 
	{
		return id;
	}
	
	public void drawCrossedSquares(Block par1Block, int par2, double par3, double par5, double par7)
    {
        Tessellator tessellator = Tessellator.instance;
        int i = par2;

        int j = (i & 0xf) << 4;
        int k = i & 0xf0;
        double d = (float)j / 256F;
        double d1 = ((float)j + 15.99F) / 256F;
        double d2 = (float)k / 256F;
        double d3 = ((float)k + 15.99F) / 256F;
        double d4 = (par3 + 0.5D) - 0.45000000000000001D;
        double d5 = par3 + 0.5D + 0.45000000000000001D;
        double d6 = (par7 + 0.5D) - 0.45000000000000001D;
        double d7 = par7 + 0.5D + 0.45000000000000001D;
        tessellator.addVertexWithUV(d4, par5 + 1.0D, d6, d, d2);
        tessellator.addVertexWithUV(d4, par5 + 0.0D, d6, d, d3);
        tessellator.addVertexWithUV(d5, par5 + 0.0D, d7, d1, d3);
        tessellator.addVertexWithUV(d5, par5 + 1.0D, d7, d1, d2);
        tessellator.addVertexWithUV(d5, par5 + 1.0D, d7, d, d2);
        tessellator.addVertexWithUV(d5, par5 + 0.0D, d7, d, d3);
        tessellator.addVertexWithUV(d4, par5 + 0.0D, d6, d1, d3);
        tessellator.addVertexWithUV(d4, par5 + 1.0D, d6, d1, d2);
        tessellator.addVertexWithUV(d4, par5 + 1.0D, d7, d, d2);
        tessellator.addVertexWithUV(d4, par5 + 0.0D, d7, d, d3);
        tessellator.addVertexWithUV(d5, par5 + 0.0D, d6, d1, d3);
        tessellator.addVertexWithUV(d5, par5 + 1.0D, d6, d1, d2);
        tessellator.addVertexWithUV(d5, par5 + 1.0D, d6, d, d2);
        tessellator.addVertexWithUV(d5, par5 + 0.0D, d6, d, d3);
        tessellator.addVertexWithUV(d4, par5 + 0.0D, d7, d1, d3);
        tessellator.addVertexWithUV(d4, par5 + 1.0D, d7, d1, d2);
    }
	
    public void drawWisp(Block par1Block, int par2, double par3, double par5, double par7)
    {
        Tessellator tessellator = Tessellator.instance;
        int i = par2;

        int j = (i & 0xf) << 4;
        int k = i & 0xf0;
        double d = (float)j / 256F;
        double d1 = ((float)j + 15.99F) / 256F;
        double d2 = (float)k / 256F;
        double d3 = ((float)k + 15.99F) / 256F;
        double d4 = (par3 + 0.5D) - 0.45000000000000001D;
        double d5 = par3 + 0.5D + 0.45000000000000001D;
        double d6 = (par7 + 0.5D) - 0.45000000000000001D;
        double d7 = par7 + 0.5D + 0.45000000000000001D;
        tessellator.addVertexWithUV(d4, par5 + 1.0D, d6, d, d2);
        tessellator.addVertexWithUV(d4, par5 + 0.0D, d6, d, d3);
        tessellator.addVertexWithUV(d5, par5 + 0.0D, d7, d1, d3);
        tessellator.addVertexWithUV(d5, par5 + 1.0D, d7, d1, d2);
        tessellator.addVertexWithUV(d5, par5 + 1.0D, d7, d, d2);
        tessellator.addVertexWithUV(d5, par5 + 0.0D, d7, d, d3);
        tessellator.addVertexWithUV(d4, par5 + 0.0D, d6, d1, d3);
        tessellator.addVertexWithUV(d4, par5 + 1.0D, d6, d1, d2);
        tessellator.addVertexWithUV(d4, par5 + 1.0D, d7, d, d2);
        tessellator.addVertexWithUV(d4, par5 + 0.0D, d7, d, d3);
        tessellator.addVertexWithUV(d5, par5 + 0.0D, d6, d1, d3);
        tessellator.addVertexWithUV(d5, par5 + 1.0D, d6, d1, d2);
        tessellator.addVertexWithUV(d5, par5 + 1.0D, d6, d, d2);
        tessellator.addVertexWithUV(d5, par5 + 0.0D, d6, d, d3);
        tessellator.addVertexWithUV(d4, par5 + 0.0D, d7, d1, d3);
        tessellator.addVertexWithUV(d4, par5 + 1.0D, d7, d1, d2);
    }
}