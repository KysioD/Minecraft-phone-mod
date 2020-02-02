package fr.kysio.phone.phones.widget.buttons;

import fr.kysio.phone.PhoneMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class ApplicationButtonImage extends ApplicationButton {

    private ResourceLocation image;

    public ApplicationButtonImage( int x, int y, int width, int height, Color selectedColor, Color foregroundColors, ResourceLocation image) {
        super("", x, y, width, height, selectedColor, Color.WHITE, foregroundColors);
        this.image = image;
    }

    @Override
    public void drawButton(){
        super.drawButton();

        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        GlStateManager.enableAlpha();
        Gui.drawScaledCustomSizeModalRect(x, y, 0, 0, width, height, width, height, width, height);
    }

    @Override
    public void onClick() {

    }
}
