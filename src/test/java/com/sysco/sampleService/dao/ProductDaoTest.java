package com.sysco.sampleService.dao;

import com.sysco.sampleService.exception.UnprocessableEntityException;
import com.sysco.sampleService.model.ProductResponse;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductDaoTest {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate= mock(NamedParameterJdbcTemplate.class);
    private final ProductDao productDao = new ProductDao(namedParameterJdbcTemplate);

     @Test
    public void testGetResponse_fail2() {
        when(namedParameterJdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), any(RowMapper.class)))
                .thenThrow(EmptyResultDataAccessException.class);
        ProductResponse response = productDao.getProductResponse("102", "CABL");
        assertNull(response);
    }

    @Test
    public void testGetResponse_fail3() {
        when(namedParameterJdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), any(RowMapper.class)))
                .thenThrow(UnprocessableEntityException.class);
        try {
            productDao.getProductResponse("102", "CABL");
        } catch (UnprocessableEntityException e) {
            assertTrue(e.getMessage().contains("Unable to query DB"));
        }
    }


    @Test
    public void testGetResponse_success2() {

        ProductResponse productResponse = ProductResponse.builder()
                .name("Grape Jelly")
                .l1("Condiments")
                .l2("Jelly/Jam")
                .l3("Grape")
                .build();

        when(namedParameterJdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), any(RowMapper.class)))
                .thenReturn(productResponse);

        ProductResponse response = productDao.getProductResponse("102", "CABL");

        assertNotNull(productResponse);
        assertEquals("Grape Jelly", productResponse.getName());
        assertEquals("Condiments", productResponse.getL1());
        assertEquals("Jelly/Jam", productResponse.getL2());
        assertEquals("Grape", productResponse.getL3());
    }



}
