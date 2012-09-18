package DecoMod;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "Forge Test", name = "ProPercivalalb Test Mod", version = "1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, versionBounds = "[1.0.0]")

public class BaseDecoMod
{
	@SidedProxy(clientSide = "DecoMod.ClientProxy", serverSide = "DecoMod.CommonProxy")
	public static CommonProxy proxy;
	
	public class MyForgeMod
	{
		
	}
	
	@Init
	public void LoadFrom(FMLInitializationEvent event)
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityParticleMachine.class, new TileEntityParticleMachineRenderer());
		proxy.registerRenderInformation();
		ClientProxy.registerRenderInformation();
	}
	
	@PreInit
	public void PreLoad(FMLPreInitializationEvent event)
	{
		
	}	
}

