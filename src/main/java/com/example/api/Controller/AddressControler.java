package com.example.api.Controller;

import com.example.api.domain.Address;
import com.example.api.domain.dto.AddressForm;
import com.example.api.domain.dto.AddressView;
import com.example.api.resource.AddressResource;
import com.example.api.service.AbstractService;
import com.example.api.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/addresses")
public class AddressControler extends AbstractController<Address, AddressForm, AddressView, Long> implements AddressResource {

    private final AddressService service;

    @Override
    public AddressView create(AddressForm form) {
        return service.createAddress(form);
    }

    @Override
    protected AbstractService<Address, AddressForm, AddressView, Long> getService() {
        return service;
    }
}
