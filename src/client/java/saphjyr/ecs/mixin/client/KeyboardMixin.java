package saphjyr.ecs.mixin.client;

import saphjyr.ecs.ElytraChestplateSwapperClient;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.input.KeyEvent;

@Mixin(KeyboardHandler.class)
public class KeyboardMixin {
    
    @Inject(method = "keyPress", at = @At(value = "HEAD"))
	private void onKey(long window, int action, KeyEvent keyEvent, CallbackInfo callbackInfo) {
        if (ElytraChestplateSwapperClient.keyBinding.getKey().getValue() == keyEvent.key()) {
            
            // Update the pressedBypass state of the SwapKeyBinding
            boolean pressed = ElytraChestplateSwapperClient.keyBinding.isPressedBypass();
            ElytraChestplateSwapperClient.keyBinding.setPressedBypass(!pressed);
        }
	}
}
