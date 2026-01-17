package com.example.pricing.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pricing.LoggingController;
import com.example.pricing.kafka.KafkaProducer;
import com.example.pricing.repository.PricingRepositoryPostgres;

@Service
public class PricingService {

    private final PricingRepositoryPostgres pricingRepositoryPostgres;
    private final KafkaProducer kafkaProducer;
    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    public PricingService(PricingRepositoryPostgres pricingRepositoryPostgres,  KafkaProducer kafkaProducer) {
        this.pricingRepositoryPostgres = pricingRepositoryPostgres;
        this.kafkaProducer = kafkaProducer;
    }

}
