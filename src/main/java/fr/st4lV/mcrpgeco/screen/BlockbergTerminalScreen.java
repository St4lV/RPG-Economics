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

    private static BlockbergTerminalScreen instance; // Add this line

    // Private constructor to prevent instantiation from outside
    private BlockbergTerminalScreen() {
        super(Component.translatable("gui." + RPGEconomics.MODID + ".blockberg_terminal"));
    }

    // Static method to get the instance of BlockbergTerminalScreen
    public static BlockbergTerminalScreen getInstance() {
        if (instance == null) {
            instance = new BlockbergTerminalScreen();
        }
        return instance;
    }

    static MarketCalculs marketCalculs = MarketCalculs.getInstance();

    public void updatePriceValues() {
        int gcBuy = marketCalculs.gcBuy;
        int scBuy = marketCalculs.scBuy;
        int bcBuy = marketCalculs.bcBuy;
        int gcSell = marketCalculs.gcSell;
        int scSell = marketCalculs.scSell;
        int bcSell = marketCalculs.bcSell;
        int gcPlayer = marketCalculs.gcPlayer;
        int scPlayer = marketCalculs.scPlayer;
        int bcPlayer = marketCalculs.bcPlayer;


        Component buyComponent = Component.translatable(/*"§d⇈*/ "§f" +
                (gcBuy != 0 ? gcBuy + "§e§l⌾ §f" : "") +
                (gcBuy < 10000 ? (scBuy!= 0  ? scBuy + "§7§l⌾ §f" : "") : "") +
                (gcBuy < 100 ? (bcBuy != 0 ? bcBuy + "§6§l⌾" : "") : ""));

        Component sellComponent = Component.translatable(/*"§b⇊*/"§f" +
                (gcSell != 0 ? gcSell + "§e§l⌾ §f" : "") +
                (gcSell < 10000 ? (scSell!= 0  ? scSell + "§7§l⌾ §f" : "") : "") +
                (gcSell < 100 ? (bcSell != 0 ? bcSell + "§6§l⌾" : "") : ""));

        Component accountDislayComponent = Component.translatable(
                "§7§l Account: §f" + gcPlayer + "§e§l⌾ §f" + scPlayer + "§7§l⌾ §f" +  bcPlayer + "§6§l⌾ §f");

        // Check for null values and assign empty components if null
        BUY_PRICE = buyComponent;
        SELL_PRICE = sellComponent;
        ACCOUNT = accountDislayComponent;
    }


    private static final Component TITLE =
            Component.translatable("gui." + RPGEconomics.MODID + ".blockberg_terminal");
    private static final Component ITEM_BUY_BUTTON =
            Component.translatable("gui." + RPGEconomics.MODID + ".blockberg_terminal.button.buy_button");
    private static final Component ITEM_SELL_BUTTON =
            Component.translatable("gui." + RPGEconomics.MODID + ".blockberg_terminal.button.sell_button");

    private static Component BUY_PRICE;
    private static Component SELL_PRICE;
    private static Component ACCOUNT;

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(RPGEconomics.MODID, "textures/gui/blockberg_terminal_gui.png");

    private BlockPos position;
    private int imageWidth;
    private int imageHeight;

    private BlockbergTerminalBlockEntity blockEntity;
    private int leftPos, topPos;

    private Button ItemBuyButton;
    private Button ItemSellButton;

    public BlockbergTerminalScreen(BlockPos position) {
        super(TITLE);

        this.position = position;
        this.imageWidth = 220;
        this.imageHeight = 229;
    }

    @Override
    protected void init() {
        MarketCalculs marketCalculs = MarketCalculs.getInstance();
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
                        .bounds(this.leftPos + 26, this.topPos + 18, 27, 16)
                        .tooltip(Tooltip.create(ITEM_BUY_BUTTON))
                        .build()
        );

        this.ItemSellButton = addRenderableWidget(
                Button.builder(ITEM_SELL_BUTTON, this::handleItem1SellButton)
                        .bounds(this.leftPos + 114, this.topPos + 18, 27, 16)
                        .tooltip(Tooltip.create(ITEM_SELL_BUTTON))
                        .build()
        );
        this.ItemBuyButton = addRenderableWidget(

                Button.builder(ITEM_BUY_BUTTON, this::handleItem2BuyButton)
                        .bounds(this.leftPos + 26, this.topPos + 36, 27, 16)
                        .tooltip(Tooltip.create(ITEM_BUY_BUTTON))
                        .build()
        );
        this.ItemSellButton = addRenderableWidget(
                Button.builder(ITEM_SELL_BUTTON, this::handleItem2SellButton)
                        .bounds(this.leftPos + 114, this.topPos + 36, 27, 16)
                        .tooltip(Tooltip.create(ITEM_SELL_BUTTON))
                        .build()
        );
        this.ItemBuyButton = addRenderableWidget(

                Button.builder(ITEM_BUY_BUTTON, this::handleItem3BuyButton)
                        .bounds(this.leftPos + 26, this.topPos + 54, 27, 16)
                        .tooltip(Tooltip.create(ITEM_BUY_BUTTON))
                        .build()
        );
        this.ItemSellButton = addRenderableWidget(
                Button.builder(ITEM_SELL_BUTTON, this::handleItem3SellButton)
                        .bounds(this.leftPos + 114, this.topPos + 54, 27, 16)
                        .tooltip(Tooltip.create(ITEM_SELL_BUTTON))
                        .build()
        );
        this.ItemBuyButton = addRenderableWidget(

                Button.builder(ITEM_BUY_BUTTON, this::handleItem4BuyButton)
                        .bounds(this.leftPos + 26, this.topPos + 72, 27, 16)
                        .tooltip(Tooltip.create(ITEM_BUY_BUTTON))
                        .build()
        );
        this.ItemSellButton = addRenderableWidget(
                Button.builder(ITEM_SELL_BUTTON, this::handleItem4SellButton)
                        .bounds(this.leftPos + 114, this.topPos + 72, 27, 16)
                        .tooltip(Tooltip.create(ITEM_SELL_BUTTON))
                        .build()
        );
        this.ItemBuyButton = addRenderableWidget(

                Button.builder(ITEM_BUY_BUTTON, this::handleItem5BuyButton)
                        .bounds(this.leftPos + 26, this.topPos + 90, 27, 16)
                        .tooltip(Tooltip.create(ITEM_BUY_BUTTON))
                        .build()
        );
        this.ItemSellButton = addRenderableWidget(
                Button.builder(ITEM_SELL_BUTTON, this::handleItem5SellButton)
                        .bounds(this.leftPos + 114, this.topPos + 90, 27, 16)
                        .tooltip(Tooltip.create(ITEM_SELL_BUTTON))
                        .build()
        );
        this.ItemBuyButton = addRenderableWidget(

                Button.builder(ITEM_BUY_BUTTON, this::handleItem6BuyButton)
                        .bounds(this.leftPos + 26, this.topPos + 108, 27, 16)
                        .tooltip(Tooltip.create(ITEM_BUY_BUTTON))
                        .build()
        );
        this.ItemSellButton = addRenderableWidget(
                Button.builder(ITEM_SELL_BUTTON, this::handleItem6SellButton)
                        .bounds(this.leftPos + 114, this.topPos + 108, 27, 16)
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
                false
        );

        graphics.drawString(this.font,
                BUY_PRICE,
                this.leftPos + 55, this.topPos + 24, 0xFF0000
        );
        graphics.drawString(this.font,
                SELL_PRICE,
                this.leftPos + 143, this.topPos + 24, 0xFF0000
        );
        graphics.drawString(this.font,
                ACCOUNT,
                this.leftPos + 20, this.topPos + 129, 0xFF0000
        );

    }

    private void handleItem1BuyButton(Button button) {
        MarketCalculs marketCalculs = MarketCalculs.getInstance();
        marketCalculs.updateMarketValues();
        marketCalculs.buyItem();
        updatePriceValues();
    }

    private void handleItem1SellButton(Button button) {
        MarketCalculs marketCalculs = MarketCalculs.getInstance();
        marketCalculs.updateMarketValues();
        marketCalculs.sellItem();
        updatePriceValues();
    }

    private void handleItem2BuyButton(Button button) {
        // logic here
    }
    private void handleItem2SellButton(Button button) {
        // logic here
    }
    private void handleItem3SellButton(Button button) {
        // logic here
    }
    private void handleItem3BuyButton(Button button) {
        // logic here
    }
    private void handleItem4BuyButton(Button button) {
        // logic here
    }
    private void handleItem4SellButton(Button button) {
        // logic here
    }
    private void handleItem5SellButton(Button button) {
        // logic here
    }
    private void handleItem5BuyButton(Button button) {
        // logic here
    }
    private void handleItem6BuyButton(Button button) {
        // logic here
    }
    private void handleItem6SellButton(Button button) {
        // logic here
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}