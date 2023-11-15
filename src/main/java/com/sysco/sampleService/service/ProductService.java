package com.sysco.sampleService.service;


import com.sysco.sampleService.dao.ProductDao;
import com.sysco.sampleService.model.ErrorResponse;
import com.sysco.sampleService.model.ProductEnvelopeResponse;
import com.sysco.sampleService.model.ProductResponse;
import com.sysco.sampleService.model.SellerIdProductId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@Slf4j
public class ProductService {

    private final ProductDao productDao;


    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public ProductEnvelopeResponse getProductEnvelopeResponse(String productId, String sellerId) {
        ProductResponse productResponse = productDao.getProductResponse(productId, sellerId);


        if (productResponse == null) {
            SellerIdProductId sellerIdProductId = new SellerIdProductId(sellerId, productId);
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), format("Product not found for <%s>", sellerIdProductId));
            return ProductEnvelopeResponse.generateResponse(productId, sellerId, null, List.of(errorResponse));
        }
        log.info("[Service] get product data returned for: {}", productResponse);
        return ProductEnvelopeResponse.generateResponse(productId, sellerId, productResponse, List.of());
    }
}
