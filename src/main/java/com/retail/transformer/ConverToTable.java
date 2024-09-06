package com.retail.transformer;

import com.google.api.services.bigquery.model.TableRow;
import org.apache.beam.sdk.transforms.DoFn;

import java.io.Serializable;

public class ConverToTable extends DoFn<String, TableRow> implements Serializable {

    @ProcessElement
    public void processElement(ProcessContext c) {
        String arr[] = c.element().split(",");
        if(arr.length==7) {
            if(arr[6].equalsIgnoreCase("India")) {

                TableRow row = new TableRow();
                row.set("userId", arr[0]);
                row.set("orderId", arr[1]);
                row.set("name", arr[2]);
                row.set("productId", arr[3]);
                row.set("Amount", Integer.valueOf(arr[4]));
                row.set("order_date", arr[5]);
                row.set("country", arr[6]);
                c.output(row);
            }
        }
    }
}
