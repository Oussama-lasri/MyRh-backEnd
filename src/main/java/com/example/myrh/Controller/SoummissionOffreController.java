package com.example.myrh.Controller;

import com.example.myrh.BaseInterface.IBaseController;
import com.example.myrh.DTO.Request.SoumissionOffreRequest;
import com.example.myrh.DTO.SoumissionOffreDTO;
import com.example.myrh.Service.ISoummissionOffreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RestController
@RequestMapping("/api/v1/")
public class SoummissionOffreController implements IBaseController<SoumissionOffreDTO, SoumissionOffreRequest> {

    private final ISoummissionOffreService soummissionOffreService ;
    private final ModelMapper modelMapper ;
    @Autowired
    public SoummissionOffreController(ISoummissionOffreService soummissionOffreService, ModelMapper modelMapper) {
        this.soummissionOffreService = soummissionOffreService;
        this.modelMapper = modelMapper;
    }


    @PostMapping("offres/{offre_id}/soumission")
    public ResponseEntity<SoumissionOffreDTO> createSoumission(@RequestParam("file") MultipartFile file, @PathVariable Long offre_id) {
            SoumissionOffreRequest request = SoumissionOffreRequest.builder()
                    .file(file)
                    .offre_id(offre_id)
                    .build();
            SoumissionOffreDTO offreDTO = soummissionOffreService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(offreDTO);
    }



    @Override
    public ResponseEntity<List<SoumissionOffreDTO>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<SoumissionOffreDTO> getById(long id) {
        return null;
    }

    @Override
    public ResponseEntity<SoumissionOffreDTO> update(long id, SoumissionOffreRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(long id) {
        return null;
    }

    @Override
    public ResponseEntity<SoumissionOffreDTO> create(SoumissionOffreRequest request) {
        return null;
    }
}
