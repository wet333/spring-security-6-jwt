package com.wetagustin.springapi.controllers;

import com.wetagustin.springapi.dto.basic.BasicHTTPResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/public")
public class PublicController {

    @GetMapping("/home")
    public ResponseEntity<BasicHTTPResponseBody<String>> home() {
        return ResponseEntity.ok().body(
                new BasicHTTPResponseBody<String>(
                        "API entrypoint",
                        "Hello, World!"
                )
        );
    }
}
