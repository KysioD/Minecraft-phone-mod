package fr.kysio.phone.phones;

import fr.kysio.phone.PhoneMod;
import fr.kysio.phone.client.KeyInputHandler;
import fr.kysio.phone.phones.widget.buttons.ApplicationButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;

public class Application extends Gui {

    private String name;
    private boolean isMenu;
    private boolean isDefault;
    private boolean enabled = false;

    private boolean isInit;

    public Minecraft mc;
    public ScaledResolution resolution;

    public ApplicationButton currentButton;

    private ArrayList<ApplicationButton> applicationButtons = new ArrayList<>();

    public Application(String name, boolean isMenu, boolean isDefault) {
        this.name = name;
        this.isMenu = isMenu;
        this.isDefault = isDefault;
        this.mc = Minecraft.getMinecraft();
    }

    public void update(){
        /**if (KeyInputHandler.unlockPhone.isPressed() && isEnabled()) {
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString("unlock pressed"));
            for(ApplicationButton button : getApplicationButtons()){
                if(button.isEnabled() && button.isSelected()){
                    button.onClick();
                }
            }
        }**/
    }

    @SubscribeEvent
    public void tick(TickEvent.PlayerTickEvent event){
    }

    @SubscribeEvent
    public void renderGameOverlay(RenderGameOverlayEvent.Post event) {
        resolution = event.getResolution();
        if(!isInit){
            init();
            isInit = true;
        }
        if (enabled) {
            drawPhoneBackground(resolution);
            renderApp(resolution, event.getType());
            buttonVerification();
        }

        update();
    }

    public void buttonVerification(){
        ClientRegistry.registerKeyBinding(KeyInputHandler.unlockPhone);
        if(currentButton == null){
            currentButton = getDefaultButton();
            if(currentButton != null){
                currentButton.setSelected(true);
            }
        }else{
            if(KeyInputHandler.rightPhone.isPressed() && isEnabled()){
                ApplicationButton button = getNextRight(currentButton.getX(), currentButton.getY());
                if(button != null){
                    currentButton.setSelected(false);
                    currentButton = button;
                    currentButton.setSelected(true);
                    System.out.println("BUTTON NEXTED TO THE RIGHT");
                }else{
                    System.out.println("BUTTON NULL");
                }
            }else if(KeyInputHandler.leftPhone.isPressed() && isEnabled()){
                ApplicationButton button = getNextLeft(currentButton.getX(), currentButton.getY());
                if(button != null){
                    currentButton.setSelected(false);
                    currentButton = button;
                    currentButton.setSelected(true);
                }
            }else if(KeyInputHandler.topPhone.isPressed() && isEnabled()){
                ApplicationButton button = getNextup(currentButton.getX(), currentButton.getY());
                if(button != null){
                    currentButton.setSelected(false);
                    currentButton = button;
                    currentButton.setSelected(true);
                }
            }else if(KeyInputHandler.bottomPhone.isPressed() && isEnabled()){
                ApplicationButton button = getnextDown(currentButton.getX(), currentButton.getY());
                if(button != null){
                    currentButton.setSelected(false);
                    currentButton = button;
                    currentButton.setSelected(true);
                }
            }
        }

        if(KeyInputHandler.unlockPhone.isPressed() && isEnabled()){
            System.out.println("UNLOCK PRESSED");
            onClickUnlock();
        }
    }

    public void onClickUnlock(){
        System.out.println("CLICK UNLOCK");
        for(ApplicationButton applicationButton : applicationButtons){
            if(applicationButton.isEnabled() && applicationButton.isSelected()) {
                applicationButton.onClick();
            }
        }
    }

    // GET LE MAX RIGHT PAS LE NEXT A PATCH
    private ApplicationButton getNextRight(int width, int height){
        int wdth = 0;

        for(ApplicationButton applicationButton : applicationButtons){
            System.out.println("Application button : "+applicationButton.getX()+" "+applicationButton.getY());
            System.out.println(applicationButton.getX()+" > "+wdth+" && "+applicationButton.getY()+" = "+height);
            System.out.println(applicationButton.getX() > wdth);
            System.out.println(applicationButton.getY() == height);
            if(applicationButton.getX() > wdth && applicationButton.getY() == height){
                wdth = applicationButton.getX();
                System.out.println("true");
            }else{
                System.out.println("false");
            }
        }

        if(wdth == width){
            System.out.println("SAME WIDTH");
            return getFirstButton(wdth, height);
        }else{
            return getButton(wdth, height);
        }
    }

    private ApplicationButton getMaxUp(){
        int y = 0;

        for(ApplicationButton button : applicationButtons){
            if(button.getY() < y || y == 0){
                y = button.getY();
            }
        }

        int x = 0;

        for(ApplicationButton button : applicationButtons){
            if(button.getY() == y && button.getX() < x || x == 0){
                x = button.getX();
            }
        }

        return getButton(x, y);
    }

