package com.bandi.rest.guice;

import javax.inject.Inject;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;

public class MagicProvider<T> implements Provider<T> {
    private Injector injector;
    private final Key<?>[] keys;
    private final Class<? extends T> clazz;

    MagicProvider(Class<? extends T> clazz, Key<?>... keys) {
        this.clazz = clazz;
        this.keys = keys;
    }

    @Inject
    public void setInjector(Injector injector) {
        this.injector = injector;
    }

    @Override
    public T get() {
        Object[] ctorParams = new Object[keys.length];
        Class<?>[] ctorTypes = new Class<?>[keys.length];
        for(int index = 0; index < ctorParams.length; index++) {
            ctorParams[index] = injector.getInstance(keys[index]);
            ctorTypes[index] = keys[index].getTypeLiteral().getRawType();
        }
        try {
            return clazz.getConstructor(ctorTypes).newInstance(ctorParams);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Should be handled better than this!", e);
        }
    }
}
