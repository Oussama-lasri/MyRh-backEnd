package com.example.myrh.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService ;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.register(request));
    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody authenticationRequest request
    ){
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.authentication(request));
    }
}
