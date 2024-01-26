package com.example.api.Controller;

import com.example.api.domain.Customer;
import com.example.api.domain.dto.CustomerForm;
import com.example.api.domain.dto.CustomerView;
import com.example.api.resource.CustomerResource;
import com.example.api.service.AbstractService;
import com.example.api.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController extends AbstractController<Customer, CustomerForm, CustomerView, Long> implements CustomerResource {

    private final CustomerService service;

    @Override
    public Page<CustomerView> getFilter(Pageable pageable, String name, String email, String gender, String city, String state) {
        return service.findByFilter(pageable, name, email, gender, city, state);
    }

    @Override
    public CustomerView update(Long id, CustomerForm form) {
        return service.update(id, form);
    }

    @Override
    protected AbstractService<Customer, CustomerForm, CustomerView, Long> getService() {
        return service;
    }
}
