package com.example.pricing.domain;

import java.util.List;

import com.example.pricing.domain.model.ProductDiscounts;
import com.example.pricing.domain.model.ProductPrice;
import com.example.pricing.repository.model.ProductPriceEntity;

public interface PricingRepository {
    ProductPriceEntity findById(Long id);
    ProductPriceEntity updateStock(ProductPrice productPrice);
}
