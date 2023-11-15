package com.sysco.sampleService.model;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class ProductEnvelopeResponse {

    private String sellerId;

    private String productId;

    private ProductResponse data;

    private List<ErrorResponse> errorResponseList;

    public static ProductEnvelopeResponse generateResponse(String productId, String sellerId, ProductResponse data, List<ErrorResponse> errorResponseList) {
        return ProductEnvelopeResponse.builder()
                .sellerId(sellerId)
                .productId(productId)
                .data(data)
                .errorResponseList(errorResponseList)
                .build();
    }

}
