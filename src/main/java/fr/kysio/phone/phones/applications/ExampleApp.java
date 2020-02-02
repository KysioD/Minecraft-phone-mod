package fr.kysio.phone.phones.applications;

import fr.kysio.phone.phones.Application;
import fr.kysio.phone.phones.widget.buttons.ApplicationButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.awt.*;

public class ExampleApp extends Application {

    // Create 2 buttons
    public ApplicationButton button1;
    public ApplicationButton button2;


    // isMenu = Your app is a menu ? ( Like UnlockApp )
    // isDefault = Your app is default installed (true) ? Or will install with store (false ) ?
    public ExampleApp() {
        super("exempleApp", false, true);
    }

    @Override
    public void init() {
        super.init();

        //Define the 2 buttons
        button1 = new ApplicationButton("text", resolution.getScaledWidth()-110, resolution.getScaledHeight()-140, 50, 20, Color.WHITE, Color.gray, Color.white) {
            @Override
            public void onClick() {
                Minecraft.getMinecraft().player.sendMessage(new TextComponentString("You pressed the first button"));
            }
        };
        button2 = new ApplicationButton("btn 2", resolution.getScaledWidth()-150, resolution.getScaledHeight()-100, 70, 30, Color.WHITE, Color.ORANGE, Color.blue) {
            @Override
            public void onClick() {
                Minecraft.getMinecraft().player.sendMessage(new TextComponentString("You pressed button 2"));
            }
        };

        //Add the 2 buttons
        addButton(button1);
        addButton(button2);
    }

    @Override
    public void renderApp(ScaledResolution res, RenderGameOverlayEvent.ElementType elementType) {
        //Draw the default background of the phone
        drawPhoneBackground(res);

        // Redefine the button x and y if player reduce the game window

        button1.setX(resolution.getScaledWidth()-110);
        button1.setY(resolution.getScaledHeight()-140);
        button2.setX(resolution.getScaledWidth()-150);
        button2.setY(resolution.getScaledHeight()-100);

        //Draw the two buttons
        button1.drawButton();
        button2.drawButton();

        super.renderApp(res, elementType);
    }
}
