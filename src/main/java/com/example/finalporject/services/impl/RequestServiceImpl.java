package com.example.finalporject.services.impl;

import com.example.finalporject.dao.RequestRepo;
import com.example.finalporject.models.entities.Code;
import com.example.finalporject.models.entities.Request;
import com.example.finalporject.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {
    private RequestRepo requestRepo;

    public RequestServiceImpl(RequestRepo requestRepo) {
        this.requestRepo = requestRepo;
    }

    @Override
    public void saveRequest(Request request) {
        requestRepo.save(request);
    }

    @Override
    public int countByCodeId(Code code) {
        return requestRepo.countByCodeId(code);
    }
}
