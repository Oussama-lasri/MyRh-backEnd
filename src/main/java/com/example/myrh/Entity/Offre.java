package com.example.myrh.Entity;

import com.example.myrh.Enum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @Column(nullable = false)
    private String titre ;

    @Column(nullable = false)
    private String description ;

    @Column(nullable = false)
    private String ville ;

    @Column(nullable = false)
    private String  niveauEducation ;

    @Column(nullable = false)
    private float salaire ;

    @Column(nullable = false)
    private String profilRechecher ;

    @Column(nullable = false)
    private Status status ;

    @ManyToOne
    @JoinColumn(name = "recruteur_id")
    private Recruteur recruteur;


}
