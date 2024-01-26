package com.example.api.service;

import com.example.api.domain.Address;
import com.example.api.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestTemplateService {

    @Value("${url.cep}")
    private String urlCep;
    private final RestTemplate restTemplate;

    public Address findAddressByCep(String cep) {
        try {
            return restTemplate.getForEntity(urlCep + "/" + cep + "/json", Address.class).getBody();
        } catch (Exception e) {
            log.info("<< getAddressByCep [error={}]", e.getMessage());
            throw new GlobalException("Endereco correspondente ao CEP nao existe");
        }
    }
}
