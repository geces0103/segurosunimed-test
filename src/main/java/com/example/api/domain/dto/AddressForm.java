package com.example.api.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressForm implements BaseDTO {
    @NotNull(message = "ID do cliente obrigatorio")
    private Long customerId;
    @NotNull(message = "CEP obrigatorio")
    private String cep;
}
