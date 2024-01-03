package com.example.myrh.Repository;

import com.example.myrh.Entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FileRepository extends JpaRepository<FileEntity,Long> {
    public Optional<FileEntity> findByName(String name);
}
