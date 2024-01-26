package com.example.api.Controller;

import com.example.api.domain.AbstractPersistence;
import com.example.api.domain.dto.BaseDTO;
import com.example.api.service.AbstractService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@Slf4j
public abstract class AbstractController<
        E extends AbstractPersistence<K>, F extends BaseDTO, V extends BaseDTO, K extends Serializable> {

    protected abstract AbstractService<E, F, V, K> getService();


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public V getById(@PathVariable K id) {
        log.debug(">> getById [id={}]", id);
        V view = getService().findById(id);
        log.debug("<< getById [id={} view={}]", id, view);
        return view;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/")
    public V create(@Valid @RequestBody F form) {
        log.debug(" >> create [form={}] ", form);
        V view = getService().save(form);
        log.debug(" << create [view={}] ", view);
        return view;
    }

    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable K id) {
        log.debug(">> getById {}", id);
        getService().delete(id);
    }
}
