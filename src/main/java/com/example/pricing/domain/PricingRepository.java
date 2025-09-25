package com.example.pricing.domain;

import com.example.pricing.domain.model.ProductPrice;
import com.example.pricing.repository.model.ProductPriceEntity;

public interface PricingRepository {
    ProductPriceEntity findProductEntityById(Long id);
    ProductPriceEntity updateStock(ProductPrice productPrice);
    ProductPrice findProductPriceById(Long id);
}
