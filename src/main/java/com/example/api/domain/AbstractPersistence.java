package com.example.api.domain;

import org.springframework.data.domain.Persistable;

public interface AbstractPersistence<T> extends Persistable<T> {

    @Override
    default boolean isNew() {
        return this.getId() == null;
    }
}
