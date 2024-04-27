package eu.ansquare.ansquaregistration;

import java.util.HashSet;
import java.util.Set;

public class Rg {
    public static Rg create(String modid){
        Rg rg = new Rg();
        rg.id = modid;
        rg.entries = new HashSet<>();
        return rg;
    }
    private String id;
    private Set<Entry<?>> entries;
    public void register(){
        entries.forEach(entry -> entry.register(id));
    }
    public ItemEntry createItem(String name){
        ItemEntry entry = new ItemEntry(name);
        entries.add(entry);
        return entry;
    }
}
