package org.alancesar.darkroom.engine.filter;

import org.alancesar.darkroom.engine.editor.Image;

public enum Filter {
    NO_FILTER(0, "Original", "nofilter", (image) -> {}),
    GOTHAM(1, "Gotham", "gotham", new Gotham()),
    TOASTER(2, "Toaster", "toaster", new Toaster()),
    NASHVILLE(3, "Nashville", "nashvile", new Nashville()),
    LOMO(4, "Lomo", "lomo", new Lomo()),
    KELVIN(5, "Kelvin", "kelvin", new Kelvin()),
    NORMALIZE(6, "Normalize", "normalize", new Normalize());

    private transient final long id;
    private final String longName;
    private final String shortName;
    private transient final Operation operation;

    Filter(long id, String longName, String shortName, Operation operation) {
        this.id = id;
        this.longName = longName;
        this.shortName = shortName;
        this.operation = operation;
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

    public void apply(Image image) {
    	operation.process(image);
    }
}
