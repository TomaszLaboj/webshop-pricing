package com.example.pricing.repository.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

public enum Discount {
    THREE_FOR_TWO,
    GET_ONE_GET_SECOND_HALF_PRICE,
    WEDNESDAY_DISCOUNT
}
