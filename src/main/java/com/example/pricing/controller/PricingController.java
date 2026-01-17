package com.example.pricing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.pricing.domain.PricingService;
import com.example.pricing.domain.model.ProductPrice;

@RestController
public class PricingController {

    PricingService pricingService;

    @Autowired
    public PricingController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @GetMapping(value="/price", params="productId")
    public ProductPrice getProduct(@RequestParam("productId") Long productId) {
        return pricingService.getPrice(productId);
    }

}
