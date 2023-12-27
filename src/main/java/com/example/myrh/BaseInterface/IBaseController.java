package com.example.myrh.BaseInterface;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBaseController<DTO, R> {
    ResponseEntity<DTO> create(R request);

    ResponseEntity<List<DTO>> getAll();

    ResponseEntity<DTO> getById(long id);

    ResponseEntity<DTO> update(long id, R request);

    ResponseEntity<?> delete(long id);
}
