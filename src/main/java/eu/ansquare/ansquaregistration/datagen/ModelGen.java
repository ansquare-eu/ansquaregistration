package eu.ansquare.ansquaregistration.datagen;

import eu.ansquare.ansquaregistration.BlockEntry;
import eu.ansquare.ansquaregistration.ItemEntry;
import eu.ansquare.ansquaregistration.Rg;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.model.BlockStateModelGenerator;
import net.minecraft.data.client.model.Model;
import net.minecraft.item.Item;

import java.util.Set;

public class ModelGen extends FabricModelProvider {
    private Rg rg;
    public ModelGen(FabricDataOutput output, Rg rg) {
        super(output);
        this.rg = rg;
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        Set<BlockEntry<? extends Block>> blocks = rg.getAllBlocks();
        blocks.forEach(blockEntry -> blockEntry.generateModel(blockStateModelGenerator));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        Set<ItemEntry<? extends Item>> items = rg.getAllItems();
        items.forEach(itemEntry -> {
            Model model = itemEntry.getModel();
            if(model != null) itemModelGenerator.register(itemEntry.get(), itemEntry.getModel());
        });
    }
}
