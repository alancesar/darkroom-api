package org.alancesar.darkroom.engine.filter;

public enum Filters {
    Gotham(new Gotham(), "Gotham", "gotham"), Kelvin(new Kelvin(), "Kelvin", "kelvin"), Lomo(new Lomo(), "Lomo",
            "lomo"), Nashvile(new Nashville(), "Nashvile", "nashvile"), Normalize(new Normalize(), "Normalize",
                    "normalize"), Toaster(new Toaster(), "Toaster", "toaster"), NoFilter((input) -> {
                    }, "No Filter", "nofilter");

    private Filter filter;
    private String longName;
    private String shortName;

    private Filters(Filter filter, String longName, String shotName) {
        this.filter = filter;
        this.longName = longName;
        this.shortName = shotName;
    }

    public Filter getInstance() {
        return filter;
    }

    public String getLongName() {
        return longName;
    }

    public String getShortName() {
        return shortName;
    }

    public static Filters getByName(String name) {
        for (Filters filter : Filters.values()) {

            if (filter.shortName.equalsIgnoreCase(name) || filter.longName.equalsIgnoreCase(name)) {
                return filter;
            }
        }

        return null;
    }
}
