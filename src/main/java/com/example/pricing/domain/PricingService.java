package com.example.pricing.domain;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pricing.LoggingController;
import com.example.pricing.domain.model.ProductPrice;
import com.example.pricing.kafka.KafkaProducer;
import com.example.pricing.repository.PricingRepositoryPostgres;
import com.example.pricing.repository.model.ProductPriceEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

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

    public ProductPrice getPrice(Long id) {
        ProductPrice productPrice = pricingRepositoryPostgres.findProductPriceEntityById(id).toProductPrice();
        return productPrice;
    }

    public void sendPrice(Long id) throws JsonProcessingException {
        logger.info("from service, before getting the product " );
        ProductPrice productPrice = pricingRepositoryPostgres.findProductPriceEntityById(id).toProductPrice();
        logger.info("from service product price: " + productPrice.toString());

//        kafkaProducer.sendMessage("hello from pricing");
    }

    public List<ProductPrice> getAllPrices() {
        return pricingRepositoryPostgres.findAllProductPriceEntities();
    }

}
