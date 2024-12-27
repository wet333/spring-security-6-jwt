package com.wetagustin.springapi.controllers;

import com.wetagustin.springapi.dto.basic.BasicHTTPResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/private")
public class PrivateController {

    @GetMapping("/check-access")
    public ResponseEntity<BasicHTTPResponseBody<?>> privateEndpointTest() {

        BasicHTTPResponseBody<String> responseBody = new BasicHTTPResponseBody<>();
        responseBody.setMessage("You gained access to private data");

        return ResponseEntity.ok().body(responseBody);
    }
}
