package com.sysco.sampleService.controller;


import com.sysco.sampleService.model.ErrorResponse;
import com.sysco.sampleService.model.ProductEnvelopeResponse;
import com.sysco.sampleService.model.ProductResponse;
import com.sysco.sampleService.service.ProductService;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductControllerTest {

    private final ProductService productService = mock(ProductService.class);

    private final ProductController productController = new ProductController(productService);


    @Test
    public void testGetResponse_success() {

        ProductEnvelopeResponse productResponse = ProductEnvelopeResponse.builder()
                .sellerId("CABL")
                .productId("102")
                .data(ProductResponse.builder()
                        .name("Grape Jelly")
                        .l1("Condiments")
                        .l2("Jelly/Jam")
                        .l3("Grape")
                        .build())
                .errorResponseList(List.of())
                .build();

        when(productService.getProductEnvelopeResponse("102", "CABL")).thenReturn(productResponse);

        ProductEnvelopeResponse response = productController.getResponse("CABL", "102");

        assertNotNull(response);
        assertEquals("CABL", response.getSellerId());
        assertEquals("102", response.getProductId());

        assertEquals("Grape Jelly", response.getData().getName());
        assertEquals("Condiments", response.getData().getL1());
        assertEquals("Jelly/Jam", response.getData().getL2());
        assertEquals("Grape", response.getData().getL3());

        assertTrue(response.getErrorResponseList().isEmpty());
    }


    @Test
    public void testGetResponse_fail() {

        ProductEnvelopeResponse productResponse = ProductEnvelopeResponse.builder()
                .sellerId("CABL")
                .productId("102")
                .data(null)
                .errorResponseList(List.of(new ErrorResponse(404, "Product not found")))
                .build();

        when(productService.getProductEnvelopeResponse("102", "CABL")).thenReturn(productResponse);

        ProductEnvelopeResponse response = productController.getResponse("CABL", "102");


        assertNotNull(response);

        assertEquals("CABL", response.getSellerId());
        assertEquals("102", response.getProductId());
        assertEquals(404, response.getErrorResponseList().get(0).getStatusCode());
        assertEquals("Product not found", response.getErrorResponseList().get(0).getMessage());

        assertNull(response.getData());
    }


}
