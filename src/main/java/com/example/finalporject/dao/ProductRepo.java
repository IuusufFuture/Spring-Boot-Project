package com.example.finalporject.dao;

import com.example.finalporject.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByName(String productName);
    Product findByBarcode(String barcode);
}
