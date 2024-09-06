package com.retail.transformer;

import com.retail.config.LocalOptions;
import com.retail.entity.User;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.schemas.Schema;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.transforms.windowing.FixedWindows;
import org.apache.beam.sdk.transforms.windowing.IntervalWindow;
import org.apache.beam.sdk.transforms.windowing.Window;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.Row;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class ReadFileAndCountByWindow {


    public void readFromLocalFileAndCountByWindow() {

        LocalOptions options = PipelineOptionsFactory.as(LocalOptions.class);

        Pipeline pipeline = Pipeline.create(options);

        PCollection<User> users = pipeline.apply(TextIO.read().from(options.getInput()))
                .apply(ParDo.of(new ConvertStringToUser()))

                .apply("AddEventTimeStamps", WithTimestamps.of(
                        (User user) -> Instant.parse(user.getTimestamp())
                ));
        //users.apply(ParDo.of(new PrintUserLogs()));
        PCollection<Row> pageView =  users.apply("WindowPerMinute", Window.into(FixedWindows.of(Duration.standardSeconds(60))))

                .apply("CountPerMinute", Combine.globally(Count.<User>combineFn()).withoutDefaults())

                        .apply("AddwindowTimestamp", ParDo.of(new UserToSchema())).setRowSchema(pageViewsSchema);

        pageView.apply(ParDo.of(new LogRows()));

        pipeline.run();

    }

    public final Schema pageViewsSchema = Schema
            .builder()
            .addInt64Field("pageviews")
            .addDateTimeField("minute")
            .build();

}
