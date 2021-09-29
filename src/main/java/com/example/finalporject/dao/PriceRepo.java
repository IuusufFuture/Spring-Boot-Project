package com.example.finalporject.dao;

import com.example.finalporject.models.entities.Price;
import com.example.finalporject.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepo extends JpaRepository<Price, Long> {
    List<Price> findAllByProductId(Product toProduct);
}
