package fr.st4lV.mcrpgeco.client;

import fr.st4lV.mcrpgeco.screen.BlockbergTerminalScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.core.BlockPos;

public class ClientHooks {
    public static void openBlockbergTerminalScreen(BlockPos pos) {
        Minecraft.getInstance().setScreen(new BlockbergTerminalScreen(pos));
    }
}