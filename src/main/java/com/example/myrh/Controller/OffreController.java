package com.example.myrh.Controller;

import com.example.myrh.BaseInterface.IBaseController;
import com.example.myrh.DTO.OffreDTO;
import com.example.myrh.DTO.Request.OffreRequest;
import com.example.myrh.Service.IOffreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/offres")
public class OffreController implements IBaseController<OffreDTO, OffreRequest> {

    private final IOffreService offreService ;
    @Autowired
    public OffreController(IOffreService offreService) {
        this.offreService = offreService;
    }

    @Override
    @PostMapping
    public ResponseEntity<OffreDTO> create(@RequestBody @Valid OffreRequest request) {
        OffreDTO Offre = offreService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Offre);
    }

    @Override
    public ResponseEntity<List<OffreDTO>> getAll() {

        return null;
    }

    @Override
    public ResponseEntity<OffreDTO> getById(long id) {
        return null;
    }

    @Override
    public ResponseEntity<OffreDTO> update(long id, OffreRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(long id) {
        return null;
    }
}
