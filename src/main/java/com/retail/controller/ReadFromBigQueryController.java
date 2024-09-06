package com.retail.controller;

import com.retail.transformer.ReadFromBigQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ReadFromBigQueryController {

    private final ReadFromBigQueryService readFromBigQueryService;

    public ReadFromBigQueryController(ReadFromBigQueryService readFromBigQueryService) {
        this.readFromBigQueryService = readFromBigQueryService;
    }


    @GetMapping(path = "/readbq")
    public void readFromBigQuery(){
        readFromBigQueryService.readFromBigQuery();
    }
}
