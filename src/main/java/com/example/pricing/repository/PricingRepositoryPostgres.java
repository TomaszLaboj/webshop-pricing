package com.example.pricing.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.pricing.domain.PricingRepository;
import com.example.pricing.domain.model.ProductPrice;
import com.example.pricing.repository.model.ProductPriceEntity;
import com.example.pricing.repository.model.ProductStockPrice;

@Repository
public class PricingRepositoryPostgres implements PricingRepository {

    final private PricingJpaRepository pricingJpaRepository;

    @Autowired
    public PricingRepositoryPostgres(PricingJpaRepository pricingJpaRepository) {
        this.pricingJpaRepository = pricingJpaRepository;
    }

    public ProductPriceEntity findById(Long id) {
        Optional<ProductPriceEntity> product = pricingJpaRepository.findById(id);
        if(product.isPresent()) {
            return product.get();
        }
        return null;
    }

    public ProductPriceEntity updateStock(ProductPrice productPrice) {
        Optional<ProductPriceEntity> product = pricingJpaRepository.findById(productPrice.getId());
        if(product.isPresent()) {
            ProductPriceEntity productPriceEntityToUpdate = product.get();
            productPriceEntityToUpdate.addStockPrice(new ProductStockPrice(productPrice.getPrice(), productPrice.getStockQuantity()));
            return pricingJpaRepository.save(productPriceEntityToUpdate);
        }
        ProductPriceEntity productPriceEntity = new ProductPriceEntity(productPrice.getId());
        productPriceEntity.addStockPrice(new ProductStockPrice(productPrice.getPrice(), productPrice.getStockQuantity()));
        return pricingJpaRepository.save(productPriceEntity);
    }
}
