package org.example.taskmanager.controller;

import lombok.AllArgsConstructor;
import org.example.taskmanager.dtos.JwtResponse;
import org.example.taskmanager.dtos.UserDtos.UserLoginRequest;
import org.example.taskmanager.services.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest loginRequest) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            var token = jwtService.GenerateToken(loginRequest.getEmail());
            return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/validate")
    public Boolean validate(@RequestHeader("Authorization") String header) {
        var token  = header.replace("Bearer ", "");
        return jwtService.validateToken(token);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<?> handleBadCredentials() {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
