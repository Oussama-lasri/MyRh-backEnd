package com.example.myrh.Controller;

import com.example.myrh.BaseInterface.IBaseController;
import com.example.myrh.DTO.OffreDTO;
import com.example.myrh.DTO.Request.OffreRequest;
import com.example.myrh.Service.IOffreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
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
    @GetMapping
    public ResponseEntity<List<OffreDTO>> getAll() {

        return ResponseEntity.status(HttpStatus.OK).body(offreService.findAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<OffreDTO> getById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(offreService.findById(id));
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
