package com.example.myrh.auth;

import com.example.myrh.Entity.UserEntity;
import com.example.myrh.Enum.Role;
import com.example.myrh.Repository.UserRepository;
import com.example.myrh.Security.JwtService;
import com.example.myrh.Service.Impl.RecruteurServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class.getName());



    private final UserRepository userRepository ;
    private final PasswordEncoder passwordEncoder ;
    private final JwtService jwtService ;
    private final AuthenticationManager authenticationManager ;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = UserEntity.builder()
                .email(request.getEmail())
                .username(request.getFirstName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authentication(authenticationRequest request) {


        log.info("email: {}", request.getEmail());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            log.warn("Authentication failed =>" + e.getMessage());
            throw new RuntimeException("Authentication failed: " + e.getMessage(), e);
        }

        log.debug("Authentication successful for email: {}", request.getEmail());


        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
