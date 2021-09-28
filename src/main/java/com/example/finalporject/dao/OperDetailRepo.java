package com.example.finalporject.dao;

import com.example.finalporject.models.entities.OperationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperDetailRepo extends JpaRepository<OperationDetail, Long> {
}
