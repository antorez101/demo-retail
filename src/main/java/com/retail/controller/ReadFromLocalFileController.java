package com.retail.controller;

import com.retail.transformer.ReadFileAndCountByWindow;
import com.retail.transformer.ReadFromLocalFileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class ReadFromLocalFileController {

    private final ReadFromLocalFileService readFromLocalFileService;
    private final ReadFileAndCountByWindow readFileAndCountByWindow;

    public ReadFromLocalFileController(ReadFromLocalFileService readFromLocalFileService, ReadFileAndCountByWindow readFileAndCountByWindow) {
        this.readFromLocalFileService = readFromLocalFileService;
        this.readFileAndCountByWindow = readFileAndCountByWindow;
    }

    @GetMapping(path = "/read")
    public void readFromLocalFile(){
        readFromLocalFileService.readFromLocalFile();
    }

    @GetMapping(path = "/read/count")
    public void readFromFileAndCount() {
        readFileAndCountByWindow.readFromLocalFileAndCountByWindow();
    }
}
