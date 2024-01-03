package com.example.myrh.BaseInterface;

import java.util.List;

public interface IBaseService <DTO,Request> {

    public DTO create(Request request);
    public DTO update(long id,Request request);
    public DTO findById(long id);
    public void delete(long id);
    public List<DTO> findAll();

}
