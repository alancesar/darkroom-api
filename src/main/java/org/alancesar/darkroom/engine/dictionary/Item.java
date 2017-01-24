package org.alancesar.darkroom.engine.dictionary;

public interface Item<T> {

    T read(Dictionary dictionary);

    void setNext(Item<T> next);
}
