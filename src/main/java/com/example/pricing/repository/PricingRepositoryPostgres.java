package com.example.pricing.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.pricing.domain.PricingRepository;
import com.example.pricing.domain.model.ProductPrice;
import com.example.pricing.repository.model.ProductPriceEntity;

@Repository
public class PricingRepositoryPostgres implements PricingRepository {

    private PricingJpaRepository pricingJpaRepository;

    @Autowired
    public PricingRepositoryPostgres(PricingJpaRepository pricingJpaRepository) {
        this.pricingJpaRepository = pricingJpaRepository;
    }

    public ProductPriceEntity updatePrice(ProductPrice productPrice) {
        Optional<ProductPriceEntity> productToUpdate = pricingJpaRepository.findById(productPrice.getId());
        if (productToUpdate.isPresent()) {
            ProductPriceEntity updatedProduct = new ProductPriceEntity(productToUpdate.get().getId(), productPrice.getPrice());
            pricingJpaRepository.save(updatedProduct);
            return updatedProduct;
        }
        return null;
    };
}
