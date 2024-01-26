package com.example.api.service;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.domain.dto.AddressForm;
import com.example.api.domain.dto.AddressView;
import com.example.api.domain.mapper.AddressMapper;
import com.example.api.domain.mapper.BaseMapper;
import com.example.api.exception.GlobalException;
import com.example.api.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AddressService extends AbstractService<Address, AddressForm, AddressView, Long> {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final CustomerService customerService;
    private final RestTemplateService restTemplateService;

    public AddressView createAddress(AddressForm form) {
        Optional<Customer> customer = customerService.findEntityById(form.getCustomerId());

        if(customer.isEmpty()) {
            throw new GlobalException("Cliente nao existe");
        }

        Address address = restTemplateService.findAddressByCep(form.getCep());

        address.setCustomer(customer.get());

        address = addressRepository.save(address);

        return addressMapper.entityToView(address);
    }

    @Override
    protected JpaRepository<Address, Long> getRepository() {
        return addressRepository;
    }

    @Override
    protected BaseMapper<Address, AddressForm, AddressView> getConverter() {
        return addressMapper;
    }
}
