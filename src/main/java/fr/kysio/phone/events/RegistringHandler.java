package fr.kysio.phone.events;

import fr.kysio.phone.ItemsManager;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RegistringHandler {

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ItemsManager.PHONE);
        System.out.println("REGISTRING HANDLER");

    }

}
