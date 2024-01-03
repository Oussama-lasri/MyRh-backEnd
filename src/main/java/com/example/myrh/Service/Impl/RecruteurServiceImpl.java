package com.example.myrh.Service.Impl;

import com.example.myrh.DTO.RecruteurDTO;
import com.example.myrh.DTO.Request.RecruteurRequest;
import com.example.myrh.Entity.FileEntity;
import com.example.myrh.Entity.Recruteur;
import com.example.myrh.Error.ErrorMessagesRecruteur;
import com.example.myrh.Exception.RecruteurException;
import com.example.myrh.Repository.RecruteurRepository;
import com.example.myrh.Service.IFileService;
import com.example.myrh.Service.IRecruteurService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service

public class RecruteurServiceImpl implements IRecruteurService {
    private static final Logger logger = LoggerFactory.getLogger(RecruteurServiceImpl.class.getName());

    private final RecruteurRepository recruteurRepository;
    private final ModelMapper modelMapper;
    private final IFileService fileService;
    @Autowired
    RecruteurServiceImpl(RecruteurRepository recruteurRepository, ModelMapper modelMapper, IFileService fileService){
        this.recruteurRepository = recruteurRepository ;
        this.modelMapper = modelMapper;
        this.fileService = fileService;
    }
    @Override
    public RecruteurDTO create(RecruteurRequest  recruteurRequest) {
        Optional<Recruteur> recruteur = recruteurRepository.findByEmail(recruteurRequest.getEmail());
        if (recruteur.isPresent()){
            throw new RecruteurException(ErrorMessagesRecruteur.EMAIL_ALREADY_EXIST.getErrorMessage());
        }
        FileEntity image = null;
        try{
             image = fileService.storeFile(recruteurRequest.getImage());
            logger.info("created image successfully");
        }catch (Exception ex){
            logger.warn("error image => " + ex.getMessage());
        }
        Recruteur recruteurSaved = modelMapper.map(recruteurRequest,Recruteur.class);
        recruteurSaved.setImage(image);
        try{
            recruteurSaved = recruteurRepository.save(recruteurSaved);
            logger.info("saved successfully");
        }catch(Exception ex){
            logger.warn(ex.getMessage());
        }
        return modelMapper.map(recruteurSaved, RecruteurDTO.class);
    }

    @Override
    public RecruteurDTO update(long id,RecruteurRequest  recruteurRequest) {
        return null;
    }

    @Override
    public RecruteurDTO findById(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<RecruteurDTO> findAll() {
        return null;
    }
}
