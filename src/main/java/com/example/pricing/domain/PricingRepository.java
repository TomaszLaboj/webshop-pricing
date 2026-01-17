package com.example.pricing.domain;

import com.example.pricing.repository.model.ProductPriceEntity;

public interface PricingRepository {
    ProductPriceEntity findProductPriceEntityById(Long id);
}
