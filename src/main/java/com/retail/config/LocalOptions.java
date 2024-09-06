package com.retail.config;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;


public interface LocalOptions extends PipelineOptions {

    String PATH_TO_GCS = "C:\\Marko\\Learning\\Retail_GCP_course\\data_files\\events.json";
    String PATH_TO_BQTABLE = "gcp-training-423221:retail_data_set.test";

    @Description("Data set location")
    @Default.String(PATH_TO_GCS)
    String getInput();
    void setInput(String input);

    @Description("Path to bigquery table")
    @Default.String(PATH_TO_BQTABLE)
    String getOutput();
    void setOutput(String output);
}
