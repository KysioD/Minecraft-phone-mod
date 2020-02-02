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
import java.lang.annotation.ElementType;

public class MainMenuApp extends Application {

    public ApplicationButton button1;
    public ApplicationButton button2;
    public ApplicationButton button3;

    public MainMenuApp() {
        super("menu", true, true);
    }

    /**
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if (KeyInputHandler.unlockPhone.isPressed() && isEnabled()) {
                Minecraft.getMinecraft().player.sendMessage(new TextComponentString("unlock pressed"));
                for(ApplicationButton button : getApplicationButtons()){
                    if(button.isEnabled() && button.isSelected()){
                        button.onClick();
                    }
                }
        }
    }**/

    @Override
    public void update() {
/**
        if (KeyInputHandler.unlockPhone.isPressed() && isEnabled()) {
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString("unlock pressed"));
            for(ApplicationButton button : getApplicationButtons()){
                if(button.isEnabled() && button.isSelected()){
                    button.onClick();
                }
            }
        }**/

        if (KeyInputHandler.backPhone.isPressed() && isEnabled()) {
            ApplicationManager.launchApplication("unlock");
            mc.player.sendMessage(new TextComponentString("BACK BUTTON PRESSED -> BACK TO UNLOCK"));
        }

        super.update();
    }

    @Override
    public void onClickUnlock(){
        super.onClickUnlock();
    }

    @Override
    public void init() {
        button1 = new ApplicationButton("button1", resolution.getScaledWidth() - 75, resolution.getScaledHeight() - 100, 50, 20, Color.white, Color.darkGray, Color.white) {
            @Override
            public void onClick() {
                mc.player.sendMessage(new TextComponentString("PLAYER SELECT BUTTON1"));
            }
        };

        button2 = new ApplicationButton("button2", resolution.getScaledWidth() - 175, resolution.getScaledHeight() - 100, 50, 20, Color.white, Color.darkGray, Color.white) {
            @Override
            public void onClick() {
                mc.player.sendMessage(new TextComponentString("PLAYER SELECT BUTTON2"));
            }
        };

        button3 = new ApplicationButton("button 3", resolution.getScaledWidth() - 125, resolution.getScaledHeight() - 150, 50, 20, Color.white, Color.darkGray, Color.white) {
            @Override
            public void onClick() {
                mc.player.sendMessage(new TextComponentString("PLAYER SELECT BUTTON3"));
            }
        };

        addButton(button1);
        addButton(button2);
        addButton(button3);

        super.init();
    }

    @Override
    public void renderApp(ScaledResolution res, RenderGameOverlayEvent.ElementType elementType) {
        super.renderApp(res, elementType);

        //320
        drawRect(res.getScaledWidth() - 181, res.getScaledHeight() - 310, res.getScaledWidth() - 21, res.getScaledHeight() - 300, new Color(Color.darkGray.getRed(), Color.darkGray.getGreen(), Color.darkGray.getBlue(), 190).getRGB());
        drawString(mc.fontRenderer, "10:04", res.getScaledWidth() - 179, res.getScaledHeight() - 309, -1);
        drawString(mc.fontRenderer, "100%", res.getScaledWidth() - 22 - mc.fontRenderer.getStringWidth("100%"), res.getScaledHeight() - 309, -1);

        button1.elementType = elementType;
        button1.drawButton();
        button2.elementType = elementType;
        button2.drawButton();
        button3.elementType = elementType;
        button3.drawButton();
    }

}
