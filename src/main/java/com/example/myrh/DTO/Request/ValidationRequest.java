package com.example.myrh.DTO.Request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationRequest {

    private String email;
    private int codeValidation;
}
