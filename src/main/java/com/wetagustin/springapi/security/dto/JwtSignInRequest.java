package com.wetagustin.springapi.security.dto;

import lombok.Data;

@Data
public class JwtSignInRequest {
    private String username;
    private String password;
}
