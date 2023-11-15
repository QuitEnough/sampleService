package com.sysco.sampleService.service;

import com.sysco.sampleService.dao.ProductDao;
import com.sysco.sampleService.model.ProductEnvelopeResponse;
import com.sysco.sampleService.model.ProductResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ProductServiceTest {

    private final ProductDao productDao = mock(ProductDao.class);

    private final ProductService productService = new ProductService(productDao);

    @Test
    public void testGetResponse_success() {

        ProductResponse productResponse = ProductResponse.builder()
                .name("Grape Jelly")
                .l1("Condiments")
                .l2("Jelly/Jam")
                .l3("Grape")
                .build();
        when(productDao.getProductResponse("102", "CABL")).thenReturn(productResponse);

        ProductEnvelopeResponse response = productService.getProductEnvelopeResponse("102", "CABL");

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
        when(productDao.getProductResponse("102", "CABL")).thenReturn(null);
        ProductEnvelopeResponse response = productService.getProductEnvelopeResponse("102", "CABL");

        assertNotNull(response);
        assertEquals("CABL", response.getSellerId());
        assertEquals("102", response.getProductId());

        assertNull(response.getData());

        assertFalse(response.getErrorResponseList().isEmpty());
        assertEquals(404, response.getErrorResponseList().get(0).getStatusCode());
        assertEquals("Product not found for <SellerIdProductId[sellerId=CABL, productId=102]>", response.getErrorResponseList().get(0).getMessage());



    }



}
