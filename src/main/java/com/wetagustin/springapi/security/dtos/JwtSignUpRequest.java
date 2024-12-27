package com.wetagustin.springapi.security.dtos;

import lombok.Data;

@Data
public class JwtSignUpRequest {

    private String username;
    private String password;
    private String email;
}
