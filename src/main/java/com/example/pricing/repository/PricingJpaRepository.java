package com.example.pricing.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.pricing.repository.model.ProductPriceEntity;

public interface PricingJpaRepository extends CrudRepository {
    ProductPriceEntity findById(Long id);
}
