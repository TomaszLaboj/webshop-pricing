package com.example.pricing.domain.model;

import java.util.List;

import com.example.pricing.repository.model.Discount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Saving implements Price {
    String description;
    float savingAmount;
}
