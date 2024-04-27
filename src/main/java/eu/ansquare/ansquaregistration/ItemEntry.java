package eu.ansquare.ansquaregistration;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class ItemEntry extends Entry<Item> implements ItemConvertible {
    private RegistryKey<ItemGroup> itemGroup;
    private QuiltItemSettings settings;

    public ItemEntry(String id) {
        super(Registries.ITEM, id);
    }
    public ItemEntry settings(QuiltItemSettings settings){
        this.settings = settings;
        return this;
    }
    public ItemEntry group(RegistryKey<ItemGroup> itemGroup){
        this.itemGroup = itemGroup;
        return this;
    }
    @Override
    public void register(String modid) {
        super.register(modid);
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(content -> {
            content.addItem(object);
        });
    }

    @Override
    public Item asItem() {
        return object;
    }
    public ItemEntry build(){
        object = new Item(settings);
        return this;
    }

}
