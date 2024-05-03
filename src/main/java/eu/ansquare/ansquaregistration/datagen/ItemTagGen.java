package eu.ansquare.ansquaregistration.datagen;

import eu.ansquare.ansquaregistration.Rg;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.HolderLookup;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTagGen extends FabricTagProvider.ItemTagProvider {
    private Rg rg;
    public ItemTagGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable BlockTagProvider blockTagProvider, Rg rg) {
        super(output, completableFuture, blockTagProvider);
        this.rg = rg;
    }

    @Override
    protected void configure(HolderLookup.Provider arg) {
    }
}
