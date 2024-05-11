package fr.st4lV.mcrpgeco.screen;

import fr.st4lV.mcrpgeco.RPGEconomics;
import fr.st4lV.mcrpgeco.block.entity.BlockbergTerminalBlockEntity;
import fr.st4lV.mcrpgeco.core.MarketItem;
import fr.st4lV.mcrpgeco.core.MarketCalculs;
import fr.st4lV.mcrpgeco.screen.BlockbergTerminalScreenComponents;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

import static fr.st4lV.mcrpgeco.screen.BlockbergTerminalScreenComponents.marketCalculs;


public class BlockbergTerminalScreen extends Screen {

    public int ActPage = 1;

    private ItemStack Item1Stack;

    private static BlockbergTerminalScreen instance;

    private BlockbergTerminalScreen() {
        super(Component.translatable("gui." + RPGEconomics.MODID + ".blockberg_terminal"));
    }

    public static BlockbergTerminalScreen getInstance() {
        if (instance == null) {
            instance = new BlockbergTerminalScreen();
        }
        return instance;
    }

    public void updatePriceValues() {

        BlockbergTerminalScreenComponents.getInstance().updateComponent();
        GetActPage();
        System.out.println("GetActPage:"+GetActPage());

        int gcPlayer = marketCalculs.gcPlayer;
        int scPlayer = marketCalculs.scPlayer;
        int bcPlayer = marketCalculs.bcPlayer;

        Component accountDislayComponent = Component.literal("")
                .append(Component.translatable("gui.mcrpgeco.blockberg_terminal.display.account"))
                .append(Component.literal(": §f"+ (gcPlayer != 0 ? gcPlayer + "§e§l⌾ §f" : "") +
                        (scPlayer != 0 ? scPlayer + "§7§l⌾ §f" : "") +
                        (bcPlayer != 0 ? bcPlayer + "§6§l⌾ §f" : "")));

        Component nextPageButtonComp = Component.literal(">");
        Component previousPageButtonComp = Component.literal("<");
        Component ActPageComp = Component.literal("[ "+BlockbergTerminalScreen.getInstance().GetActPage()+" / "+ MarketItem.getInstance().getMaxPage()+" ]");

        Component nextPageTooltipComp = Component.translatable("gui."+RPGEconomics.MODID+".blockberg_terminal.next_page_button");
        Component previousPageTooltipComp = Component.translatable("gui."+RPGEconomics.MODID+".blockberg_terminal.previous_page_button");



        ResourceLocation item1Texture = determineItemTexture1();

        ACCOUNT = accountDislayComponent;
        NEXT_PAGE_BUTTON = nextPageButtonComp;
        PREVIOUS_PAGE_BUTTON = previousPageButtonComp;
        NEXT_PAGE_TOOLTIP = nextPageTooltipComp;
        PREVIOUS_PAGE_TOOLTIP = previousPageTooltipComp;
        ACT_PAGE = ActPageComp;

        QSTOCK1 = BlockbergTerminalScreenComponents.getInstance().getQstockComponent1();
        BUY_PRICE1 = BlockbergTerminalScreenComponents.getInstance().getBuyComponent1();
        SELL_PRICE1 = BlockbergTerminalScreenComponents.getInstance().getSellComponent1();
        BUY_TOOLTIP1 = BlockbergTerminalScreenComponents.getInstance().getBuyTooltipComp1();
        SELL_TOOLTIP1 = BlockbergTerminalScreenComponents.getInstance().getSellTooltipComp1();



    }
    public int GetActPage() {
        System.out.println("ActPage5:"+ActPage);
        return ActPage;
    };

    private static final Component TITLE =
            Component.translatable("gui." + RPGEconomics.MODID + ".blockberg_terminal");
    private static final Component ITEM_BUY_BUTTON =
            Component.translatable("gui." + RPGEconomics.MODID + ".blockberg_terminal.button.buy_button");
    private static final Component ITEM_SELL_BUTTON =
            Component.translatable("gui." + RPGEconomics.MODID + ".blockberg_terminal.button.sell_button");

    private static Component BUY_PRICE1;
    private static Component SELL_PRICE1;
    private static Component ACCOUNT;
    private static Component QSTOCK1;
    private static Component BUY_TOOLTIP1;
    private static Component SELL_TOOLTIP1;
    private static Component NEXT_PAGE_BUTTON;
    private static Component PREVIOUS_PAGE_BUTTON;
    private static Component NEXT_PAGE_TOOLTIP;
    private static Component PREVIOUS_PAGE_TOOLTIP;
    public static Component ACT_PAGE;

