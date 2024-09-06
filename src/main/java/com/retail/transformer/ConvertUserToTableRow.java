package com.retail.transformer;

import com.google.api.services.bigquery.model.TableRow;
import com.retail.entity.User;
import org.apache.beam.sdk.transforms.DoFn;

public class ConvertUserToTableRow extends DoFn<User, TableRow> {

    @ProcessElement
    public void processElement(@Element User user, OutputReceiver<TableRow> receiver) {
        TableRow tableRow = new TableRow();
        tableRow.set("user_id", user.getUser_id());
        tableRow.set("http_request", user.getHttp_request());
        tableRow.set("user_agent", user.getUser_agent());
        tableRow.set("timestamp", user.getNum_bytes());
        tableRow.set("num_bytes", user.getUser_id());
        tableRow.set("ip", user.getIp());
        tableRow.set("http_response", user.getHttp_response());
        tableRow.set("lat", user.getLat());
        tableRow.set("lng", user.getLng());
        receiver.output(tableRow);
    }
}
