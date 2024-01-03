package com.example.myrh.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "soumissions_offre")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SoumissionOffre {

        @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id ;

        @ManyToOne
        private Offre offre ;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "file_id", referencedColumnName = "id")
        private FileEntity file ;

}
//soumission d'offre