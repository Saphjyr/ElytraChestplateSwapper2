package saphjyr.ecs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants.Key;

/**
 * SwapKeyBinding
 */
public class SwapKeyBinding extends KeyMapping {

    private boolean pressedBypass;

    public SwapKeyBinding(String name, int keyCode, KeyMapping.Category category) {
        super(name, keyCode, category);
        pressedBypass = false;
    }

    public Key getKey() {
        return this.key;
    }

    @Override
    public void setDown(boolean pressed) {
        super.setDown(pressed);
        if (pressed) {
            onPressed();
        }
    }

    public void onPressed() {
        InventoryUtils.swapChestplate(Minecraft.getInstance());
    }

    public boolean isPressedBypass() {
        return pressedBypass;
    }

    public void setPressedBypass(boolean pressed) {
        pressedBypass = pressed;
        if (pressed) onPressBypass();
    }

    public void onPressBypass() {

        try {
            Minecraft client = Minecraft.getInstance();
        
            // If the the inventory screen, trigger swap
            if (client.screen instanceof InventoryScreen) {
                InventoryUtils.swapChestplate(client);
            }

            //If in the creative screen, only trigger when in the inventory tab
            if (client.screen instanceof CreativeModeInventoryScreen) {
                CreativeModeInventoryScreen cis = (CreativeModeInventoryScreen)client.screen;
                if(cis.isInventoryOpen()) {
                    InventoryUtils.swapChestplate(client);
                }
            }
            
        } catch (Exception e) {
            System.out.println("ECS creative inventory swapping is not compatible with this version of Minecraft");
        }
        
            
    }
    
}