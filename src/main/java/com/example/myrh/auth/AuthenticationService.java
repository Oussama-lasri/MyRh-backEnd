package com.example.myrh.auth;

import com.example.myrh.Entity.Agent;
import com.example.myrh.Entity.Recruteur;
import com.example.myrh.Entity.UserEntity;
import com.example.myrh.Enum.Role;
import com.example.myrh.Repository.AgentRepository;
import com.example.myrh.Repository.RecruteurRepository;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class.getName());



    private final UserRepository userRepository ;
    private final AgentRepository agentRepository ;
    private final RecruteurRepository recruteurRepository ;
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

        log.info("Authentication successful for email: {}", request.getEmail());



        var user = checkUser(request.getEmail());
        Map<String , Object> hashMap = new HashMap<>();
       if(user instanceof  Recruteur){
           long id = ((Recruteur) user).getId();
           hashMap.put("recruteur",id);
       } else if (user instanceof  UserEntity) {
           long id = ((UserEntity) user).getId();
           hashMap.put("user",id);
       }else {
           long id = ((Agent) user).getId();
           hashMap.put("agent",id);
       }

        var jwtToken = jwtService.generateToken(hashMap,(UserDetails) user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private Object checkUser(String username) {
        Optional<Agent> agent = agentRepository.findByEmail(username);
        if (agent.isPresent()) {
            return agent.get();
        }

        Optional<Recruteur> recruteur = recruteurRepository.findByEmail(username);
        if(recruteur.isPresent()){
            return recruteur.get();
        }

        return userRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username: " + username)
        );
    }
}
