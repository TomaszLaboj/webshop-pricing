package com.example.pricing.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.pricing.domain.PricingRepository;
import com.example.pricing.domain.model.ProductPrice;
import com.example.pricing.repository.model.ProductPriceEntity;

@Repository
public class PricingRepositoryPostgres implements PricingRepository {

    @Autowired
    private PricingJpaRepository pricingJpaRepository;

    public ProductPriceEntity updatePrice(ProductPrice productPrice) {
        ProductPriceEntity productToUpdate = pricingJpaRepository.findById(productPrice.getId());
        ProductPriceEntity updatedProduct = new ProductPriceEntity(productToUpdate.getId(), productPrice.getPrice());
        pricingJpaRepository.save(updatedProduct);
        return updatedProduct;
    };
}
