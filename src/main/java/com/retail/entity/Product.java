package com.retail.entity;

import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

@DefaultSchema(JavaBeanSchema.class)
public class Product {

    String userId;
    String orderId;
    String name;
    String productId;
    Integer Amount;
    String order_date;
    String country;
}
