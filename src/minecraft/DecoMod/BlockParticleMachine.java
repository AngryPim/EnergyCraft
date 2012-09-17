package DecoMod; 

import java.util.Random;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModLoader;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockParticleMachine extends BlockContainer 
{                                                                                                                                                                                                                                                                              
	public String getTextureFile()                                                                                                          
    {                      	                                                                                                    
        return "DecoMod/Medieval/Entitys/ParticleMachine Off.png";                                                                                          
    }      

	public static boolean isActive = false;
	private static boolean keepAnimatorInventory = false;
	public static boolean lantenon = false;
	public static String blocktexture = CommonProxy.Entitys + "AnimatorOff.png";
	private Class anEntityClass;
	public Random rand;


	public BlockParticleMachine(int i, int j, Class class1, boolean par2, String Texture) 
	{
		super(i, j, Material.cloth);
		this.isActive = par2;
		anEntityClass = class1;
		blocktexture = Texture;
	}

	public int idDropped(int par1, Random par2Random, int par3)
	{
		return CommonProxy.particleMachine.blockID;
	}
	
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
    }
	
	public int quantityDropped(Random random) 
	{
		return 1;
	}
	
	public boolean isOpaqueCube() 
	{
		return false;
	}
	
	public boolean renderAsNormalBlock() 
	{
		return false;
	}
	
	public int getRenderType() 
	{
		return DecoMod.RenderParticleMachineID;
	}
    
/*    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
		player.openGui(TheBetweenlands.instance, GuiAnimator.GuiID, world, x, y, z);
        return true;
    }*/
    
	public static void updateAnimatorBlockState(boolean par0, World par1World, int par2, int par3, int par4)
	{
		int var5 = par1World.getBlockMetadata(par2, par3, par4);
		TileEntity var6 = par1World.getBlockTileEntity(par2, par3, par4);
		keepAnimatorInventory = true;

		int posX = 1;
		int posY = 1;
		int posZ = 1;
		if (par0)
		{
			par1World.setBlockWithNotify(par2, par3, par4, CommonProxy.particleMachineOn.blockID);
			par1World.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "Betweenlands.Animator", 0.5F, 0.5F);
			lantenon = true;
		}
		else
		{
			par1World.setBlockWithNotify(par2, par3, par4, CommonProxy.particleMachine.blockID);
			lantenon = false;
		}

		keepAnimatorInventory = false;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, var5);
		 if (var6 != null)
	        {
	            var6.validate();
	            par1World.setBlockTileEntity(par2, par3, par4, var6);
	        }
	}

	public void onBlockPlaced(World world, int i, int j, int k, int l)
    {
	EntityPlayer par5EntityPlayer = ModLoader.getMinecraftInstance().thePlayer;
       int p = MathHelper.floor_double((double)((par5EntityPlayer.rotationYaw * 4F) / 360F) + 0.5D) & 3; //this is a smart equation

       byte byte0 = 3;
                       
                       
                                     if (p == 0)
                                     {
                                                     byte0 = 4;
                                     }
                                     if (p == 1)
                                     {
                                                     byte0 = 3;
                                     }
                                     if (p == 2)
                                     {
                                                     byte0 = 2;
                                     }
                                     if (p == 3)
                                     {
                                                     byte0 = 1;
                                     }

                                     world.setBlockMetadataWithNotify(i, j, k, byte0);

     }
	
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
    {
        int var6 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (var6 == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2);
        }

        if (var6 == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 5);
        }

        if (var6 == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3);
        }

        if (var6 == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 4);
        }
    }
	
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
		int var6 = par1World.getBlockMetadata(par2, par3, par4);
        float var7 = (float)par2 + 0.5F;
        float var8 = (float)par3 + 1.0F + par5Random.nextFloat() * 6.0F / 16.0F;
        float var9 = (float)par4 + 0.5F;
        //if (this.isActive)
        //{
        //    float var10 = 0.52F;
        //    float var11 = par5Random.nextFloat() * 0.6F - 0.3F;
        //    par1World.spawnParticle("reddust", var7 + 0.4, var8, var9 + 0.4, 0.0D /*red*/, 1.0D /*green*/, 3.0D /*blue*/);
        //    par1World.spawnParticle("reddust", var7 - 0.4, var8, var9 + 0.4, 0.0D /*red*/, 1.0D /*green*/, 3.0D /*blue*/);
        //    par1World.spawnParticle("reddust", var7 + 0.4, var8, var9 - 0.4, 0.0D /*red*/, 1.0D /*green*/, 3.0D /*blue*/);
        //    par1World.spawnParticle("reddust", var7 - 0.4, var8, var9 - 0.4, 0.0D /*red*/, 1.0D /*green*/, 3.0D /*blue*/);
        //}
        
        //Just testing some things
        
        /*ModLoader.getMinecraftInstance().effectRenderer.addEffect(new EntityWispFX(par1World, var7 + 0.4, var8 + 0.6F, var9 + 0.4, 0.0D, 0.0D, 0.0D, 1F, 50, 0xFF0000FF, false));
    	ModLoader.getMinecraftInstance().effectRenderer.addEffect(new EntityWispFX(par1World, var7 - 0.4, var8 + 0.6F, var9 + 0.4, 0.0D, 0.0D, 0.0D, 1F, 50, 0xFF0000FF, false));
    	ModLoader.getMinecraftInstance().effectRenderer.addEffect(new EntityWispFX(par1World, var7 + 0.4, var8 + 0.6F, var9 - 0.4, 0.0D, 0.0D, 0.0D, 1F, 50, 0xFF0000FF, false));
    	ModLoader.getMinecraftInstance().effectRenderer.addEffect(new EntityWispFX(par1World, var7 - 0.4, var8 + 0.6F, var9 - 0.4, 0.0D, 0.0D, 0.0D, 1F, 50, 0xFF0000FF, false));
    */}

	@Override
	public TileEntity createNewTileEntity(World var1) 
	{
		return new TileEntityParticleMachine();
	}
}