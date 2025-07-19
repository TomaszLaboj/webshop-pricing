package com.example.pricing.domain.model;

import java.util.List;

import com.example.pricing.repository.model.Discount;

public class ProductPriceQuantity extends ProductPrice implements Price{
    Long quantity;
    public ProductPriceQuantity(Long id, float price, List<Discount> discounts, Long quantity) {
        super(id, price, discounts);
        this.quantity = id;
    }
}
