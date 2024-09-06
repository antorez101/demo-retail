package com.retail.transformer;

import org.apache.beam.sdk.transforms.DoFn;

import java.io.Serializable;

public class PrintLogTransformer extends DoFn<String, Void> implements Serializable {

    @ProcessElement
    public void processElement(@Element String json) {
        System.out.println(json);
    }
}
