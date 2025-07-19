package com.example.pricing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pricing.domain.PricingService;
import com.example.pricing.domain.model.ProductPrice;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class PricingController {

    PricingService pricingService;

    @Autowired
    public PricingController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

}
