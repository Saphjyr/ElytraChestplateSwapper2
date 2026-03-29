package saphjyr.ecs;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public class ElytraChestplateSwapperClient implements ClientModInitializer {
    
    public static SwapKeyBinding keyBinding;
    
    @Override
    public void onInitializeClient() {

        // Create and log the key binding
        KeyMapping.Category category = KeyMapping.Category.register(Identifier.fromNamespaceAndPath("ecs", "swap"));
        keyBinding = new SwapKeyBinding("key.ecs.swap", GLFW.GLFW_KEY_GRAVE_ACCENT, category);
        KeyBindingHelper.registerKeyBinding(keyBinding);
    }
}