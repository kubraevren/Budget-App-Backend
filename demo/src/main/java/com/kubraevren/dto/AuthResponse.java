package com.kubraevren.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String accessToken;

    @Builder.Default // Builder kullanırken varsayılan değerin ezilmemesi için şart
    private String tokenType = "Bearer";

    private Long expiresIn;
}