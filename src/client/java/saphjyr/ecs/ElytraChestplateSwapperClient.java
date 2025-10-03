package saphjyr.ecs;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.Identifier;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public class ElytraChestplateSwapperClient implements ClientModInitializer {
    
    public static SwapKeyBinding keyBinding;
    
    @Override
    public void onInitializeClient() {

        // Create and log the key binding
        KeyBinding.Category ecsCategory = KeyBinding.Category.create(Identifier.of("ecs", "swap"));
        keyBinding = new SwapKeyBinding("key.ecs.swap", GLFW.GLFW_KEY_GRAVE_ACCENT, ecsCategory);
        KeyBindingHelper.registerKeyBinding(keyBinding);
    }
}