package com.example.myrh.Controller;

import com.example.myrh.BaseInterface.IBaseController;
import com.example.myrh.DTO.RecruteurDTO;
import com.example.myrh.DTO.Request.RecruteurRequest;
import com.example.myrh.DTO.Request.ValidationRequest;
import com.example.myrh.Entity.Recruteur;
import com.example.myrh.Service.IRecruteurService;
import com.example.myrh.Service.Impl.RecruteurServiceImpl;
import com.example.myrh.auth.AuthenticationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
@Transactional(rollbackFor = Exception.class)
@RequestMapping("api/v1/recruteurs")
public class RecruteurController implements IBaseController<RecruteurDTO, RecruteurRequest> {

    private final RecruteurServiceImpl recruteurService ;
    private final ModelMapper modelMapper ;
    @Autowired
    public RecruteurController(RecruteurServiceImpl recruteurService, ModelMapper modelMapper) {
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


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@ModelAttribute @Valid  RecruteurRequest recruteurRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(recruteurService.register(recruteurRequest));

    }
    @PostMapping("/validateCode")
    public ResponseEntity<?> validationCompte(@RequestBody ValidationRequest validationRequest){
        if(recruteurService.validationEmail(validationRequest.getEmail(),validationRequest.getCodeValidation()))
            return ResponseEntity.status(HttpStatus.OK).body("validate");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("date expirated or code not correct");
    }


}
