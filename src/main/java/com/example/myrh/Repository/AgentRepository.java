package com.example.myrh.Repository;

import com.example.myrh.Entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent,Long> {

    Optional<Agent> findByEmail(String email);
}
