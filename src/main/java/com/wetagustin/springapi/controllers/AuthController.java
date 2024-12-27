package com.wetagustin.springapi.controllers;

import com.wetagustin.springapi.dto.basic.BasicHTTPResponseBody;
import com.wetagustin.springapi.models.Role;
import com.wetagustin.springapi.models.User;
import com.wetagustin.springapi.repositories.RoleRepository;
import com.wetagustin.springapi.repositories.UserRepository;
import com.wetagustin.springapi.security.JwtUtils;
import com.wetagustin.springapi.security.configurations.Roles;
import com.wetagustin.springapi.security.dtos.JwtSignInRequest;
import com.wetagustin.springapi.security.dtos.JwtSignUpRequest;
import com.wetagustin.springapi.security.dtos.JwtTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, RoleRepository roleRepository, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/check")
    public ResponseEntity<?> check() {
        return ResponseEntity.ok().body("Public Auth API.");
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody JwtSignInRequest signInRequest) {

        BasicHTTPResponseBody<JwtTokenResponse> responseBody = new BasicHTTPResponseBody<>();

        try {
            String username = signInRequest.getUsername();
            String password = signInRequest.getPassword();

            Optional<User> user = userRepository.findByUsername(username);

            if (user.isEmpty()) {
                return ResponseEntity.badRequest().body("Username or password is incorrect.");
            }

            if (!passwordEncoder.matches(password, user.get().getPassword())) {
                return ResponseEntity.badRequest().body("Username or password is incorrect.");
            }

            String authToken = jwtUtils.generateToken(user.get());

            responseBody.setMessage("Successfully logged in.");
            responseBody.setData(JwtTokenResponse.builder().token(authToken).build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error occurred while logging in.");
        }
        return ResponseEntity.ok().body(responseBody);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> singUp(@RequestBody JwtSignUpRequest signUpRequest) {

        BasicHTTPResponseBody<String> responseBody = new BasicHTTPResponseBody<>();

        try {
            String username = signUpRequest.getUsername();
            String password = passwordEncoder.encode(signUpRequest.getPassword());
            String email = signUpRequest.getEmail();

            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                return ResponseEntity.badRequest().body("Credentials cannot be empty");
            }

            if (userRepository.existsByUsername(username)) {
                return ResponseEntity.badRequest().body("User with username " + username + " already exists");
            }

            if (userRepository.existsByEmail(email)) {
                return ResponseEntity.badRequest().body("Email " + email + " is already taken.");
            }

            User newUser = new User();
            Optional<Role> role = roleRepository.findByName(Roles.ROLE_GUEST.getRole());

            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setEmail(email);
            newUser.setRoles(Set.of(role.orElseThrow()));

            userRepository.save(newUser);

            responseBody.setMessage("Successfully signed up");
            responseBody.setData("Account for " + username + ", has been created.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error occurred while signing up");
        }

        return ResponseEntity.ok().body(responseBody);
    }

}
