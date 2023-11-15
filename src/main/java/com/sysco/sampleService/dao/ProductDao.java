package com.sysco.sampleService.dao;

import com.sysco.sampleService.exception.UnprocessableEntityException;
import com.sysco.sampleService.model.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class ProductDao {


    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ProductDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public ProductResponse getProductResponse(String productId, String sellerId) {
        log.info("[Dao] executing query to get product data for: sellerId {} | productId {}", sellerId, productId); // parameterized
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("SELLER_ID", sellerId)
                .addValue("PRODUCT_ID", productId);

        try {
            return namedParameterJdbcTemplate.queryForObject("""
                    SELECT
                        products.details.value ->> 'name' as name,
                        products.taxonomy.value ->> 'L1' as L1,
                        products.taxonomy.value ->> 'L2' as L2,
                        products.taxonomy.value ->> 'L3' as L3
                    FROM
                        products.details
                    INNER JOIN
                        products.taxonomy
                    ON
                        products.details.seller_id = products.taxonomy.seller_id
                    WHERE
                        products.details.product_id = :PRODUCT_ID  AND products.details.seller_id = :SELLER_ID
                    """, mapSqlParameterSource, rowMapper);
        } catch (EmptyResultDataAccessException ex) {
            log.warn("[Dao] No product data found for: sellerId {} | productId {}", sellerId, productId);
            return null;
        } catch (Exception e) {
            log.error("[Dao] an error occurred when querying product data for: sellerId {} | productId {}", sellerId, productId);
            throw new UnprocessableEntityException("Unable to query DB" + e.getMessage(), e);
        }
    }

    private final RowMapper<ProductResponse> rowMapper = (rs, rowNum) -> {
        return ProductResponse.builder()
                .name(rs.getString("name"))
                .l1(rs.getString("l1"))
                .l2(rs.getString("l2"))
                .l3(rs.getString("l3"))
                .build();
    };

}
