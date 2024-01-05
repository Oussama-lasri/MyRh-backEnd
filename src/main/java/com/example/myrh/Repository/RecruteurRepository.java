package com.example.myrh.Repository;

import com.example.myrh.Entity.Recruteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(rollbackFor = Exception.class)
public interface RecruteurRepository extends JpaRepository<Recruteur,Long> {

    Optional<Recruteur> findByEmail(String email);
    Optional<Recruteur> findById(Long id);

}
