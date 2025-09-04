package com.example.pricing.domain;

import java.util.List;

import com.example.pricing.domain.model.ProductPrice;
import com.example.pricing.repository.model.ProductPriceEntity;

public interface PricingRepository {
    ProductPriceEntity findById(Long id);
    List<ProductPriceEntity> findAllByProductId(Long productId);
    ProductPriceEntity createPrice(ProductPriceEntity productPriceEntity);
    ProductPriceEntity updatePrice(ProductPrice productPrice);
}
