package com.example.myrh.Service.Impl;

import com.example.myrh.DTO.OffreDTO;
import com.example.myrh.DTO.RecruteurDTO;
import com.example.myrh.DTO.Request.OffreRequest;
import com.example.myrh.Entity.FileEntity;
import com.example.myrh.Entity.Offre;
import com.example.myrh.Entity.Recruteur;
import com.example.myrh.Error.ErrorMessageGeneral;
import com.example.myrh.Exception.OffreException;
import com.example.myrh.Exception.RecruteurException;
import com.example.myrh.Repository.OffreRepository;
import com.example.myrh.Repository.RecruteurRepository;
import com.example.myrh.Service.IFileService;
import com.example.myrh.Service.IOffreService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OffreServiceImpl implements IOffreService {
    private static final Logger logger = LoggerFactory.getLogger(OffreServiceImpl.class.getName());
    private final OffreRepository offreRepository;
    private final RecruteurRepository recruteurRepository ;
    private final ModelMapper modelMapper ;

    @Autowired
    public OffreServiceImpl(OffreRepository offreRepository, RecruteurRepository recruteurRepository, ModelMapper modelMapper) {
        this.offreRepository = offreRepository;
        this.recruteurRepository = recruteurRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public OffreDTO create(OffreRequest offreRequest) {
        Recruteur recruteur = recruteurRepository.findById(offreRequest.getRecruteur_id()).orElseThrow(()->
                new RecruteurException(ErrorMessageGeneral.NO_RECORD_FOUND.getErrorMessage())
                );


        Offre offre = modelMapper.map(offreRequest,Offre.class);
        offre.setRecruteur(recruteur);
        Offre offreCreated = null ;
        try{
            offreCreated = offreRepository.save(offre);
            logger.info("created");
        }catch (Exception ex){
            ex.getMessage();
            logger.warn("something wrong");
        }

        OffreDTO offreDTO = modelMapper.map(offreCreated,OffreDTO.class);
        offreDTO.setRecruteur(modelMapper.map(offreCreated.getRecruteur(), RecruteurDTO.class));
        return offreDTO ;
    }

    @Override
    public OffreDTO update(long id, OffreRequest offreRequest) {
       Offre existOffre = offreRepository.findById(id).orElseThrow(()->
                new OffreException(ErrorMessageGeneral.NO_RECORD_FOUND.getErrorMessage())
                );
        existOffre.setDescription(offreRequest.getDescription());
        existOffre.setSalaire(offreRequest.getSalaire());
        existOffre.setNiveauEducation(offreRequest.getNiveauEducation());
        existOffre.setProfilRechecher(offreRequest.getProfilRechecher());

        Offre offreUpdated = offreRepository.save(existOffre);

        return modelMapper.map(offreUpdated,OffreDTO.class);
    }

    @Override
    public OffreDTO findById(long id) {
        Offre existOffre = offreRepository.findById(id).orElseThrow(()->
                new OffreException(ErrorMessageGeneral.NO_RECORD_FOUND.getErrorMessage())
        );
        return modelMapper.map(existOffre,OffreDTO.class);
    }

    @Override
    public void delete(long id) {
        Offre existOffre = offreRepository.findById(id).orElseThrow(()->
                new OffreException(ErrorMessageGeneral.NO_RECORD_FOUND.getErrorMessage())
        );
        offreRepository.delete(existOffre);
    }

    @Override
    public List<OffreDTO> findAll() {
        List<OffreDTO> listOffre = offreRepository.findAll().stream().map(offre->{
                    OffreDTO offreDTO = modelMapper.map(offre,OffreDTO.class);
                    offreDTO.setRecruteur(modelMapper.map(offre.getRecruteur(),RecruteurDTO.class));
                    return offreDTO ;
                }
                ).toList();
        return listOffre;
    }
}
