package com.example.pricing.repository;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.pricing.LoggingController;
import com.example.pricing.domain.PricingRepository;
import com.example.pricing.repository.model.ProductPriceEntity;

@Repository
public class PricingRepositoryPostgres implements PricingRepository {

    final private PricingJpaRepository pricingJpaRepository;

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    public PricingRepositoryPostgres(PricingJpaRepository pricingJpaRepository) {
        this.pricingJpaRepository = pricingJpaRepository;
    }

    public ProductPriceEntity findProductPriceEntityById(Long id) {
        Optional<ProductPriceEntity> product = pricingJpaRepository.findById(id);
        if(product.isPresent()) {
            return product.get();
        }
        return null;
    }
}
