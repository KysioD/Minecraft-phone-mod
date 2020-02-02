package fr.kysio.phone.phones.widget.buttons;

import fr.kysio.phone.client.KeyInputHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

public abstract class ApplicationButton {

    public String text;
    public int x;
    public int y;
    public int width;
    public int height;
    private Color selectedColor;
    private Color backgroundColor;
    private Color foregroundColor;

    public RenderGameOverlayEvent.ElementType elementType;

    private boolean enabled;
    private boolean selected;

    public ApplicationButton(String text, int x, int y, int width, int height, Color selectedColor, Color backgroundColor, Color foregroundColors){
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.selectedColor = selectedColor;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColors;
        this.enabled = true;
        this.selected = false;
    }

    public abstract void onClick();

    public void drawButton(){
        Minecraft mc = Minecraft.getMinecraft();

//        if (elementType.equals(RenderGameOverlayEvent.ElementType.ALL)) {
            if(enabled){

                if(selected){
                    Gui.drawRect(x-1, y+1, x+width+1, y-height-1, selectedColor.getRGB());
                }

                //if(!selected) {
                Gui.drawRect(x, y, x + width, y - height, backgroundColor.getRGB());
                mc.fontRenderer.drawString(text, (x + width / 2) - (mc.fontRenderer.getStringWidth(text) / 2), (y - height / 2) - 10, foregroundColor.getRGB());
                /** }else{
                 Gui.drawRect(x, y, x + width, y - height, selectedColor.getRGB());
                 mc.fontRenderer.drawString(text, (x + width / 2) - (mc.fontRenderer.getStringWidth(text) / 2), y - (height / 2) - 5, textColor.getRGB());
                 }**/
            }
  //      }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public RenderGameOverlayEvent.ElementType getElementType() {
        return elementType;
    }

    public void setElementType(RenderGameOverlayEvent.ElementType elementType) {
        this.elementType = elementType;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
