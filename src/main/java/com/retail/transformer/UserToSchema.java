package com.retail.transformer;

import org.apache.beam.sdk.schemas.Schema;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.windowing.IntervalWindow;
import org.apache.beam.sdk.values.Row;
import org.joda.time.Instant;

public class UserToSchema extends DoFn<Long, Row> {

    @ProcessElement
    public void processElement(@Element Long pageviews, OutputReceiver<Row> r, IntervalWindow window) {
        Instant i = Instant.ofEpochMilli(window.start().getMillis());
        Row timeStampedRow = Row.withSchema(pageViewsSchema)
                .addValues(pageviews, i)
                .build();
        r.output(timeStampedRow);
    }

    public final Schema pageViewsSchema = Schema
            .builder()
            .addInt64Field("pageviews")
            .addDateTimeField("minute")
            .build();
}
