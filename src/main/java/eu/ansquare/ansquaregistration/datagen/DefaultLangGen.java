package eu.ansquare.ansquaregistration.datagen;

import eu.ansquare.ansquaregistration.Rg;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import java.nio.file.Path;

public class DefaultLangGen extends FabricLanguageProvider {
    private Rg rg;

    public DefaultLangGen(FabricDataOutput dataOutput, Rg rg) {
        super(dataOutput);
        this.rg = rg;
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        rg.getAllEntries().forEach(entry -> entry.addDefaultTranslations(translationBuilder));
    }
}
