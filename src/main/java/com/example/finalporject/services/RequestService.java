package com.example.finalporject.services;

import com.example.finalporject.models.entities.Code;
import com.example.finalporject.models.entities.Request;

public interface RequestService {
    void saveRequest(Request request);
    int countByCodeId(Code code);
}
