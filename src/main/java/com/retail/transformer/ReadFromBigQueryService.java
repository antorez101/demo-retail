package com.retail.transformer;

import com.google.api.services.bigquery.model.TableFieldSchema;
import com.google.api.services.bigquery.model.TableSchema;
import com.retail.config.DataflowOptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.beam.runners.dataflow.DataflowRunner;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ReadFromBigQueryService {



    public void readFromBigQuery() {
        DataflowOptions options = PipelineOptionsFactory.as(DataflowOptions.class);
        options.setRunner(DataflowRunner.class);
        options.setProject("gcp-training-423221");
        options.setRegion("us-central1");
        options.setStagingLocation("gs://gcp_training70/input");
        options.setTempLocation("gs://gcp_training70/input");

        Pipeline pipeline = Pipeline.create(options);

        List<TableFieldSchema> columns = new ArrayList<TableFieldSchema>();
        columns.add(new TableFieldSchema().setName("userId").setType("STRING"));
        columns.add(new TableFieldSchema().setName("orderId").setType("STRING"));
        columns.add(new TableFieldSchema().setName("name").setType("STRING"));
        columns.add(new TableFieldSchema().setName("productId").setType("STRING"));
        columns.add(new TableFieldSchema().setName("Amount").setType("INTEGER"));
        columns.add(new TableFieldSchema().setName("order_date").setType("STRING"));
        columns.add(new TableFieldSchema().setName("country").setType("STRING"));

        TableSchema tblSchema = new TableSchema().setFields(columns);


        PCollection<String> pInput =pipeline.apply(TextIO.read().from("gs://gcp_training70/input/user_.csv"));


        pInput.apply(ParDo.of(new ConverToTable()))
                .apply(BigQueryIO.writeTableRows().to("retail_data.user_data")
                        .withSchema(tblSchema)
                        .withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND)
                        .withCreateDisposition(BigQueryIO.Write.CreateDisposition.CREATE_IF_NEEDED)
                );

        pipeline.run();

    }
}
