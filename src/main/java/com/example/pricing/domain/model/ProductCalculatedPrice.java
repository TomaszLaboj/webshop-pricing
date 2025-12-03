package com.example.pricing.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductCalculatedPrice {
    Long productId;
    float calculatedPrice;
}
