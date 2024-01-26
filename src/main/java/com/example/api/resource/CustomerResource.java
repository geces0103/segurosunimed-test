package com.example.api.resource;

import com.example.api.domain.dto.CustomerForm;
import com.example.api.domain.dto.CustomerView;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public interface CustomerResource {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/filter")
    Page<CustomerView> getFilter(
            Pageable pageable,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state);


    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/{id}")
    CustomerView update(@PathVariable("id") Long id, @Valid @RequestBody CustomerForm form);

}
