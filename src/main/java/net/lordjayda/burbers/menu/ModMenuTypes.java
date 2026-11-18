package net.lordjayda.burbers.menu;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.lordjayda.burbers.Burbers;
import net.lordjayda.burbers.menu.custom.AssemblerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.MenuType;

public class ModMenuTypes {
    public static final MenuType<AssemblerMenu> ASSEMBLER_MENU =
            Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(Burbers.MOD_ID, "assembler_menu"),
                    new ExtendedMenuType<>(AssemblerMenu::new, BlockPos.STREAM_CODEC));


    public static void registerModMenuTypes() {
        Burbers.LOGGER.info("Registering ModMenuTypes for " + Burbers.MOD_ID);
    }
}