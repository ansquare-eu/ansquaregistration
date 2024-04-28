package eu.ansquare.ansquaregistration.testinits;

import eu.ansquare.ansquaregistration.BlockEntry;
import eu.ansquare.ansquaregistration.ItemEntry;
import eu.ansquare.ansquaregistration.Rg;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.data.client.model.Models;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class Ansquaregistration implements ModInitializer {
    public static final Rg RG = Rg.create("ansquaregistration");
    public ItemEntry<Item> testItem = RG.createItem("test", Item::new)
            .settings(new QuiltItemSettings().maxCount(1))
            .group(ItemGroups.NATURAL_BLOCKS)
            .defaultName("Test");
    public ItemEntry<Item> testHandheld = RG.createItem("test_hand", Item::new)
            .settings(new QuiltItemSettings().maxCount(1))
            .group(ItemGroups.TOOLS_AND_UTILITIES)
            .model(Models.HANDHELD)
            .defaultName("Hand test");
    public BlockEntry<Block> testBlock = RG.createBlock("test_block", Block::new)
            .settings(QuiltBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque())
            .defaultName("Test block")
            .simpleItem(ItemGroups.NATURAL_BLOCKS)
            .renderLayer(RenderLayer.getTranslucent());
    @Override
    public void onInitialize(ModContainer mod) {
        RG.register();
    }
}