package gg.lassa.client.gui;

import gg.lassa.client.modules.ModuleManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class LassaClickGUI extends Screen {

    public LassaClickGUI() {
        super(Text.literal("Lassa.gg Client - Configuration"));
    }

    @Override
    protected void init() {
        int startX = this.width / 2 - 100;
        int startY = 60;
        int spacing = 24;

        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Freelook: " + (ModuleManager.freelookEnabled ? "ON" : "OFF")),
            button -> {
                ModuleManager.freelookEnabled = !ModuleManager.freelookEnabled;
                button.setMessage(Text.literal("Freelook: " + (ModuleManager.freelookEnabled ? "ON" : "OFF")));
            }
        ).dimensions(startX, startY, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Armor HUD: " + (ModuleManager.armorHud ? "ON" : "OFF")),
            button -> {
                ModuleManager.armorHud = !ModuleManager.armorHud;
                button.setMessage(Text.literal("Armor HUD: " + (ModuleManager.armorHud ? "ON" : "OFF")));
            }
        ).dimensions(startX, startY + spacing, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Hitboxes: " + (ModuleManager.hitboxShown ? "ON" : "OFF")),
            button -> {
                ModuleManager.hitboxShown = !ModuleManager.hitboxShown;
                if (this.client != null) {
                    this.client.getEntityRenderDispatcher().setRenderHitboxes(ModuleManager.hitboxShown);
                }
                button.setMessage(Text.literal("Hitboxes: " + (ModuleManager.hitboxShown ? "ON" : "OFF")));
            }
        ).dimensions(startX, startY + (spacing * 2), 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Reach Display: " + (ModuleManager.reachDisplay ? "ON" : "OFF")),
            button -> {
                ModuleManager.reachDisplay = !ModuleManager.reachDisplay;
                button.setMessage(Text.literal("Reach Display: " + (ModuleManager.reachDisplay ? "ON" : "OFF")));
            }
        ).dimensions(startX, startY + (spacing * 3), 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Show Totems: " + (ModuleManager.showTotem ? "ON" : "OFF")),
            button -> {
                ModuleManager.showTotem = !ModuleManager.showTotem;
                button.setMessage(Text.literal("Show Totems: " + (ModuleManager.showTotem ? "ON" : "OFF")));
            }
        ).dimensions(startX, startY + (spacing * 4), 200, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        context.drawCenteredTextWithShadow(this.textRenderer, "=== Lassa.gg Client Settings ===", this.width / 2, 25, 0x00FFCC);
        context.drawCenteredTextWithShadow(this.textRenderer, "Press ESC to close menu", this.width / 2, 40, 0xAAAAAA);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
