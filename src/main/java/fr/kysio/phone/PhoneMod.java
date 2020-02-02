package fr.kysio.phone;

import fr.kysio.phone.client.KeyInputHandler;
import fr.kysio.phone.common.CommonProxy;
import fr.kysio.phone.events.RegistringHandler;
import fr.kysio.phone.phones.ApplicationManager;
import fr.kysio.phone.phones.PhoneEvents;
import fr.kysio.phone.phones.applications.ExampleApp;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = "phonemod")
public class PhoneMod {


    public static final String MODID = "phonemod";

    public static final BlockPos SPAWN = new BlockPos(0, 120, 0);

    @Mod.Instance(PhoneMod.MODID)
    public static PhoneMod instance;

    @SidedProxy(clientSide = "fr.kysio.phone.common.CommonProxy", serverSide = "fr.kysio.phone.client.ClientProxy")
    public static CommonProxy proxy;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();

        MinecraftForge.EVENT_BUS.register(ItemsManager.class);
        MinecraftForge.EVENT_BUS.register(RegistringHandler.class);

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();

        if (event.getSide().isClient()) {

            //Add your custom app to the defaults app
            ApplicationManager.addApp(new ExampleApp());

            ApplicationManager.loadApplications();
            new KeyInputHandler();
            MinecraftForge.EVENT_BUS.register(new PhoneEvents());
        }
    }


}
