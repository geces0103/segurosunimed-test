package com.example.api.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ADDRESS")
@EqualsAndHashCode(callSuper = true)
public class Address extends AbstractBaseId {
    @Column
    private String cep;
    @Column
    private String logradouro;
    @Column
    private String complemento;
    @Column
    private String bairro;
    @Column
    private String localidade;
    @Column
    private String uf;
    @Column
    private String ibge;
    @Column
    private String gia;
    @Column
    private String ddd;
    @Column
    private String siafi;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "ID_CUSTOMER")
    private Customer customer;
}
