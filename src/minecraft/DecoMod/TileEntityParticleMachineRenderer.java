package DecoMod;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntitySpecialRenderer;

import org.lwjgl.opengl.GL11;

public class TileEntityParticleMachineRenderer extends TileEntitySpecialRenderer
{
	private ModelParticleMachine model;
    private float rotation;
	
	public TileEntityParticleMachineRenderer() 
	{
		model = new ModelParticleMachine();  
	}
	
	private Map entityHashMap = new HashMap();
	public boolean BlockActive = true;
	private String In = "";
	private float floater = 0;
	private boolean back = false;
	
	
	
	public void renderAModelAt(TileEntityParticleMachine tile, double d, double d1, double d2, float f) 
	{
		int i = 0;
		int j = 0;
		
		if (tile.worldObj == null)
        {
                i = 0;
        }else
    	i = tile.getBlockMetadata();
		if (i == 0)
		{
			j = 0;
		}
		if (i == 1)
		{
			j = 90;
		}

		if (i == 2)
		{
			j = 180;
		}	
		if (i == 3)
		{
			j = 270;
		}
		
		if(In == "")
		{
			bindTextureByName("/betweenlands/textures/entities/AnimatorOff.png"); //texture
		}
		else if(In != "")
		{
			bindTextureByName("/betweenlands/textures/entities/AnimatorOn.png");
		}
		GL11.glPushMatrix(); //start
		GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F); //size
		GL11.glRotatef(j, 0.0F, 1.0F, 0.0F); //rotate based on metadata
		GL11.glScalef(1.0F, -1F, -1F); //if you read this comment out this line and you can see what happens
		model.renderModel(0.0625F); //renders and yes 0.0625 is a random number
		GL11.glPopMatrix(); //end
	}

	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) 
	{
		renderAModelAt((TileEntityParticleMachine) tileentity, d, d1, d2, f); //where to render
	}
}