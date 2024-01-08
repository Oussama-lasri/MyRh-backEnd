package com.example.myrh.Entity;

import com.example.myrh.Enum.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recruteur  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @Column(nullable = false,unique = true)
    private String login ;
    @Column(nullable = false)
    private String password ;
    @Column(nullable = false,unique = true)
    private String email ;
    @Column(nullable = false,unique = true)
    private String Phone ;
    @Enumerated(EnumType.STRING)
    private Role role = Role.RECRUTEUR;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @CreationTimestamp
    private LocalDateTime updatedOn;




    private Boolean isValid;
    private Integer codeValidation;
    @OneToOne
    private FileEntity image;
    @OneToMany(mappedBy = "recruteur" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Offre> offreList ;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }


    public String getUsername() {
        return this.email;
    }
    @Override
    public String getPassword(){
        return this.password ;
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
