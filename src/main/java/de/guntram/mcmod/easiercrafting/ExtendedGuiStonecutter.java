package de.guntram.mcmod.easiercrafting;

import net.minecraft.client.gui.screen.ingame.StonecutterScreen;
import net.minecraft.container.SlotActionType;
import net.minecraft.container.StonecutterContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class ExtendedGuiStonecutter extends StonecutterScreen implements SlotClickAccepter {

    private RecipeBook recipeBook;

    public ExtendedGuiStonecutter(StonecutterContainer container, PlayerInventory lowerInv, Text title) {
        super(container, lowerInv, title);
    }
    
    @Override
    protected void init() {
        super.init();
        //if (!ConfigurationHandler.getAllowMinecraftRecipeBook()) {
        //    this.buttons.clear();
        //}
        this.recipeBook.afterInitGui();
    }

    public void setRecipeBook(RecipeBook recipeBook) {
        this.recipeBook=recipeBook;
    }
    
    @Override
    protected void drawForeground(final int mouseX, final int mouseY) {
        super.drawForeground(mouseX, mouseY);
        recipeBook.drawRecipeList(font, itemRenderer, containerWidth, containerHeight, mouseX-x, mouseY-y);
    }
    
    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        recipeBook.scrollBy((int) delta);
        return super.mouseScrolled(mouseX, mouseY, delta);
    }    
    
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        recipeBook.mouseClicked((int)mouseX, (int)mouseY, mouseButton, x, y);
        return true;
    }

    @Override
    public boolean keyPressed(int c, int scancode, int modifiers) {
        if (c==GLFW.GLFW_KEY_ESCAPE)
            return super.keyPressed(c, scancode, modifiers);
        else if (recipeBook.keyPressed(c, scancode, modifiers))
            return true;
        else
            return super.keyPressed(c, scancode, modifiers);
    }
    
    @Override
    public boolean charTyped(char codepoint, int modifiers) {
        if (!recipeBook.charTyped(codepoint, modifiers))
            return super.charTyped(codepoint, modifiers);
        return true;
    }

    @Override
    public void slotClick(int slot, int mouseButton, SlotActionType clickType) {
        this.onMouseClick(null, slot, mouseButton, clickType);
    }
}