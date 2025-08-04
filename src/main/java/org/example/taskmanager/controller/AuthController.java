package org.example.taskmanager.controller;

import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.example.taskmanager.config.JwtConfig;
import org.example.taskmanager.dtos.JwtResponse;
import org.example.taskmanager.dtos.UserDtos.UserDto;
import org.example.taskmanager.dtos.UserDtos.UserLoginRequest;
import org.example.taskmanager.mapper.UserMapper;
import org.example.taskmanager.repositories.UserRepository;
import org.example.taskmanager.services.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final JwtConfig jwtConfig;
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest loginRequest
            , HttpServletResponse response) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
            var accessToken = jwtService.generateAccessToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);

            var cookie = new Cookie("Refresh",refreshToken);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(jwtConfig.getRefreshTokenExpiration());
            response.addCookie(cookie);

            return ResponseEntity.ok(new JwtResponse(accessToken));
    }

    @PostMapping("/validate")
    public Boolean validate(@RequestHeader("Authorization") String header) {
        var token  = header.replace("Bearer ", "");
        return jwtService.validateToken(token);
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refresh(
            @CookieValue(value = "Refresh") String refreshToken
    ){
        if(!jwtService.validateToken(refreshToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var userId = jwtService.getUserIdByToken(refreshToken);
        var user = userRepository.findById(userId).orElseThrow();
        var accessToken = jwtService.generateAccessToken(user);

        return ResponseEntity.ok(new JwtResponse(accessToken));
    }

    @GetMapping("/CurrentUser")
    public ResponseEntity<UserDto> getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId =(Long) authentication.getPrincipal();

        var user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var userDto = userMapper.toUserDto(user);
        return ResponseEntity.ok(userDto);

    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<?> handleBadCredentials() {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
