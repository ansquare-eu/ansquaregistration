package eu.ansquare.ansquaregistration;

import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class Ansquaregistration implements ModInitializer {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    public Rg rg = Rg.create("ansquaregistration");
    public ItemEntry testItem = rg.createItem("test")
            .settings(new QuiltItemSettings().maxCount(1))
            .group(ItemGroups.NATURAL_BLOCKS)
            .build();
    @Override
    public void onInitialize(ModContainer mod) {
        rg.register();
    }
}