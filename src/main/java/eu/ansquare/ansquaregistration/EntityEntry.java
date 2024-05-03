package eu.ansquare.ansquaregistration;


import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.entity.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.quiltmc.qsl.entity.extensions.api.QuiltEntityTypeBuilder;
import org.quiltmc.qsl.entity.extensions.impl.QuiltEntityType;

import java.util.function.Consumer;

public class EntityEntry<N extends Entity, B extends QuiltEntityTypeBuilder<N>> extends Entry<EntityType<N>> {
    private String defaultTranslation = "";
    private B builder;
    private EntityType.EntityFactory<N> factory;
    private Consumer<B> builderConsumer = builder -> {};
    public EntityEntry(String id, B builder) {
        super(Registries.ENTITY_TYPE, id);
        this.builder = builder;
    }

    public EntityEntry<N, B> defaultName(String name){
        this.defaultTranslation = name;
        return this;
    }
    public EntityEntry<N, B> properties(Consumer<B> consumer){
        builderConsumer = builderConsumer.andThen(consumer);
        return this;
    }
    @Override
    protected void build() {
        builderConsumer.accept(builder);
        object = builder.build();
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
