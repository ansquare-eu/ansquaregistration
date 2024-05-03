package eu.ansquare.ansquaregistration;

import eu.ansquare.ansquaregistration.datagen.BlockLootGen;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.data.client.model.BlockStateModelGenerator;
import net.minecraft.data.client.model.BlockStateSupplier;
import net.minecraft.data.client.model.Model;
import net.minecraft.data.client.model.Models;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlockEntry<T extends Block> extends Entry<T> {
    private QuiltBlockSettings settings;
    private Function<QuiltBlockSettings, T> factory;
    private String defaultTranslation = "";
    private ItemEntry<? extends BlockItem> blockItem = null;
    private RenderLayer renderLayer;
    private BiConsumer<BlockEntry<? extends Block>, BlockStateModelGenerator> blockModelGenerator = ((blockEntry, generator) -> generator.registerSimpleCubeAll(blockEntry.get()));
    private BiConsumer<BlockEntry<? extends Block>, BlockLootGen> lootGen = (blockEntry, blockLootGen) -> {};
    public BlockEntry(String id, Function<QuiltBlockSettings, T> factory) {
        super(Registries.BLOCK, id);
        this.factory = factory;
    }
    public BlockEntry<T> settings(QuiltBlockSettings settings){
        this.settings = settings;
        return this;
    }
    public BlockEntry<T> defaultName(String name){
        this.defaultTranslation = name;
        return this;
    }
    public BlockEntry<T> renderLayer(RenderLayer layer){
        this.renderLayer = layer;
        return this;
    }
    public BlockEntry<T> loot(BiConsumer<BlockEntry<? extends Block>, BlockLootGen> consumer){
        lootGen = lootGen.andThen(consumer);
        return this;
    }
    public BlockEntry<T> drops(ItemConvertible item){
        return loot(((blockEntry, blockLootGen) -> blockLootGen.addDrop(blockEntry.get(), item)));
    }
    public BlockEntry<T> dropsSelf(){
        return drops(getItem());
    }
    public BlockEntry<T> model(BiConsumer<BlockEntry<? extends Block>, BlockStateModelGenerator> blockModelGenerator){
        this.blockModelGenerator = blockModelGenerator;
        return this;
    }
    public BlockEntry<T> noModel(){
        blockModelGenerator = null;
        return this;
    }
    public boolean generateModel(BlockStateModelGenerator generator){
        if(blockModelGenerator == null) return false;
        blockModelGenerator.accept(this, generator);
        return true;
    }
    public void generateLoot(BlockLootGen gen){
        lootGen.accept(this, gen);
    }
    @Override
    protected void build() {
        if(settings == null) settings =QuiltBlockSettings.create();
        object = factory.apply(settings);
        if(hasItem()){
            blockItem.build();
        }
    }

    @Override
    public void register(String modid) {
        super.register(modid);
        if(hasItem()){
            blockItem.register(modid);
        }
    }

    public boolean hasItem(){
        return blockItem != null;
    }
    public ItemEntry<? extends BlockItem> getItem() {
        return blockItem;
    }
    public BlockEntry<T> item(Function<BlockEntry<? extends Block>, ItemEntry<? extends BlockItem>> itemFactory){
        blockItem = itemFactory.apply(this);
        blockItem.noModel();
        return this;
    }
    public BlockEntry<T> simpleItem(){
        blockItem = new ItemEntry<>(id, settings1 -> new BlockItem(get(), settings1));
        blockItem.setDefaultNameIfEmpty(defaultTranslation);
        blockItem.noModel();
        return this;
    }
    public BlockEntry<T> simpleItem(RegistryKey<ItemGroup> group){
        simpleItem();
        blockItem.group(group);
        return this;
    }
    @Override
    public int addDefaultTranslations(FabricLanguageProvider.TranslationBuilder translationBuilder) {
        translationBuilder.add(get(), defaultTranslation);
        return 1;
    }

    @Override
    public void clientRegister(String modid) {
        if(renderLayer != null) BlockRenderLayerMap.put(renderLayer, get());
    }
}
