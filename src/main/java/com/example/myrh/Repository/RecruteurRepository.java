package com.example.myrh.Repository;

import com.example.myrh.Entity.Recruteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecruteurRepository extends JpaRepository<Recruteur,Long> {

    Optional<Recruteur> findByEmail(String email);
    Optional<Recruteur> findById(Long id);

}
