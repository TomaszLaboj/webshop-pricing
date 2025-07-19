package com.example.pricing.domain.model;

import java.util.List;

import com.example.pricing.repository.model.Discount;
import com.example.pricing.repository.model.ProductPriceEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductPrice implements Price {
    Long id;
    float price;
    List<Discount> discounts;

    public static ProductPrice fromEntity(ProductPriceEntity productPrice) {
        return new ProductPrice(productPrice.getId(), productPrice.getPrice(), productPrice.getDiscounts());
    }
}
