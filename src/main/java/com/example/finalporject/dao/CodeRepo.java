package com.example.finalporject.dao;

import com.example.finalporject.models.dto.CodeDto;
import com.example.finalporject.models.entities.Code;
import com.example.finalporject.models.entities.User;
import com.example.finalporject.models.enums.CodeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepo extends JpaRepository<Code, Long> {
    Code findByUserIdAndCodeStatus(User user, CodeStatus codeStatus);
}
