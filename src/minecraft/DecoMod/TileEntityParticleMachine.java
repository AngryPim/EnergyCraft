package DecoMod;
import net.minecraft.src.Block;
import net.minecraft.src.EntityList;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;

public class TileEntityParticleMachine extends TileEntity implements IInventory, ISidedInventory
{
    private String mobID = "";
    private String prevMobID = "";
    public double yaw;
    public double yaw2 = 0.0D;
    private float rotation;
    
	public TileEntityParticleMachine()
	{
		
	}
    private ItemStack[] animatorItemStacks = new ItemStack[3];
    
    public int aquaMiddleGemBurnTime = 0;
    public int currentAquaMiddleGemBurnTime = 0;
    
    public int sulfurBurnTime = 0;
    public int currentSulfurBurnTime = 0;

    public int getSizeInventory()
    {
        return this.animatorItemStacks.length;
    }

    public int getBlockMetadata()
    {
    	try{
        if (this.blockMetadata == -1)
        {
            this.blockMetadata = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
        }
    	}catch(Exception ex){}

        return this.blockMetadata;
    }
    
    public ItemStack getStackInSlot(int par1)
    {
    	try{
    		if(isBurning())
    		{
		    	if(animatorItemStacks[par1].getItem().getItemName().contains("IdolBronze"))
				{
		    		mobID = "bronzeidol";
				}
		    	else if(animatorItemStacks[par1].getItem().getItemName().contains("IdolSilver"))
		    	{
		    		mobID = "silveridol";
		    	}
		    	else if(animatorItemStacks[par1].getItem().getItemName().contains("IdolGold"))
		    	{
		    		mobID = "goldidol";
		    	}
		    	else
		    	{
		    		mobID = "";
		    	}
    		}
    	}
    	catch(Exception ex){
    	}
        return this.animatorItemStacks[par1];
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.animatorItemStacks[par1] != null)
        {
            ItemStack var3;

            if (this.animatorItemStacks[par1].stackSize <= par2)
            {
                var3 = this.animatorItemStacks[par1];
                this.animatorItemStacks[par1] = null;
                return var3;
            }
            else
            {
                var3 = this.animatorItemStacks[par1].splitStack(par2);

                if (this.animatorItemStacks[par1].stackSize == 0)
                {
                    this.animatorItemStacks[par1] = null;
                }

                return var3;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.animatorItemStacks[par1] != null)
        {
            ItemStack var2 = this.animatorItemStacks[par1];
            this.animatorItemStacks[par1] = null;
            return var2;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.animatorItemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "Animator";
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
        this.animatorItemStacks = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            byte var5 = var4.getByte("Slot");

            if (var5 >= 0 && var5 < this.animatorItemStacks.length)
            {
                this.animatorItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        this.aquaMiddleGemBurnTime = par1NBTTagCompound.getShort("AquaBurnTime");
        this.currentAquaMiddleGemBurnTime = par1NBTTagCompound.getShort("currentAquaBurnTime");
        
        this.sulfurBurnTime = par1NBTTagCompound.getShort("SulfurBurnTime");
        this.currentSulfurBurnTime = par1NBTTagCompound.getShort("currentSulfurBurnTime");
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("AquaBurnTime", (short)this.aquaMiddleGemBurnTime);
        par1NBTTagCompound.setShort("SulfurBurnTime", (short)this.sulfurBurnTime);
        par1NBTTagCompound.setShort("currentAquaBurnTime", (short)this.currentAquaMiddleGemBurnTime);
        par1NBTTagCompound.setShort("currentSulfurBurnTime", (short)this.currentSulfurBurnTime);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.animatorItemStacks.length; ++var3)
        {
            if (this.animatorItemStacks[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.animatorItemStacks[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        par1NBTTagCompound.setTag("Items", var2);
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
     * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
     */
    public int getAquaMiddleGemBurnTimeRemainingScaled(int par1)
    {
        if (this.currentAquaMiddleGemBurnTime == 0)
        {
            this.currentAquaMiddleGemBurnTime = 200;
        }
        return this.aquaMiddleGemBurnTime * par1 / this.currentAquaMiddleGemBurnTime;
    }
    
    public int getSulfurBurnTimeRemainingScaled(int par1)
    {
        if (this.currentSulfurBurnTime == 0)
        {
            this.currentSulfurBurnTime = 200;
        }
        return this.sulfurBurnTime * par1 / this.currentSulfurBurnTime;
    }

    public boolean isBurning()
    {
        return this.aquaMiddleGemBurnTime > 0 && this.sulfurBurnTime > 0 && this.animatorItemStacks[2] != null;
    }
    
   

    /**
     * Returns true if the animator can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    /*private boolean canSmelt(int par1)
    {
    	try
    	{
	        if (par1 == 0 && this.animatorItemStacks[0].itemID == TheBetweenlands.LifeGem.shiftedIndex)
	        {
	            return true;
	        }
	        if (par1 == 1 && this.animatorItemStacks[1].itemID == TheBetweenlands.Sulfur.shiftedIndex)
	        {
	            return true;
	        }
    	}
    	catch(Exception ex)
    	{
    		
    	}
		return false;
    } */

   /* public static int getItemBurnTime(ItemStack par1ItemStack)
    {
        if (par1ItemStack == null)
        {
            return 0;
        }
        else
        {
            int var1 = par1ItemStack.getItem().shiftedIndex;
            if (par1ItemStack.getItem() instanceof ItemBlock && Block.blocksList[var1].blockMaterial == Material.wood) return 300;
            if (var1 == TheBetweenlands.LifeGem.shiftedIndex) return 5760;
            if (var1 == TheBetweenlands.Sulfur.shiftedIndex) return 90;
            //TODO no plan how forge works
            int ret = ForgeHooks.getBurnTime(par1ItemStack);
            if (ret > 0) 
            {
                return ret;
            }
            return FMLCommonHandler.instance().fuelLookup(var1, par1ItemStack.getItemDamageForDisplay());
            return 0;
        }
    }*/

    /**
     * Return true if item is a fuel source (getItemBurnTime() > 0).
     */
    /*public static boolean isItemFuel(ItemStack par0ItemStack)
    {
        return getItemBurnTime(par0ItemStack) > 0;
    }*/

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
   /* public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}

    public String getMobID()
    {
        return mobID;
    }*/
    
   /* public void updateEntity()
    {
        super.updateEntity();
        
        if(this.animatorItemStacks[2] == null)
        {
        	aquaMiddleGemBurnTime = 0;
        	sulfurBurnTime = 0;
        	return;
        }
        
        boolean var1 = this.aquaMiddleGemBurnTime > 0;
        boolean var2 = false;

        if (this.aquaMiddleGemBurnTime > 0)
        {
        	if(isBurning())
            {
        		--this.aquaMiddleGemBurnTime;
            }
        }

        if (!this.worldObj.isRemote)
        {
            if (this.aquaMiddleGemBurnTime == 0 && this.canSmelt(0))
            {
            	if(!isBurning())
                {
            		--this.animatorItemStacks[0].stackSize;
                }
                this.currentAquaMiddleGemBurnTime = this.aquaMiddleGemBurnTime = getItemBurnTime(this.animatorItemStacks[0]);
                if (this.aquaMiddleGemBurnTime > 0)
                {
                    var2 = true;

                    if (this.animatorItemStacks[0] != null)
                    {
                    	if(isBurning())
                        {
                    		--this.animatorItemStacks[0].stackSize;
                        }
                        if (this.animatorItemStacks[0].stackSize == 0)
                        {
                            this.animatorItemStacks[0] = null;
                        }
                    }
                }
            }
            if (var1 != this.aquaMiddleGemBurnTime > 0)
            {
                var2 = true;
            }
        }

        if (var2)
        {
            this.onInventoryChanged();
        }
        
        
        boolean var11 = this.sulfurBurnTime > 0;
        boolean var21 = false;

        if (this.sulfurBurnTime > 0)
        {
        	if(isBurning())
            {
        		--this.sulfurBurnTime;
            }
        }

        if (!this.worldObj.isRemote)
        {
            if (this.sulfurBurnTime == 0 && this.canSmelt(1))
            {
                this.currentSulfurBurnTime = this.sulfurBurnTime = getItemBurnTime(this.animatorItemStacks[1]);

                if (this.sulfurBurnTime > 0)
                {
                    var2 = true;

                    if (this.animatorItemStacks[1] != null)
                    {
                    	if(isBurning())
                        {
                    		--this.animatorItemStacks[1].stackSize;
                        }
                        if (this.animatorItemStacks[1].stackSize == 0)
                        {
                            this.animatorItemStacks[1] = null;
                        }
                    }
                }
            }
            if (var11 != this.sulfurBurnTime > 0)
            {
                var21 = true;
            }
        }*/

       /* BlockAnimator.updateAnimatorBlockState(this.isBurning(), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
        
        if (var21)
        {
            this.onInventoryChanged();
        }
        
        if(isBurning() && this.aquaMiddleGemBurnTime <= 1)
        {
        	try
        	{
	        	EntityLiving var9 = (EntityLiving)((EntityLiving)EntityList.createEntityByName(this.mobID, this.worldObj));            
	        	var9.setLocationAndAngles(this.xCoord + 0.5, this.yCoord + 1.5, this.zCoord + 0.5, this.worldObj.rand.nextFloat() * 360.0F, 0.0F);
	            this.worldObj.spawnEntityInWorld(var9);
	            --this.animatorItemStacks[2].stackSize;
        	}
        	catch(Exception ex)
        	{
        		
        	}
        }
        
        EntityPlayer var60 = this.worldObj.getClosestPlayer((double)((float)this.xCoord + 0.5F), (double)((float)this.yCoord + 0.5F), (double)((float)this.zCoord + 0.5F), 10.0D);

        if (var60 != null)
        {
            double var20 = var60.posX - (double)((float)this.xCoord + 0.5F);
            double var4 = var60.posZ - (double)((float)this.zCoord + 0.5F);
            
        	this.rotation = (float)Math.atan2(var4, var20);
        }
        
        if(!isBurning())
        {
        	if(mobID != ""){prevMobID = mobID;}
        	mobID = "";
        }
        else if(prevMobID != "")
        {
        	mobID = prevMobID;
        }
        
        this.onInventoryChanged();
    }*/
    
    public float getRotation()
    {
    	return rotation;
    }

	@Override
	public int getStartInventorySide(ForgeDirection side) {
		if (side == ForgeDirection.UP) return 1;
        if (side == ForgeDirection.DOWN) return 0;
        return 2;
	}

	@Override
	public int getSizeInventorySide(ForgeDirection side) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub
		
	}
}
