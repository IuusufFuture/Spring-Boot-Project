package com.example.finalporject.dao;

import com.example.finalporject.models.entities.Discount;
import com.example.finalporject.models.entities.Price;
import com.example.finalporject.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepo extends JpaRepository<Discount, Long> {
    List<Discount> findAllByProductId(Product toProduct);
    @Query(value = "SELECT * FROM discount WHERE discount.id_products = ?1 and CURRENT_TIMESTAMP BETWEEN discount.start_date and discount.end_date",nativeQuery = true)
    Discount findDiscountByProductIdAndCurrentDiscountBetweenSysdate(Long id);
}
