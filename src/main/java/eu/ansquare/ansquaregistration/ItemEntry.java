package eu.ansquare.ansquaregistration;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.data.client.model.Model;
import net.minecraft.data.client.model.Models;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.TagKey;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class ItemEntry<T extends Item> extends Entry<T> implements ItemConvertible {
    private RegistryKey<ItemGroup> itemGroup;
    private QuiltItemSettings settings;
    private Model itemModel = Models.SINGLE_LAYER_ITEM;
    private Function<QuiltItemSettings, T> factory;
    private String defaultTranslation = "";
    private Set<TagKey<Item>> tags = new HashSet<>();
    public ItemEntry(String id, Function<QuiltItemSettings, T> factory) {
        super(Registries.ITEM, id);
        this.factory = factory;
    }
    public ItemEntry<T> setDefaultNameIfEmpty(String name){
        if(defaultTranslation.equals("")) defaultTranslation = name;
        return this;
    }
    public ItemEntry<T> settings(QuiltItemSettings settings){
        this.settings = settings;
        return this;
    }
    public ItemEntry<T> group(RegistryKey<ItemGroup> itemGroup){
        this.itemGroup = itemGroup;
        return this;
    }
    public ItemEntry<T> model(Model model){
        this.itemModel = model;
        return this;
    }
    public ItemEntry<T> tag(TagKey<Item> tag){
        tags.add(tag);
        return this;
    }
    public ItemEntry<T> noModel(){
        this.itemModel = null;
        return this;
    }
    public ItemEntry<T> defaultName(String name){
        this.defaultTranslation = name;
        return this;
    }
    public Model getModel(){
        return itemModel;
    }
    @Override
    public void register(String modid) {
        super.register(modid);
        if(itemGroup == null) return;
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(content -> {
            content.addItem(get());
        });
    }

    @Override
    public Item asItem() {
        return get();
    }
    protected void build(){
        if(settings == null) settings = new QuiltItemSettings();
        object = factory.apply(settings);
    }

    @Override
    public int addDefaultTranslations(FabricLanguageProvider.TranslationBuilder translationBuilder) {
        translationBuilder.add(get(), defaultTranslation);
        return 1;
    }

    @Override
    public void clientRegister(String modid) {

    }

}
