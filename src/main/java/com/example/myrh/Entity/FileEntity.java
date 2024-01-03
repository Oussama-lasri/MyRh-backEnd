package com.example.myrh.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

@Entity(name = "files")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name ;
    private String type ;
    private String path ;
    @Lob
    private byte[] fileData ;

}
