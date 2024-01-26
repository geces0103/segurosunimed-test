package com.example.api.domain.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorView {
    private LocalDateTime timestamp = LocalDateTime.now();
    private String path;
    private Integer status;
    private String error;
    private List<String> messages;
}
