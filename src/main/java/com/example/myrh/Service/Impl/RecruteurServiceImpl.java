package com.example.myrh.Service.Impl;

import com.example.myrh.BaseInterface.IBaseService;
import com.example.myrh.DTO.RecruteurDTO;
import com.example.myrh.Service.IRecruteurService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruteurServiceImpl implements IRecruteurService {

    private final RecruteurRepository recruteurRepository;

    RecruteurServiceImpl(RecruteurRepository recruteurRepository ){
        this.recruteurRepository = recruteurRepository ;
    }
    @Override
    public RecruteurDTO create(RecruteurDTO recruteurDTO) {
        if
        return null;
    }

    @Override
    public RecruteurDTO update(long id, RecruteurDTO recruteurDTO) {
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
