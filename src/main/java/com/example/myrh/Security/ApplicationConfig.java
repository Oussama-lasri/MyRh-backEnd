package com.example.myrh.Security;

import com.example.myrh.Entity.Agent;
import com.example.myrh.Entity.Recruteur;
import com.example.myrh.Entity.UserEntity;
import com.example.myrh.Repository.AgentRepository;
import com.example.myrh.Repository.RecruteurRepository;
import com.example.myrh.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository ;
    private final AgentRepository agentRepository ;
    private final RecruteurRepository recruteurRepository ;
    @Bean
    public UserDetailsService userDetailsService(){


        return username -> {


//            Optional<UserEntity> userEntity = userRepository.findByEmail(username);
//            if (userEntity.isPresent()) {
//                return userEntity.get();
//            }

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
        };

    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public LogoutHandler logoutHandler(){
        return null;
    }
}
