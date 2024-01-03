package com.example.myrh.DTO;

import com.example.myrh.Entity.FileEntity;
import com.example.myrh.Entity.Offre;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SoumissionOffreDTO {
    private Long id ;

    private OffreDTO offre ;

    private FileEntity file ;
}
