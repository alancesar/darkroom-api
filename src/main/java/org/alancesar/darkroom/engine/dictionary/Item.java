package org.alancesar.darkroom.engine.dictionary;

public interface Item<T> {

    T read(Dictionary dictionary);

    void next(Item<T> next);
}
