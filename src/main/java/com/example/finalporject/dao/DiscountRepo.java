package com.example.finalporject.dao;

import com.example.finalporject.models.entities.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepo extends JpaRepository<Discount, Long> {
}
