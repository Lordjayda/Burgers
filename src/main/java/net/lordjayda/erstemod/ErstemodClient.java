package net.lordjayda.erstemod;

import net.fabricmc.api.ClientModInitializer;
import net.lordjayda.erstemod.block.entity.ModBlockEntities;
import net.lordjayda.erstemod.block.entity.renderer.AssemblerBlockEntityRenderer;
import net.lordjayda.erstemod.menu.ModMenuTypes;
import net.lordjayda.erstemod.menu.custom.AssemblerScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

public class ErstemodClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockEntityRenderers.register(ModBlockEntities.cuttingboard_be, AssemblerBlockEntityRenderer::new);
        MenuScreens.register(ModMenuTypes.ASSEMBLER_MENU, AssemblerScreen::new);
    }
}