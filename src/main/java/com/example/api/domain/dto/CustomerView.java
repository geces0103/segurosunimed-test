package com.example.api.domain.dto;

import com.example.api.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerView implements BaseDTO {
    private Long id;
    private String name;
    private String email;
    private String gender;
    private List<Address> addresses = new ArrayList<>();
}