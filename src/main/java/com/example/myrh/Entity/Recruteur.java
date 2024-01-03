package com.example.myrh.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recruteur {
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
    @OneToOne
    private FileEntity image;
    @OneToMany(mappedBy = "recruteur" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Offre> offreList ;
}
