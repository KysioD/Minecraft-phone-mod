package fr.kysio.phone.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class KeyInputHandler {

    public static KeyBinding unlockPhone = new KeyBinding("key.unlockphone.desc",
            Keyboard.KEY_RETURN,
            "kysio.phones.category"
    );
    public static KeyBinding leftPhone = new KeyBinding("key.leftphone.desc",
            Keyboard.KEY_LEFT,
            "kysio.phones.category"
    );
    public static KeyBinding rightPhone = new KeyBinding("key.rightphone.desc",
            Keyboard.KEY_RIGHT,
            "kysio.phones.category"
    );
    public static KeyBinding topPhone = new KeyBinding("key.topphone.desc",
            Keyboard.KEY_UP,
            "kysio.phones.category"
    );
    public static KeyBinding bottomPhone = new KeyBinding("key.bottomphone.desc",
            Keyboard.KEY_DOWN,
            "kysio.phones.category"
    );
    public static KeyBinding backPhone = new KeyBinding("key.backphone.desc",
            Keyboard.KEY_BACK,
            "kysio.phones.category"
    );

    public KeyInputHandler(){
        ClientRegistry.registerKeyBinding(unlockPhone);
        ClientRegistry.registerKeyBinding(leftPhone);
        ClientRegistry.registerKeyBinding(rightPhone);
        ClientRegistry.registerKeyBinding(topPhone);
        ClientRegistry.registerKeyBinding(bottomPhone);
        ClientRegistry.registerKeyBinding(backPhone);
    }

}
