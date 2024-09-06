package com.retail.config;

import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;

public interface DataflowOptions extends DataflowPipelineOptions {

    String PATH_TO_GCS = "gs://gcp-training-bucket-423221/retail_events_data1.json";
    String PATH_TO_BQTABLE = "gcp-training-423221:retail_data_set.products";

    @Description("Data set location")
    @Default.String(PATH_TO_GCS)
    String getInput();
    void setInput(String input);

    @Description("Path to bigquery table")
    @Default.String(PATH_TO_BQTABLE)
    String getOutput();
    void setOutput(String output);
}
