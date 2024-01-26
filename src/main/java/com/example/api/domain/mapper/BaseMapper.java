package com.example.api.domain.mapper;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BaseMapper<E, F, V> {
    E formToEntity(F form);

    V entityToView(E entity);

    E viewToEntity(V view);

    F entityToForm(E entity);

    F viewToForm(V view);

}
