package com.locale.demo.controller;

import com.locale.demo.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

/**
 * @author Parisana
 */
@RestController
@RequestMapping("file-upload")
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("csv")
    public CompletableFuture<String> uploadCSV(@RequestParam("file") MultipartFile csvFile,
                                               @RequestParam(value = "customer-id", required = false, defaultValue = "1") Long customerId){
        return storageService.parseAndSave(customerId, csvFile);
    }

}
