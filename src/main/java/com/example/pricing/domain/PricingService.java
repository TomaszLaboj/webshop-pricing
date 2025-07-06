package com.example.pricing.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pricing.domain.model.ProductPrice;
import com.example.pricing.kafka.KafkaProducer;
import com.example.pricing.repository.PricingRepositoryPostgres;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class PricingService {

    private PricingRepositoryPostgres pricingRepositoryPostgres;
    private KafkaProducer kafkaProducer;

    @Autowired
    public PricingService(PricingRepositoryPostgres pricingRepositoryPostgres,  KafkaProducer kafkaProducer) {
        this.pricingRepositoryPostgres = pricingRepositoryPostgres;
        this.kafkaProducer = kafkaProducer;
    }

    public ProductPrice updatePrice(ProductPrice productPrice) throws JsonProcessingException {
        pricingRepositoryPostgres.updatePrice(productPrice);
        kafkaProducer.sendUpdated(productPrice);
        return productPrice;
    }
}
