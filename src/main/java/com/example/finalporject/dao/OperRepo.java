package com.example.finalporject.dao;

import com.example.finalporject.models.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperRepo extends JpaRepository<Operation, Long> {
}
