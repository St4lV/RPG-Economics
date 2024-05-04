package fr.st4lV.mcrpgeco.client;

import fr.st4lV.mcrpgeco.screen.BlockbergTerminalScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

public class ClientHooks {
    public static void openExampleBlockScreen(BlockPos pos) {
        Minecraft.getInstance().setScreen(new BlockbergTerminalScreen(pos));
    }
}