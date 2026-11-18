package net.lordjayda.burbers;

import net.fabricmc.api.ClientModInitializer;
import net.lordjayda.burbers.block.entity.ModBlockEntities;
import net.lordjayda.burbers.block.entity.renderer.AssemblerBlockEntityRenderer;
import net.lordjayda.burbers.menu.ModMenuTypes;
import net.lordjayda.burbers.menu.custom.AssemblerScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

public class BurbersClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Burbers.LOGGER.info("Welp chicken burber could be ready soon");

        BlockEntityRenderers.register(ModBlockEntities.cuttingboard_be, AssemblerBlockEntityRenderer::new);
        MenuScreens.register(ModMenuTypes.ASSEMBLER_MENU, AssemblerScreen::new);
    }
}