    //ATTENTION ! NE FONCTIONNE PAS SI BUTTON UP EST PLUS A DROITE
    private ApplicationButton getNextup(int x, int y){
        int nextX = x;
        int nextY = y;
        boolean finished = false;

        for(ApplicationButton button : applicationButtons){
            if(button.getX() < nextX){
                nextX = button.getX();
            }
            if(button.getY() > nextY && !finished){
                finished = true;
                nextY = button.getY();
            }
            if(button.getY() < nextY && button.getY() > nextY && finished){
                nextY = button.getY();
            }
        }

        if(nextY == y){
            return getMaxUp();
        }else {
            return getButton(x, y);
        }
    }

    private ApplicationButton getnextDown(int x, int y){
        int nextY = y;
        int nextX = x;
        boolean finished = false;

        for(ApplicationButton button : applicationButtons){
            if(button.getX() < nextX){
                nextX = button.getX();
            }

            System.out.println("CHECK");
            System.out.println(button.getY());
            System.out.println(nextY);

            if(button.getY() < nextY && !finished){
                finished = true;
                nextY = button.getY();
            }
            if(button.getY() > nextY && button.getY() < nextY && finished){
                nextY = button.getY();
            }
        }

        System.out.println("DEBUG");
        System.out.println(y);
        System.out.println(nextY);

        if(y == nextY){
            return getMaxDown();
        }else {
            return getButton(x, y);
        }
    }

    private ApplicationButton getMaxDown(){
        int y = 0;

        for(ApplicationButton button : applicationButtons){
            if(button.getY() > y || y == 0){
                y = button.getY();
            }
        }

        int x = 0;

        for(ApplicationButton button : applicationButtons){
            if(button.getY() == y && button.getX() < x || x == 0){
                x = button.getX();
            }
        }

        return getButton(x, y);
    }

    private ApplicationButton getNextLeft(int x, int y){
        int xResult = x;
        boolean finished = false;

        for(ApplicationButton applicationButton : applicationButtons){
            if(applicationButton.getX() < x && applicationButton.getY() == y && !finished){
                xResult = applicationButton.getX();
                finished = true;
            }
            if(finished){
                if(applicationButton.getX() > xResult && applicationButton.getX() < x && applicationButton.getY() == y){
                    xResult = applicationButton.getX();
                }
            }
        }

        if(xResult == x){
            System.out.println("get last button");
            return getLastButton(x, y);
        }else{
            return getButton(xResult, y);
        }
    }

    private ApplicationButton getFirstButton(int width, int height){
        int wdth = width;
        for(ApplicationButton button : applicationButtons){
            if(button.getX() < wdth && height == button.getY()){
                wdth = button.getX();
            }
        }

        return getButton(wdth, height);
    }

    //FONCTIONNE PAS

    private ApplicationButton getLastButton(int x, int y){
        int lastX = x;
        for(ApplicationButton button : applicationButtons){
            if(button.getX() > lastX && button.getY() == y){
                lastX = button.getX();
            }
        }

        return getButton(lastX, y);
    }

    private ApplicationButton getDefaultButton(){
        int width = 0;
        int height = 0;
        for(ApplicationButton button : applicationButtons){
            if(button.getX() < width || width == 0 && button.getY() < height || height == 0){
                width = button.getX();
                height = button.getY();
            }
        }

        return getButton(width, height);
    }

    private ApplicationButton getButton(int width, int height){
        ApplicationButton btn = null;

        for(ApplicationButton button : applicationButtons){
            if(button.getX() == width && button.getY() == height){
                btn = button;
            }
        }

        return btn;
    }

    public void drawPhoneBackground(ScaledResolution res) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(PhoneMod.MODID, "textures/phone/telephone.png"));
        GlStateManager.enableAlpha();
        Gui.drawScaledCustomSizeModalRect(res.getScaledWidth() - 200, res.getScaledHeight() - 350, 0, 0, 200, 350, 200, 350, 200, 350);

    }

    public void init() {

    }

    public void renderApp(ScaledResolution res, RenderGameOverlayEvent.ElementType elementType) {
        this.resolution = res;
    }

    public void addButton(ApplicationButton applicationButton) {
        applicationButtons.add(applicationButton);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMenu() {
        return isMenu;
    }

    public void setMenu(boolean menu) {
        isMenu = menu;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isInit() {
        return isInit;
    }

    public void setInit(boolean init) {
        isInit = init;
    }

    public Minecraft getMc() {
        return mc;
    }

    public void setMc(Minecraft mc) {
        this.mc = mc;
    }

    public ScaledResolution getResolution() {
        return resolution;
    }

    public void setResolution(ScaledResolution resolution) {
        this.resolution = resolution;
    }

    public ApplicationButton getCurrentButton() {
        return currentButton;
    }

    public void setCurrentButton(ApplicationButton currentButton) {
        this.currentButton = currentButton;
    }

    public ArrayList<ApplicationButton> getApplicationButtons() {
        return applicationButtons;
    }

    public void setApplicationButtons(ArrayList<ApplicationButton> applicationButtons) {
        this.applicationButtons = applicationButtons;
    }
}