    private static final ResourceLocation GUI_TEXTURE =
            new ResourceLocation(RPGEconomics.MODID, "textures/gui/blockberg_terminal_gui.png");

    private ResourceLocation determineItemTexture1() {
        if (ActPage == 1) {
            return new ResourceLocation(MarketItem.getInstance().getItemMod(), "textures/" + MarketItem.getInstance().getItemType() + "/" + MarketItem.getInstance().getItem() + ".png");
        } else if (ActPage == 2) {
            return new ResourceLocation(RPGEconomics.MODID, "textures/item/bronze_coin.png");
        } else {
            return null;
        }
    }

    private static final ResourceLocation ITEM1_MODEL =
            new ResourceLocation(MarketItem.getInstance().getItemMod(), "models/"+MarketItem.getInstance().getItemType()+"/"+MarketItem.getInstance().getItem());

    private BlockPos position;
    private int imageWidth;
    private int imageHeight;

    private BlockbergTerminalBlockEntity blockEntity;
    private int leftPos, topPos;

    private Button ItemBuyButton;
    private Button ItemSellButton;
    private Button NextPageButton;
    private Button PreviousPageButton;

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
        this.NextPageButton = addRenderableWidget(

                Button.builder(NEXT_PAGE_BUTTON, this::handleNextpage)
                        .bounds(this.leftPos + 192, this.topPos + 126, 16, 16)
                        .tooltip(Tooltip.create(NEXT_PAGE_TOOLTIP))
                        .build()
        );
        this.PreviousPageButton = addRenderableWidget(

                Button.builder(PREVIOUS_PAGE_BUTTON, this::handlePreviouspage)
                        .bounds(this.leftPos + 4, this.topPos + 126, 16, 16)
                        .tooltip(Tooltip.create(PREVIOUS_PAGE_TOOLTIP))
                        .build()
        );
        this.ItemBuyButton = addRenderableWidget(

                Button.builder(ITEM_BUY_BUTTON, this::handleItem1BuyButton)
                        .bounds(this.leftPos + 26, this.topPos + 18, 27, 16)
                        .tooltip(Tooltip.create(BUY_TOOLTIP1))
                        .build()
        );

        this.ItemSellButton = addRenderableWidget(
                Button.builder(ITEM_SELL_BUTTON, this::handleItem1SellButton)
                        .bounds(this.leftPos + 114, this.topPos + 18, 27, 16)
                        .tooltip(Tooltip.create(SELL_TOOLTIP1))
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
        graphics.blit(GUI_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        super.render(graphics, mouseX, mouseY, partialTicks);

        graphics.drawString(this.font,
                TITLE,
                this.leftPos + 8,
                this.topPos + 8,
                0x404040,
                false
        );
        graphics.drawString(this.font,
                ACCOUNT,
                this.leftPos + 26,
                this.topPos + 129,
                0xFFFFFF
        );
        graphics.drawString(this.font,
                ACT_PAGE,
                this.leftPos + 155,
                this.topPos + 8,
                0x404040
        );

        graphics.drawString(this.font,
                BUY_PRICE1,
                this.leftPos + 55, this.topPos + 24, 0xFFFFFF
        );
        graphics.drawString(this.font,
                SELL_PRICE1,
                this.leftPos + 143, this.topPos + 24, 0xFFFFFF
        );

        ResourceLocation item1Texture = determineItemTexture1();
        if (item1Texture != null) {
            graphics.blit(
                    item1Texture,
                    this.leftPos + 8, this.topPos + 18,
                    0, 0,
                    16, 16, 16, 16);
        }
        graphics.drawString(this.font,
                QSTOCK1,
                this.leftPos + 13, this.topPos + 27, 0xFFFFFF
        );

    }

    private void handleNextpage(Button button) {
        if (ActPage >= MarketItem.getInstance().getMaxPage()) {
            return;
        } else {
            ActPage = ActPage + 1;
        }
        System.out.println("ActPage:"+ActPage);
        updatePriceValues();
    }

    private void handlePreviouspage(Button button) {
        if (ActPage > 1) {
            ActPage = ActPage - 1;
        } else {
            return;
        }
        System.out.println("ActPage:"+ActPage);
        updatePriceValues();
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