package com.example.finalporject.dao;

import com.example.finalporject.models.entities.Code;
import com.example.finalporject.models.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepo extends JpaRepository<Request, Long> {
    int countByCodeId(Code code);
}
