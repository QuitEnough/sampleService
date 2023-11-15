package com.sysco.sampleService.controller;

import com.sysco.sampleService.model.ProductEnvelopeResponse;
import com.sysco.sampleService.service.ProductService;
import com.sysco.sampleService.validation.ValidProductId;
import com.sysco.sampleService.validation.ValidSellerId;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Validated
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/sellerId/{sellerId}/products/{productId}/taxonomy", produces = "application/json")
    public ProductEnvelopeResponse getResponse(@PathVariable @ValidSellerId String sellerId,
                                               @PathVariable @ValidProductId String productId) {
        log.info("[RequestParams] get product data for sellerId: {} | productId: {}", sellerId, productId);
        ProductEnvelopeResponse response = productService.getProductEnvelopeResponse(productId, sellerId);
        log.info("[Response] get product data {}", response);
        return response;
    }
}
