package eu.ansquare.ansquaregistration;

import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public abstract class Entry<T> {
    private Registry<T> registry;
    private String id;
    protected T object;
    public void register(String modid){
        if(object == null) throw new RuntimeException("Object is null");
        Registry.register(registry, new Identifier(modid, id), object);
    }
    public Entry(Registry<T> registry, String id){
        this.registry = registry;
        this.id = id;
    }
}
