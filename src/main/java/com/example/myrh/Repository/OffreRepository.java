package com.example.myrh.Repository;

import com.example.myrh.Entity.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffreRepository extends JpaRepository<Offre,Long> {
}
