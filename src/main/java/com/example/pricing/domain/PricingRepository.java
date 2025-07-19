package com.example.pricing.domain;

import com.example.pricing.domain.model.ProductPrice;
import com.example.pricing.repository.model.ProductPriceEntity;

public interface PricingRepository {
    ProductPriceEntity findById(Long id);
    ProductPriceEntity createPrice(ProductPriceEntity productPriceEntity);
    ProductPriceEntity updatePrice(ProductPrice productPrice);
}
