package org.alancesar.darkroom.engine.filter;

public enum Filter {
    NO_FILTER(0, "Original", "nofilter", Original.class),
    GOTHAM(1, "Gotham", "gotham", Gotham.class),
    TOASTER(2, "Toaster", "toaster", Toaster.class),
    NASHVILLE(3, "Nashville", "nashvile", Nashville.class),
    LOMO(4, "Lomo", "lomo", Lomo.class),
    KELVIN(5, "Kelvin", "kelvin", Kelvin.class);

    private transient final long id;
    private final String longName;
    private final String shortName;
    private transient final Class<?> instance;

    Filter(long id, String longName, String shortName, Class<?> instance) {
        this.id = id;
        this.longName = longName;
        this.shortName = shortName;
        this.instance = instance;
    }

    public static Filter getByName(String name) {
        for (Filter f : Filter.values()) {
            if (f.shortName.equalsIgnoreCase(name))
                return f;
        }

        return Filter.NO_FILTER;
    }

    public static Filter getById(long id) {
        for (Filter f : Filter.values()) {
            if (f.id == id)
                return f;
        }

        return null;
    }

    public long getId() {
        return id;
    }

    public String getLongName() {
        return longName;
    }

    public String getShortName() {
        return shortName;
    }

    public FilterEffect get() {
        try {
        	return (FilterEffect) instance.newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}
