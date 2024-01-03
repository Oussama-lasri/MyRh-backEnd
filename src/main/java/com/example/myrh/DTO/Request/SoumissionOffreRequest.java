package com.example.myrh.DTO.Request;

import com.example.myrh.Entity.FileEntity;
import com.example.myrh.Entity.Offre;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SoumissionOffreRequest {
    @NotNull
    private long offre_id ;
    private MultipartFile file ;
}
