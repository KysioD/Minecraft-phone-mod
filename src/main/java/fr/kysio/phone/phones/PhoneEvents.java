package fr.kysio.phone.phones;

import fr.kysio.phone.ItemsManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class PhoneEvents {

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {

        if (event.player.inventory.getCurrentItem().getItem() == ItemsManager.PHONE) {
            if (ApplicationManager.isPhoneOn()) return;
            ApplicationManager.setPhoneOn(true);
            //System.out.println("PHONE IN HAND");
        } else {
            if (!ApplicationManager.isPhoneOn()) return;
            ApplicationManager.setPhoneOn(false);
        }
    }

}
