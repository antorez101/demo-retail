package com.retail.entity;

import lombok.Data;
import lombok.ToString;
import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

import java.io.Serializable;

@Data
@ToString
@DefaultSchema(JavaBeanSchema.class)
public class User implements Serializable {

    String user_id;
    String http_request;
    String user_agent;
    String timestamp;
    Integer num_bytes;
    String ip;
    Integer http_response;
    Float lat;
    Float lng;
}
