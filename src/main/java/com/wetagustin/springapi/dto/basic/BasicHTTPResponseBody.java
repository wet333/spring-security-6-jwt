package com.wetagustin.springapi.dto.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicHTTPResponseBody<T> {
    private String message;
    private T data;
}
