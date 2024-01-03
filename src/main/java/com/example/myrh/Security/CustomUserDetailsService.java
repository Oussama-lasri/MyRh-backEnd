//package com.example.myrh.Security;
//
//import com.example.myrh.Entity.Role;
//import com.example.myrh.Entity.UserEntity;
//import com.example.myrh.Exception.RecruteurException;
//import com.example.myrh.Repository.RecruteurRepository;
//import com.example.myrh.Repository.UserRepository;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    private final UserRepository userRepository;
//
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity user = userRepository.findByUsername(username).orElseThrow(()->
//            new RecruteurException("UserName not found"));
//        return null ;
//        //return new User(user.getUsername(),user.getPassword(),mapRolesToAuthorities((List<>)user.getRole()));
//    }
//
//    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles){
//        return roles.stream().map(role ->
//            new SimpleGrantedAuthority(role.getName())
//        ).collect(Collectors.toList());
//    }
//}
