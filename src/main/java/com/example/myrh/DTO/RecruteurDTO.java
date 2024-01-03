package com.example.myrh.DTO;

import com.example.myrh.Entity.FileEntity;
import com.example.myrh.Entity.Offre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruteurDTO {
    private long id ;
    private String login ;
    private String password ;
    private String email ;
    private String Phone ;
    private FileEntity image;
    //private List<Offre> offreList ;
}
