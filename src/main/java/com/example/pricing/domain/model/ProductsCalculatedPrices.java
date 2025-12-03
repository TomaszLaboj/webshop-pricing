package com.example.pricing.domain.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductsCalculatedPrices {

    List<ProductCalculatedPrice> productCalculatedPrices;
    public ProductsCalculatedPrices(List<ProductCalculatedPrice> productPrices) {
        this.productCalculatedPrices = productPrices;
    }
}
