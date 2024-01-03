package com.example.myrh.DTO.Request;

import com.example.myrh.Entity.Recruteur;
import com.example.myrh.Enum.Status;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OffreRequest {

    @NotEmpty
    @NotNull
    private String titre ;

    @NotEmpty
    @NotNull
    private String description ;

    @NotEmpty
    @NotNull
    private String ville ;

    @NotEmpty
    @NotNull
    private String  niveauEducation ;


    @NotNull
    private float salaire ;

    @NotEmpty
    @NotNull
    private String profilRechecher ;


    private Status status ;

    @NotNull
    private long recruteur_id;

}
