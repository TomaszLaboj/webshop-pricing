package com.example.pricing.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pricing.domain.model.ProductPrice;
import com.example.pricing.domain.model.Saving;
import com.example.pricing.kafka.KafkaProducer;
import com.example.pricing.repository.PricingRepositoryPostgres;
import com.example.pricing.repository.model.Discount;
import com.example.pricing.repository.model.ProductStockPrice;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class PricingService {

    private final PricingRepositoryPostgres pricingRepositoryPostgres;
    private final KafkaProducer kafkaProducer;

    @Autowired
    public PricingService(PricingRepositoryPostgres pricingRepositoryPostgres,  KafkaProducer kafkaProducer) {
        this.pricingRepositoryPostgres = pricingRepositoryPostgres;
        this.kafkaProducer = kafkaProducer;
    };

    public ProductPrice updateStock(ProductPrice productPrice) throws JsonProcessingException {
        pricingRepositoryPostgres.updateStock(productPrice);
        return productPrice; // is this correct it returns received product price, maybe it should return product price with current price
    };

    public ProductPrice checkPrice(Long id) throws JsonProcessingException {
        return ProductPrice.fromEntity(pricingRepositoryPostgres.findById(id));
    }

}
