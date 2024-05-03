package eu.ansquare.ansquaregistration.datagen;

import eu.ansquare.ansquaregistration.Rg;
import eu.ansquare.ansquaregistration.testinits.Ansquaregistration;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class BlockLootGen extends FabricBlockLootTableProvider {
    private Rg rg;
    public BlockLootGen(FabricDataOutput dataOutput, Rg rg) {
        super(dataOutput);
        this.rg = rg;
    }

    @Override
    public void generate() {
        rg.getAllBlocks().forEach(blockEntry -> blockEntry.generateLoot(this));
    }
}
