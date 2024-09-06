package com.retail.transformer;

import com.retail.entity.User;
import org.apache.beam.sdk.transforms.DoFn;

import java.io.Serializable;

public class PrintUserLogs extends DoFn<User, Void> implements Serializable {
    @ProcessElement
    public void processElement(ProcessContext c) {
        System.out.println(c.element());
    }
}
