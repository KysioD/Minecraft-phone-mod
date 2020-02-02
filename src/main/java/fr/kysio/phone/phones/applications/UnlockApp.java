package fr.kysio.phone.phones.applications;

import fr.kysio.phone.client.KeyInputHandler;
import fr.kysio.phone.phones.Application;
import fr.kysio.phone.phones.ApplicationManager;
import fr.kysio.phone.phones.widget.buttons.ApplicationButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.awt.*;

public class UnlockApp extends Application {

    public ApplicationButton button;

    public UnlockApp() {
        super("unlock", true, true);
    }

    @Override
    public void renderApp(ScaledResolution res, RenderGameOverlayEvent.ElementType elementType) {
        super.renderApp(res, elementType);

        mc.fontRenderer.drawString("10:04", res.getScaledWidth() - 115, res.getScaledHeight() - 270, Color.WHITE.getRGB());

        drawString(mc.fontRenderer, "Appuyez sur "+ KeyInputHandler.unlockPhone.getDisplayName(),res.getScaledWidth()-100-(mc.fontRenderer.getStringWidth("Appuyez sur "+ KeyInputHandler.unlockPhone.getDisplayName())/2), res.getScaledHeight()-280+155, -1);
    }

    @Override
    public void onClickUnlock(){
        Minecraft.getMinecraft().player.sendMessage(new TextComponentString("UNLOCK PHONE"));
            ApplicationManager.launchApplication("exempleApp");
            super.onClickUnlock();
    }

}
