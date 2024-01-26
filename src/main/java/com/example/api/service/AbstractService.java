package com.example.api.service;

import com.example.api.domain.AbstractPersistence;
import com.example.api.domain.dto.BaseDTO;
import com.example.api.domain.mapper.BaseMapper;
import com.example.api.exception.GlobalException;
import com.example.api.exception.NotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractService<E extends AbstractPersistence<K>, F extends BaseDTO, V extends BaseDTO, K extends Serializable> {
    /**
     * Returns the generic repository for the entity type and key type specified.
     *
     * @return the generic JpaRepository for the entity type and key type
     */
    protected abstract JpaRepository<E, K> getRepository();

    /**
     * Returns the generic converter for mapping entity type to DTO type.
     *
     * @return the generic BaseMapper for entity type and DTO type
     */
    protected abstract BaseMapper<E, F, V> getConverter();


    /**
     * Finds an object by its id in the repository.
     *
     * @param id the id representing the primary key
     * @return the specific object
     * @throws EntityNotFoundException if the object is not found in the repository
     * @since 1.0.0
     */
    public V findById(K id) {
        log.debug(">> findById [id={}] ", id);
        Optional<E> entity = getRepository().findById(id);
        log.debug("<< findById [id={}, entity={}] ", id, entity);
        E e = entity.orElseThrow(NotFoundException::new);
        V view = getConverter().entityToView(e);
        log.debug("<< findById [id={}, entity={}, view={}] ", id, entity, view);
        return view;
    }

    /**
     * Finds an object by its id in the repository.
     *
     * @param id the id representing the primary key
     * @return the specific object
     * @throws EntityNotFoundException if the object is not found in the repository
     * @since 1.0.0
     */
    public Optional<E> findEntityById(K id) {
        log.debug(">> findById [id={}] ", id);
        Optional<E> entity = getRepository().findById(id);
        entity.orElseThrow(NotFoundException::new);
        log.debug("<< findById [id={}, entity={}] ", id, entity);
        return entity;
    }

    /**
     * Saves a new entity in the database.
     *
     * @param dto the generic entity to be created
     * @return the entity saved in the database
     * @since 1.0.0
     */
    public V save(F form) {
        log.debug(">> save [form={}] ", form);
        E entity = getConverter().formToEntity(form);
        log.debug(">> save [entity={}, form={}] ", entity, form);
        E e = getRepository().save(entity);
        log.debug("<< save [entity={}] ", e);
        V view = getConverter().entityToView(e);
        log.debug("<< save [form={}] ", form);
        return view;
    }

    /**
     * Deletes an entity from the database with the given id.
     *
     * @param id the id of the entity to be deleted
     * @since 1.0.0
     */
    public void delete(K id) {
        Optional<E> entity = getRepository().findById(id);
        entity.orElseThrow(NotFoundException::new);
        log.debug(">> delete [id={}] ", id);
        getRepository().deleteById(id);
    }
}
