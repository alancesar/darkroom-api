package org.alancesar.darkroom.engine.filter;

import java.io.File;

public enum Filter {

	NO_FILTER("Original", "nofilter", (image) -> {
	}),

	GOTHAM("Gotham", "gotham", new Gotham()),

	TOASTER("Toaster", "toaster", new Toaster()),

	NASHVILLE("Nashville", "nashvile", new Nashville()),

	LOMO("Lomo", "lomo", new Lomo()),

	KELVIN("Kelvin", "kelvin", new Kelvin()),

	NORMALIZE("Normalize", "normalize", new Normalize());

	public String longName;
	public String shortName;
	private transient Operation operation;

	Filter(String longName, String shortName, Operation operation) {

		this.longName = longName;
		this.shortName = shortName;
		this.operation = operation;
	}

	public static Filter getByName(String name) {

		for (Filter filter : Filter.values()) {
			if (filter.shortName.equalsIgnoreCase(name)) {
				return filter;
			}
		}

		return Filter.NO_FILTER;
	}

	public void apply(File input) {

		operation.process(input);
	}
}
