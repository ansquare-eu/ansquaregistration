package eu.ansquare.ansquaregistration;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public abstract class Entry<T> {
    private Registry<? super T> registry;
    protected String id;
    protected T object;
    public void register(String modid){
        Registry.register(registry, new Identifier(modid, id), get());
    }
    public Entry(Registry<? super T> registry, String id){
        this.registry = registry;
        this.id = id;
    }
    protected abstract void build();
    public abstract int addDefaultTranslations(TranslationBuilder translationBuilder);
    @NotNull
    public T get(){
        if(object == null) build();
        return object;
    }
    public abstract void clientRegister(String modid);
}
