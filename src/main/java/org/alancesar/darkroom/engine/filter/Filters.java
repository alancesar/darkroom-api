package org.alancesar.darkroom.engine.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filters {

    private static final Map<String, Filter> filters = new HashMap<>();

    static {
        filters.put("gotham", new Gotham());
        filters.put("grayscale", new Grayscale());
        filters.put("kelvin", new Kelvin());
        filters.put("lomo", new Lomo());
        filters.put("nashville", new Nashville());
        filters.put("normalize", new Normalize());
        filters.put("toaster", new Toaster());
        filters.put("night", new Night());
        filters.put("nofilter", new NoFilter());
        filters.put("highcontrast", new HighContrast());
    }

    public static Filter getByName(String filterName) {
        return filters.get(filterName);
    }

    public static List<Filter> getValues() {
        return new ArrayList<Filter>(filters.values());
    }

}
