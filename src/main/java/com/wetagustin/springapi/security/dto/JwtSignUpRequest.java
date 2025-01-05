package com.wetagustin.springapi.security.dto;

import lombok.Data;

@Data
public class JwtSignUpRequest {

    private String username;
    private String password;
    private String email;
}
