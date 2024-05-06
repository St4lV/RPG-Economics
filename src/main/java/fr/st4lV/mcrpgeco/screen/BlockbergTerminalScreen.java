package fr.st4lV.mcrpgeco.screen;

import fr.st4lV.mcrpgeco.RPGEconomics;
import fr.st4lV.mcrpgeco.block.entity.BlockbergTerminalBlockEntity;
import fr.st4lV.mcrpgeco.core.MarketCalculs;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

public class BlockbergTerminalScreen extends Screen {
    private static final Component TITLE =
            Component.translatable("gui." + RPGEconomics.MODID + ".blockberg_terminal");
    private static final Component ITEM_BUY_BUTTON =
            Component.translatable("gui." + RPGEconomics.MODID + ".blockberg_terminal.button.buy_button");
    private static final Component ITEM_SELL_BUTTON =
            Component.translatable("gui." + RPGEconomics.MODID + ".blockberg_terminal.button.sell_button");

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(RPGEconomics.MODID, "textures/gui/blockberg_terminal_gui.png");

    private final BlockPos position;
    private final int imageWidth, imageHeight;

    private BlockbergTerminalBlockEntity blockEntity;
    private int leftPos, topPos;

    private Button ItemBuyButton;
    private Button ItemSellButton;

    public BlockbergTerminalScreen(BlockPos position) {
        super(TITLE);

        this.position = position;
        this.imageWidth = 176;
        this.imageHeight = 229;
    }

