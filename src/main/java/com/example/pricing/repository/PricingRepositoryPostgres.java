package com.example.pricing.repository;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.pricing.LoggingController;
import com.example.pricing.domain.PricingRepository;
import com.example.pricing.domain.model.ProductPrice;
import com.example.pricing.repository.model.ProductPriceEntity;
import com.example.pricing.repository.model.ProductStockPrice;

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

    public List<ProductPrice> findAllProductPriceEntities() {
        List<ProductPriceEntity> productEntites = pricingJpaRepository.findAll();
        List<ProductPrice> products = productEntites.stream().map((productEntity) -> productEntity.toProductPrice()).toList();
        return products;
    }
}
