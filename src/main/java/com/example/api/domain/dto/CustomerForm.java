package com.example.api.domain.dto;

import com.example.api.domain.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
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
public class CustomerForm implements BaseDTO {
    @NotEmpty(message = "Name obrigatorio")
    private String name;
    @NotEmpty(message = "Email obrigatorio")
    private String email;
    @NotEmpty(message = "gender obrigatorio")
    private String gender;
    @JsonIgnore
    private List<Address> addresses = new ArrayList<>();
}
