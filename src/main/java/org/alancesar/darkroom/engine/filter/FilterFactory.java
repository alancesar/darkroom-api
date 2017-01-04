package org.alancesar.darkroom.engine.filter;

public class FilterFactory {

    public static Filter getByName(String name) {

        try {
            String className = "." + name.substring(0, 1).toUpperCase() + name.substring(1);
            Class<?> filter = Class.forName(Filter.class.getPackage().getName() + className);
            return (Filter) filter.newInstance();
        } catch (Exception e) {
        }

        return null;
    }
}
