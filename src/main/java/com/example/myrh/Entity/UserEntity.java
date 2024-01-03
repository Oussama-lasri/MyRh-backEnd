package com.example.myrh.Entity;

import com.example.myrh.Enum.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_user")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String username ;
    private String email ;
    private String password ;
//for create dynamic roles
//    @ManyToMany(fetch =  FetchType.EAGER,cascade =CascadeType.ALL)
//    @JoinTable(name = "user_roles",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id")
//            ,inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
//    private List<Role> roles = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Role role ;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
