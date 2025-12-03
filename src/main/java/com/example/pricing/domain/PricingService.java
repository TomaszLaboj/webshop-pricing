package com.example.pricing.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pricing.domain.model.ProductCalculatedPrice;
import com.example.pricing.domain.model.ProductPrice;
import com.example.pricing.domain.model.ProductPrices;
import com.example.pricing.domain.model.ProductsCalculatedPrices;
import com.example.pricing.domain.model.ProductStockPrice;
import com.example.pricing.kafka.KafkaProducer;
import com.example.pricing.repository.PricingRepositoryPostgres;
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

    public ProductPrice checkPrice(Long productId) throws JsonProcessingException {
        return pricingRepositoryPostgres.findProductPriceById(productId);
    }

    public ProductsCalculatedPrices sendPricesUpdate() {
        List<ProductCalculatedPrice> productsWithCalculatedPrices = new ArrayList<>();

        List<ProductPrices> productStockPrices = pricingRepositoryPostgres.findAllProductStockPrices();
        for(ProductPrices productPrices : productStockPrices) {
            productsWithCalculatedPrices.add(calculateAveragePrice(productPrices));
        }
        return new ProductsCalculatedPrices(productsWithCalculatedPrices);
    }

    public ProductCalculatedPrice calculateAveragePrice(ProductPrices productPrices) {
        Float calculatedAveragePrice;
        List<Integer> list = new ArrayList<>();
        List<Float> listOfPrices = productPrices.getStockPrices().stream().map(productStockPrice -> Float.valueOf(productStockPrice.getPrice())).collect(Collectors.toList());
        calculatedAveragePrice = listOfPrices.stream().reduce(0.0f, Float::sum)/listOfPrices.size();
        return new ProductCalculatedPrice(productPrices.getProductId(), calculatedAveragePrice);
    }
}
