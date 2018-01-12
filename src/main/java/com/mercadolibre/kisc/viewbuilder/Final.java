package com.mercadolibre.kisc.viewbuilder;

/**
 * Created by abertolo on 12/01/18.
 */
public class Final<T> {

    T instance;

    public Final() {
    }

    public Final(T instance) {
        this.instance = instance;
    }


    public T get() {
        if (instance == null) {
            throw new NullPointerException("Instance is null");
        }
        return instance;
    }

    public Final<T> set(T i) {
        if (instance != null) {
            throw new IllegalStateException("Can only set this instance once");
        }
        instance = i;
        return this;
    }
}
