package com.wetagustin.springapi.security.dtos;

import lombok.Data;

@Data
public class JwtSignInRequest {
    private String username;
    private String password;
}
