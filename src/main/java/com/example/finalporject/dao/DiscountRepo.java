package com.example.finalporject.dao;

import com.example.finalporject.models.entities.Discount;
import com.example.finalporject.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountRepo extends JpaRepository<Discount, Long> {
    List<Discount> findAllByProductId(Product toProduct);
}
