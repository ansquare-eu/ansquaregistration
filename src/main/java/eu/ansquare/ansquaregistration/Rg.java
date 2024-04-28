package eu.ansquare.ansquaregistration;

import eu.ansquare.ansquaregistration.datagen.DefaultLangGen;
import eu.ansquare.ansquaregistration.datagen.ModelGen;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class Rg {
    public static Rg create(String modid){
        Rg rg = new Rg();
        rg.id = modid;
        rg.entries = new HashSet<>();
        return rg;
    }
    public String getId(){
        return id;
    }
    public void runDatagen(FabricDataGenerator generator){
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider((FabricDataGenerator.Pack.Factory<ModelGen>) output -> new ModelGen(output, this));
        pack.addProvider((FabricDataGenerator.Pack.Factory<DefaultLangGen>) output -> new DefaultLangGen(output, this));
    }
    private String id;
    private Set<Entry<?>> entries;

    public void register(){
        entries.forEach(entry -> entry.register(id));
    }
    public void clientRegister(){
        entries.forEach(entry -> entry.clientRegister(id));
    }
    public <T extends Item> ItemEntry<T> createItem(String name, Function<QuiltItemSettings, T> factory){
        ItemEntry<T> entry = new ItemEntry<>(name, factory);
        entries.add(entry);
        return entry;
    }
    public <T extends Block> BlockEntry<T> createBlock(String name, Function<QuiltBlockSettings, T> factory){
        BlockEntry<T> entry = new BlockEntry<>(name, factory);
        entries.add(entry);
        return entry;
    }
    public Set<Entry<?>> getAllEntries(){
        return entries;
    }
    public Set<ItemEntry<? extends Item>> getAllItems(){
        Set<ItemEntry<? extends Item>> set = new HashSet<>();
        entries.forEach(entry -> {
            if(entry instanceof ItemEntry<? extends Item> item){
                set.add(item);
            }
            if(entry instanceof BlockEntry<? extends Block> block){
                if(block.hasItem()){
                    set.add(block.getItem());
                }
            }
        });
        return set;
    }
    public Set<BlockEntry<? extends Block>> getAllBlocks(){
        Set<BlockEntry<? extends Block>> set = new HashSet<>();
        entries.forEach(entry -> {
            if(entry instanceof BlockEntry<? extends Block> block){
                set.add(block);
            }
        });
        return set;
    }
}
