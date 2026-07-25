package gg.lassa.client.render;

import gg.lassa.client.modules.ModuleManager;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class HUDRenderer implements HudRenderCallback {

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.options.hudHidden) return;

        int yOffset = 10;

        drawContext.drawTextWithShadow(client.textRenderer, "[Lassa.gg Client v1.0]", 10, yOffset, 0x00FFCC);
        yOffset += 14;

        if (ModuleManager.reachDisplay) {
            String reachStr = String.format("Reach: %.2f blocks", ModuleManager.lastReachDistance);
            drawContext.drawTextWithShadow(client.textRenderer, reachStr, 10, yOffset, 0xFFFFFF);
            yOffset += 12;
        }

        if (ModuleManager.showTotem) {
            int totems = countTotems(client);
            String totemStr = "Totems: " + totems;
            drawContext.drawTextWithShadow(client.textRenderer, totemStr, 10, yOffset, 0xFFD700);
            yOffset += 12;
        }

        if (ModuleManager.armorHud) {
            renderArmorHUD(drawContext, client);
        }
    }

    private int countTotems(MinecraftClient client) {
        if (client.player == null) return 0;
        PlayerInventory inv = client.player.getInventory();
        int count = 0;
        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getStack(i);
            if (stack.getItem() == Items.TOTEM_OF_UNDYING) {
                count += stack.getCount();
            }
        }
        return count;
    }

    private void renderArmorHUD(DrawContext drawContext, MinecraftClient client) {
        if (client.player == null) return;
        int x = drawContext.getScaledWindowWidth() / 2 + 15;
        int y = drawContext.getScaledWindowHeight() - 55;

        for (int i = 3; i >= 0; i--) {
            ItemStack armorStack = client.player.getInventory().getArmorStack(i);
            if (!armorStack.isEmpty()) {
                drawContext.drawItem(armorStack, x, y);
                drawContext.drawItemInSlot(client.textRenderer, armorStack, x, y);
                x += 18;
            }
        }
    }
}
