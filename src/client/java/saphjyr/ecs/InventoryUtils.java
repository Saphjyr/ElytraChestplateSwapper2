package saphjyr.ecs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.inventory.ContainerInput;
import net.fabricmc.api.Environment;

public class InventoryUtils {

    @Environment(net.fabricmc.api.EnvType.CLIENT)
    public static void swapChestplate(Minecraft client){

        // Check if client.player is not null
        if (client.player == null) return;

        // Check if it is a player
        if (!(client.player instanceof LocalPlayer)) return;

        // Check if is it not dead
        if (client.player.isDeadOrDying()) return;

        // Slots to swap to
        int elytraSlot = -1;
        int chestplateSlot = -1;

        int HOTBAR_SIZE = Inventory.getSelectionSize(); // 9
        int MAIN_SIZE = Inventory.INVENTORY_SIZE; // 36
        int TOTAL_SIZE = MAIN_SIZE + 1; // 37

        // List inventory slots, in a special order so the selected slot is the most top-left chestplate : 9 - 35 40 0 - 8
        int[] range = new int[TOTAL_SIZE];

        // Main inventory
        for (int i = 0; i < MAIN_SIZE - HOTBAR_SIZE; i++) {
            range[i] = i + HOTBAR_SIZE;
        }

        // Off hand
        range[MAIN_SIZE - HOTBAR_SIZE] = Inventory.SLOT_OFFHAND; // 40

        // Hotbar
        for (int i = 0; i < HOTBAR_SIZE; i++) {
            range[i + MAIN_SIZE - HOTBAR_SIZE + 1] = i;
        }

        // Find elytraSlot and chestplateSlot
        for(int slot : range) {

            // Get the itemstack in the slot
            ItemStack stack = client.player.getInventory().getItem(slot);

            // Check if the itemstack is empty
            if (stack.isEmpty()) {
                continue;
            } 
            else {

                // Check if the item is an elytra item
                if (isElytra(stack) && elytraSlot < 0) {
                    elytraSlot = slot;
                }

                // Check if the item is a chestplate item
                else if (isChestplate(stack) && chestplateSlot < 0) {
                    chestplateSlot = slot;
                }
            }


        }

        // Get the itemstack in the chestplate slot
        ItemStack wearedItemStack =  client.player.getInventory().getItem(38);
        if (wearedItemStack.isEmpty() && elytraSlot >= 0) {
            sendSwapPackets(elytraSlot, client);
        }
        else if (isElytra(wearedItemStack) && chestplateSlot >=0) {
            sendSwapPackets(chestplateSlot, client);
        }
        else if (isChestplate(wearedItemStack) && elytraSlot >=0) {
            sendSwapPackets(elytraSlot, client);
        }
    }

    private static boolean isElytra(ItemStack stack) {
        return stack.getItem() == Items.ELYTRA;
    }

    private static boolean isChestplate(ItemStack stack) {
        if (!stack.has(net.minecraft.core.component.DataComponents.EQUIPPABLE)) {
            return false;
        }

        if (stack.getItem() == Items.ELYTRA) {
            return false; // Elytra is not a chestplate
        }

        var equippable = stack.get(net.minecraft.core.component.DataComponents.EQUIPPABLE);
        if (equippable == null || equippable.slot() != EquipmentSlot.CHEST) {
            return false; // Check if the item is in the chest slot
        }

        if (!stack.getItem().toString().toLowerCase().contains("chestplate")) {
            return false; // Not a chestplate by name
        }
        return true;
    }

    private static void sendSwapPackets(int slot, Minecraft client) {
        int sentSlot = slot;
        if (sentSlot == 40) sentSlot += 5; // Off Hand offset
        if(sentSlot < Inventory.getSelectionSize()) sentSlot += 36;   // Toolbar offset

        if (client.gameMode != null) {

            // Take the item to swap to
            client.gameMode.handleContainerInput(0, sentSlot, 0, ContainerInput.PICKUP, client.player);

            // Put it in the armor slot
            client.gameMode.handleContainerInput(0, 6, 0, ContainerInput.PICKUP, client.player);

            // Put back what was in the armor slot (can be air)
            client.gameMode.handleContainerInput(0, sentSlot, 0, ContainerInput.PICKUP, client.player);
        }
    }


}
