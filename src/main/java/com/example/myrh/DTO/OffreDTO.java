package com.example.myrh.DTO;

import com.example.myrh.Entity.Recruteur;
import com.example.myrh.Entity.SoumissionOffre;
import com.example.myrh.Enum.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OffreDTO {
    private long id ;

    private String titre ;

    private String description ;

    private String ville ;


    private String  niveauEducation ;


    private float salaire ;


    private String profilRechecher ;


    private Status status ;


    private RecruteurDTO recruteur;


    private List<SoumissionOffre> soumissionList ;
}
