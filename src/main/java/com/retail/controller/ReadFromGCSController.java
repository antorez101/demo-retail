package com.retail.controller;

import com.retail.transformer.ReadUserFromGCSService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gcs")
public class ReadFromGCSController {

    private final ReadUserFromGCSService readUserFromGCSService;

    public ReadFromGCSController(ReadUserFromGCSService readUserFromGCSService) {
        this.readUserFromGCSService = readUserFromGCSService;
    }

    @GetMapping(path = "/read")
    public void readFromGCS() {
        readUserFromGCSService.readUserFromGCS();
    }
}
