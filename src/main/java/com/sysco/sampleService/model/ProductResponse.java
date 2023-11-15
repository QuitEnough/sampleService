package com.sysco.sampleService.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductResponse {

    private String name;

    private String l1;

    private String l2;

    private String l3;



}
