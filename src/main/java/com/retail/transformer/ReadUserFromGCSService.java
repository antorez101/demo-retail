package com.retail.transformer;

import com.google.api.services.bigquery.model.TableFieldSchema;
import com.google.api.services.bigquery.model.TableSchema;
import com.google.common.collect.ImmutableList;
import com.retail.config.DataflowOptions;
import com.retail.entity.User;
import org.apache.beam.runners.dataflow.DataflowRunner;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.springframework.stereotype.Service;

@Service
public class ReadUserFromGCSService {


    public void readUserFromGCS() {

        DataflowOptions options = PipelineOptionsFactory.as(DataflowOptions.class);
        options.setProject("gcp-training-423221");
        options.setRegion("us-central1");
        options.setStagingLocation("gs://gcp_training70/other");
        options.setTempLocation("gs://gcp_training70/other");
        options.setRunner(DataflowRunner.class);

        Pipeline pipeline = Pipeline.create(options);

        PCollection<String> pInput =pipeline.apply(TextIO.read().from("gs://gcp_training70/other/event_test.json"));

       // pInput.apply(ParDo.of(new PrintLogTransformer()));

        PCollection<User> userPCollection = pInput.apply(ParDo.of(new ConvertStringToUser()));

        //userPCollection.apply(ParDo.of(new PrintUserLogs()));

        TableSchema schema = new TableSchema().setFields(
                ImmutableList.of(
                        new TableFieldSchema().setName("user_id").setType("STRING"),
                        new TableFieldSchema().setName("http_request").setType("STRING"),
                        new TableFieldSchema().setName("user_agent").setType("STRING"),
                        new TableFieldSchema().setName("timestamp").setType("INTEGER"),
                        new TableFieldSchema().setName("num_bytes").setType("STRING"),
                        new TableFieldSchema().setName("ip").setType("STRING"),
                        new TableFieldSchema().setName("http_response").setType("INTEGER"),
                        new TableFieldSchema().setName("lat").setType("FLOAT"),
                        new TableFieldSchema().setName("lng").setType("FLOAT")
                )
        );

        userPCollection.apply(ParDo.of(new ConvertUserToTableRow()))
        .apply(BigQueryIO.writeTableRows().to("retail_data.user_table")
                .withSchema(schema)
                .withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_TRUNCATE)
                .withCreateDisposition(BigQueryIO.Write.CreateDisposition.CREATE_IF_NEEDED));

        pipeline.run();
    }
}
