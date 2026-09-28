package com.product_pagination.repository;

import com.product_pagination.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Page<Product> findByCategory(String category, Pageable pageable);

    Page<Product> findByPriceBetween(Double price, Double price2, Pageable pageable);

    Page<Product> findByInStockTrue(Pageable pageable);

    Page<Product> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
}
