package com.example.myrh.Repository;

import com.example.myrh.Entity.SoumissionOffre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoummissionOffreRepository extends JpaRepository<SoumissionOffre,Long> {
}
