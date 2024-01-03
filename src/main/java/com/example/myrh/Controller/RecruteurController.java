package com.example.myrh.Controller;

import com.example.myrh.BaseInterface.IBaseController;
import com.example.myrh.DTO.RecruteurDTO;
import com.example.myrh.DTO.Request.RecruteurRequest;
import com.example.myrh.Service.IRecruteurService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/recruteurs")
public class RecruteurController implements IBaseController<RecruteurDTO, RecruteurRequest> {

    private final IRecruteurService recruteurService ;
    private final ModelMapper modelMapper ;
    @Autowired
    public RecruteurController(IRecruteurService recruteurService, ModelMapper modelMapper) {
        this.recruteurService = recruteurService;
        this.modelMapper = modelMapper;
    }

    @Override
    @PostMapping
    public ResponseEntity<RecruteurDTO> create(@ModelAttribute @Valid RecruteurRequest recruteurRequest)  {
        return ResponseEntity.status(HttpStatus.CREATED).body(recruteurService.create(recruteurRequest));
    }

    @Override
    public ResponseEntity<List<RecruteurDTO>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<RecruteurDTO> getById(long id) {
        return null;
    }

    @Override
    public ResponseEntity<RecruteurDTO> update(long id, RecruteurRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(long id) {
        return null;
    }
}
