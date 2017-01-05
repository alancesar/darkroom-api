package org.alancesar.darkroom.engine.filter;

public class FilterFactory {

    public static Filter getByName(String name) {

        Filters filter = Filters.getByName(name);
        
        if (filter != null) {
            return filter.getInstance(); 
        }
        
        return null;
    }
    
    public static Filter get(Filters filter) {
        return filter.getInstance();
    }
}
