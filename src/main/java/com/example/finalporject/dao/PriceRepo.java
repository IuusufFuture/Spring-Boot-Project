package com.example.finalporject.dao;

import com.example.finalporject.models.entities.Price;
import com.example.finalporject.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepo extends JpaRepository<Price, Long> {
    List<Price> findAllByProductId(Product toProduct);
    Price findByProductId(Long id);
    @Query(value = "SELECT * FROM price WHERE price.id_products = ?1 and CURRENT_TIMESTAMP BETWEEN price.start_date and price.end_date",nativeQuery = true)
    Price findPriceByProductIdAndCurrentPriceBetweenSysdate(Long id);
}
