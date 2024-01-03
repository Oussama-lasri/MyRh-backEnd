package com.example.myrh.Service.Impl;

import com.example.myrh.DTO.OffreDTO;
import com.example.myrh.DTO.Request.SoumissionOffreRequest;
import com.example.myrh.DTO.SoumissionOffreDTO;
import com.example.myrh.Entity.FileEntity;
import com.example.myrh.Entity.Offre;
import com.example.myrh.Entity.SoumissionOffre;
import com.example.myrh.Error.ErrorMessageOffre;
import com.example.myrh.Exception.OffreException;
import com.example.myrh.Exception.SoumissionOffreException;
import com.example.myrh.Repository.OffreRepository;
import com.example.myrh.Repository.SoummissionOffreRepository;
import com.example.myrh.Service.IFileService;
import com.example.myrh.Service.ISoummissionOffreService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SoummissionOffreServiceImpl implements ISoummissionOffreService {

    private final SoummissionOffreRepository soummissionOffreRepository;
    private final OffreRepository offreRepository ;
    private final ModelMapper modelMapper ;
    private final IFileService fileService ;

    public SoummissionOffreServiceImpl(SoummissionOffreRepository soummissionOffreRepository, OffreRepository offreRepository, ModelMapper modelMapper, IFileService fileService) {
        this.soummissionOffreRepository = soummissionOffreRepository;
        this.offreRepository = offreRepository;
        this.modelMapper = modelMapper;
        this.fileService = fileService;
    }

    @Override
    public SoumissionOffreDTO create(SoumissionOffreRequest soumissionOffreRequest) {
        Offre offre = offreRepository.findById(soumissionOffreRequest.getOffre_id()).
                orElseThrow(()-> new OffreException(ErrorMessageOffre.NO_RECORD_FOUND.getErrorMessage()));
        FileEntity file = null ;
        try {
            file = fileService.storeFile(soumissionOffreRequest.getFile());
        }catch (Exception ex){
            throw new SoumissionOffreException(ex.getMessage());
        }

        SoumissionOffre soumission = SoumissionOffre.builder()
                .offre(offre)
                .file(file)
                .build();

        SoumissionOffre offreCreated = soummissionOffreRepository.save(soumission);

        return SoumissionOffreDTO.builder()
                .offre(modelMapper.map(offreCreated.getOffre(), OffreDTO.class))
                .file(offreCreated.getFile())
                .id(offreCreated.getId())
                .build();
    }

    @Override
    public SoumissionOffreDTO update(long id, SoumissionOffreRequest soumissionOffreRequest) {
        return null;
    }

    @Override
    public SoumissionOffreDTO findById(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<SoumissionOffreDTO> findAll() {
        return null;
    }
}
