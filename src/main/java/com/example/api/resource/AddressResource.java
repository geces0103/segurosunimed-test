package com.example.api.resource;

import com.example.api.domain.dto.AddressForm;
import com.example.api.domain.dto.AddressView;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface AddressResource {

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/address/customer")
    AddressView create(@Valid @RequestBody AddressForm form);
}
