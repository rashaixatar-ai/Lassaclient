package gg.lassa.client;

import gg.lassa.client.gui.LassaClickGUI;
import gg.lassa.client.modules.ModuleManager;
import gg.lassa.client.render.HUDRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class LassaClient implements ClientModInitializer {
    public static final String MOD_ID = "lassaclient";
    public static final String CLIENT_NAME = "Lassa.gg Client";
    
    public static KeyBinding openGuiKey;
    public static KeyBinding zoomKey;
    public static KeyBinding freelookKey;

    @Override
    public void onInitializeClient() {
        openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.lassa.open_gui",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            "category.lassa.title"
        ));

        zoomKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.lassa.zoom",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_C,
            "category.lassa.title"
        ));

        freelookKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.lassa.freelook",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            "category.lassa.title"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openGuiKey.wasPressed()) {
                if (client.player != null) {
                    client.setScreen(new LassaClickGUI());
                }
            }

            ModuleManager.zoomActive = zoomKey.isPressed();
            ModuleManager.freelookActive = freelookKey.isPressed();
        });

        HudRenderCallback.EVENT.register(new HUDRenderer());
    }
}
