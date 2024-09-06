package com.retail.transformer;

import com.retail.config.LocalOptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.values.PCollection;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReadFromLocalFileService {


    public void readFromLocalFile() {
        LocalOptions localOptions = PipelineOptionsFactory.create().as(LocalOptions.class);
        Pipeline pipeline = Pipeline.create(localOptions);
        log.info("Reading from file ...");
        PCollection<String> stringPCollection = pipeline.apply(TextIO.read().from(localOptions.getInput()));

        pipeline.run();
    }
}
