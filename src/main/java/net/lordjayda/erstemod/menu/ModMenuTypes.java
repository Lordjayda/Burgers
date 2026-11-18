package net.lordjayda.erstemod.menu;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.lordjayda.erstemod.Erstemod;
import net.lordjayda.erstemod.menu.custom.AssemblerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.MenuType;

public class ModMenuTypes {
    public static final MenuType<AssemblerMenu> ASSEMBLER_MENU =
            Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(Erstemod.MOD_ID, "assembler_menu"),
                    new ExtendedMenuType<>(AssemblerMenu::new, BlockPos.STREAM_CODEC));


    public static void registerModMenuTypes() {
        Erstemod.LOGGER.info("Registering ModMenuTypes for " + Erstemod.MOD_ID);
    }
}