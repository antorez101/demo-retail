package com.retail.transformer;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.Row;

import java.io.Serializable;

public class LogRows extends DoFn<Row, Void> implements Serializable {

    @ProcessElement
    public void processElement(@Element Row r) {
        System.out.println(r);
    }
}