    @Override
    protected void init() {
        super.init();

        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        if(this.minecraft == null) return;
        Level level = this.minecraft.level;
        if(level == null) return;

        BlockEntity be = level.getBlockEntity(this.position);
        if(be instanceof BlockbergTerminalBlockEntity blockEntity) {
            this.blockEntity = blockEntity;
        } else {
            System.err.printf("BlockEntity at %s is not of type BlockbergTerminalBlockEntity!\n", this.position);
            return;
        }

        this.ItemBuyButton = addRenderableWidget(
                Button.builder(ITEM_BUY_BUTTON, this::handleItem1BuyButton)
                        .bounds(this.leftPos + 7, this.topPos + 35, 27, 17)
                        .tooltip(Tooltip.create(ITEM_BUY_BUTTON))
                        .build()
        );

        this.ItemSellButton = addRenderableWidget(
                Button.builder(ITEM_SELL_BUTTON, this::handleItem1SellButton)
                        .bounds(this.leftPos + 33, this.topPos + 35, 27, 17)
                        .tooltip(Tooltip.create(ITEM_SELL_BUTTON))
                        .build()
        );

        this.ItemBuyButton = addRenderableWidget(
                Button.builder(ITEM_BUY_BUTTON, this::handleItem2BuyButton)
                        .bounds(this.leftPos + 61, this.topPos + 35, 27, 17)
                        .tooltip(Tooltip.create(ITEM_BUY_BUTTON))
                        .build()
        );

        this.ItemSellButton = addRenderableWidget(
                Button.builder(ITEM_SELL_BUTTON, this::handleItem2SellButton)
                        .bounds(this.leftPos + 88, this.topPos + 35, 27, 17)
                        .tooltip(Tooltip.create(ITEM_SELL_BUTTON))
                        .build()
        );
        this.ItemBuyButton = addRenderableWidget(
                Button.builder(ITEM_BUY_BUTTON, this::handleItem2BuyButton)
                        .bounds(this.leftPos + 115, this.topPos + 35, 27, 17)
                        .tooltip(Tooltip.create(ITEM_BUY_BUTTON))
                        .build()
        );

        this.ItemSellButton = addRenderableWidget(
                Button.builder(ITEM_SELL_BUTTON, this::handleItem2SellButton)
                        .bounds(this.leftPos + 142, this.topPos + 35, 27, 17)
                        .tooltip(Tooltip.create(ITEM_SELL_BUTTON))
                        .build()
        );

        this.ItemBuyButton = addRenderableWidget(
                Button.builder(ITEM_BUY_BUTTON, this::handleItem1BuyButton)
                        .bounds(this.leftPos + 7, this.topPos + 71, 27, 17)
                        .tooltip(Tooltip.create(ITEM_BUY_BUTTON))
                        .build()
        );

        this.ItemSellButton = addRenderableWidget(
                Button.builder(ITEM_SELL_BUTTON, this::handleItem1SellButton)
                        .bounds(this.leftPos + 33, this.topPos + 71, 27, 17)
                        .tooltip(Tooltip.create(ITEM_SELL_BUTTON))
                        .build()
        );

        this.ItemBuyButton = addRenderableWidget(
                Button.builder(ITEM_BUY_BUTTON, this::handleItem2BuyButton)
                        .bounds(this.leftPos + 61, this.topPos + 71, 27, 17)
                        .tooltip(Tooltip.create(ITEM_BUY_BUTTON))
                        .build()
        );

        this.ItemSellButton = addRenderableWidget(
                Button.builder(ITEM_SELL_BUTTON, this::handleItem2SellButton)
                        .bounds(this.leftPos + 88, this.topPos + 71, 27, 17)
                        .tooltip(Tooltip.create(ITEM_SELL_BUTTON))
                        .build()
        );
        this.ItemBuyButton = addRenderableWidget(
                Button.builder(ITEM_BUY_BUTTON, this::handleItem2BuyButton)
                        .bounds(this.leftPos + 115, this.topPos + 71, 27, 17)
                        .tooltip(Tooltip.create(ITEM_BUY_BUTTON))
                        .build()
        );

        this.ItemSellButton = addRenderableWidget(
                Button.builder(ITEM_SELL_BUTTON, this::handleItem2SellButton)
                        .bounds(this.leftPos + 142, this.topPos + 71, 27, 17)
                        .tooltip(Tooltip.create(ITEM_SELL_BUTTON))
                        .build()
        );

        this.ItemBuyButton = addRenderableWidget(
                Button.builder(ITEM_BUY_BUTTON, this::handleItem1BuyButton)
                        .bounds(this.leftPos + 7, this.topPos + 107, 27, 17)
                        .tooltip(Tooltip.create(ITEM_BUY_BUTTON))
                        .build()
        );

        this.ItemSellButton = addRenderableWidget(
                Button.builder(ITEM_SELL_BUTTON, this::handleItem1SellButton)
                        .bounds(this.leftPos + 33, this.topPos + 107, 27, 17)
                        .tooltip(Tooltip.create(ITEM_SELL_BUTTON))
                        .build()
        );

        this.ItemBuyButton = addRenderableWidget(
                Button.builder(ITEM_BUY_BUTTON, this::handleItem2BuyButton)
                        .bounds(this.leftPos + 61, this.topPos + 107, 27, 17)
                        .tooltip(Tooltip.create(ITEM_BUY_BUTTON))
                        .build()
        );

        this.ItemSellButton = addRenderableWidget(
                Button.builder(ITEM_SELL_BUTTON, this::handleItem2SellButton)
                        .bounds(this.leftPos + 88, this.topPos + 107, 27, 17)
                        .tooltip(Tooltip.create(ITEM_SELL_BUTTON))
                        .build()
        );
        this.ItemBuyButton = addRenderableWidget(
                Button.builder(ITEM_BUY_BUTTON, this::handleItem2BuyButton)
                        .bounds(this.leftPos + 115, this.topPos + 107, 27, 17)
                        .tooltip(Tooltip.create(ITEM_BUY_BUTTON))
                        .build()
        );

        this.ItemSellButton = addRenderableWidget(
                Button.builder(ITEM_SELL_BUTTON, this::handleItem2SellButton)
                        .bounds(this.leftPos + 142, this.topPos + 107, 27, 17)
                        .tooltip(Tooltip.create(ITEM_SELL_BUTTON))
                        .build()
        );
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        renderBackground(graphics);
        graphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        super.render(graphics, mouseX, mouseY, partialTicks);

        graphics.drawString(this.font,
                TITLE,
                this.leftPos + 8,
                this.topPos + 8,
                0x404040,
                false);

    }

    private void handleItem1BuyButton(Button button) {
        MarketCalculs marketCalculs = MarketCalculs.getInstance();
        marketCalculs.updateMarketValues();
        marketCalculs.buyItem();
    }

    private void handleItem1SellButton(Button button) {
        MarketCalculs marketCalculs = MarketCalculs.getInstance();
        marketCalculs.updateMarketValues();
        marketCalculs.sellItem();
    }

    private void handleItem2BuyButton(Button button) {
        // logic here
    }
    private void handleItem2SellButton(Button button) {
        // logic here
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}