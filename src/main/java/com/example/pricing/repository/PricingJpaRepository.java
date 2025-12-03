package com.example.pricing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.pricing.repository.model.ProductPriceEntity;

public interface PricingJpaRepository extends CrudRepository<ProductPriceEntity, Long> {
    Optional<ProductPriceEntity> findById(Long id);
    List<ProductPriceEntity> findAllById(Long id);
    List<ProductPriceEntity> findAll();
}
