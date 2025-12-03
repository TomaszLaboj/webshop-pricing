package com.example.pricing.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductPrices {
    Long productId;
    List<ProductStockPrice> stockPrices;
}
