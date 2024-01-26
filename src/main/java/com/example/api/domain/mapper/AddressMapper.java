package com.example.api.domain.mapper;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.domain.dto.AddressForm;
import com.example.api.domain.dto.AddressView;
import com.example.api.domain.dto.CustomerForm;
import com.example.api.domain.dto.CustomerView;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = BaseMapper.class)
public interface AddressMapper extends BaseMapper<Address, AddressForm, AddressView> {
}
